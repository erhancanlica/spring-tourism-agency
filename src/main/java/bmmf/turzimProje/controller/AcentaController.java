package bmmf.turzimProje.controller;

import bmmf.turzimProje.model.AcentaUser;
import bmmf.turzimProje.model.Client;
import bmmf.turzimProje.model.Staff;
import bmmf.turzimProje.model.dto.ClientFilterRequest;
import bmmf.turzimProje.model.dto.ClientTourDto;
import bmmf.turzimProje.model.dto.GeneralResponse;
import bmmf.turzimProje.model.dto.TourDto;
import bmmf.turzimProje.model.enums.TourType;
import bmmf.turzimProje.service.ClientService;
import bmmf.turzimProje.service.StaffService;
import bmmf.turzimProje.service.TourService;
import bmmf.turzimProje.utils.Constants;
import bmmf.turzimProje.utils.ExcelGenerator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/acenta")
public class AcentaController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private TourService tourService;
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ModelAndView dashboard(HttpSession httpSession){
        AcentaUser acentaUser = (AcentaUser) httpSession.getAttribute(Constants.userInfoKey);
        ModelAndView modelAndView = new ModelAndView("acenta/dashboard");
        modelAndView.addObject("staffs",staffService.findAllStaff(acentaUser));
        return modelAndView;
    }

    @PostMapping("staff")
    @ResponseBody
    public GeneralResponse createStaff(@RequestBody Staff staff, HttpSession httpSession){
        AcentaUser acentaUser = (AcentaUser) httpSession.getAttribute(Constants.userInfoKey);
        return staffService.save(staff, acentaUser);
    }

    @DeleteMapping("staff")
    @ResponseBody
    public GeneralResponse deleteStaff(@RequestParam("id") Long id, HttpSession httpSession){
        AcentaUser acentaUser = (AcentaUser) httpSession.getAttribute(Constants.userInfoKey);
        return staffService.delete(id,acentaUser);
    }

    @PutMapping("staff")
    @ResponseBody
    public GeneralResponse updateStaff(@RequestBody Staff staff, HttpSession httpSession){
        AcentaUser acentaUser = (AcentaUser) httpSession.getAttribute(Constants.userInfoKey);
        return staffService.update(staff, acentaUser);
    }

    @PostMapping("createTour")
    @ResponseBody
    public GeneralResponse createTour(@RequestBody TourDto tourDto, HttpSession httpSession){
        AcentaUser acentaUser = (AcentaUser) httpSession.getAttribute(Constants.userInfoKey);
        return tourService.save(tourDto,acentaUser);

    }

    @GetMapping("getTour")
    public ModelAndView getTour(HttpSession httpSession){
        AcentaUser acentaUser = (AcentaUser) httpSession.getAttribute(Constants.userInfoKey);
        ModelAndView view =new ModelAndView("acenta/createTour");
        view.addObject("staffs",staffService.findAllStaff(acentaUser));
        view.addObject("tourTypes", TourType.values());

        return view;
    }

    @GetMapping("tours")
    public ModelAndView getTours(HttpSession httpSession){
        AcentaUser acentaUser = (AcentaUser) httpSession.getAttribute(Constants.userInfoKey);
        ModelAndView view =new ModelAndView("acenta/tours");
//        view.addObject("staffs",staffService.findAllStaff(acentaUser));
        return view;
    }

    @GetMapping("getTours")
    @ResponseBody
    public List<TourDto> findTours(@ModelAttribute TourDto tourDto, HttpSession httpSession)throws Exception{
        AcentaUser acentaUser = (AcentaUser) httpSession.getAttribute(Constants.userInfoKey);
        tourDto.setAcenta_Id(acentaUser.getId());
        return tourService.findByTour(tourDto);
    }

    @GetMapping("/client")
    public ModelAndView dashboardClient(HttpSession httpSession, @ModelAttribute ClientFilterRequest filterRequest, @RequestParam(value = "id", required = false) Long id){

        ModelAndView modelAndView = new ModelAndView("acenta/clients");
        List<Client> clients = new ArrayList<>();
        if (id != null){
            clients = clientService.findTourClient(id);
        }else {
            clients = clientService.findAllClient(filterRequest);
        }
        modelAndView.addObject("tourId", id);
        httpSession.setAttribute("clients",clients);
        modelAndView.addObject("clients",clients);
        return modelAndView;
    }

    @PostMapping("/client")
    @ResponseBody
    public GeneralResponse createClient(@RequestBody ClientTourDto clientTourDto){
        return clientService.save(clientTourDto);
    }

    @DeleteMapping("/client")
    @ResponseBody
    public GeneralResponse deleteClient(@RequestParam("id") Long id, @RequestParam(value = "tourId", required = false) Long tourId){
        return clientService.delete(id, tourId);
    }

    @PutMapping("/client")
    @ResponseBody
    public GeneralResponse updateClient(@RequestBody Client client){
        return clientService.update(client);
    }

    @GetMapping("/tst")
    @ResponseBody
    public GeneralResponse tst(ModelAndView modelAndView){
        GeneralResponse response = new GeneralResponse();
        return response;
    }

    @PostMapping("/importClient")
    @ResponseBody
    public GeneralResponse importClient(@RequestParam("file") MultipartFile reapExcelDataFile, @RequestParam(value = "tourId",required = false) Long tourId) throws IOException {
        GeneralResponse response = new GeneralResponse();
        try {
            List<ClientTourDto> tempStudentList = new ArrayList<ClientTourDto>();
            XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                ClientTourDto tempClient = new ClientTourDto();

                XSSFRow row = worksheet.getRow(i);

                tempClient.setName(row.getCell(0).getStringCellValue());
                tempClient.setSurname(row.getCell(1).getStringCellValue());
                tempClient.setEmail(row.getCell(2).getStringCellValue());
                tempClient.setPhone(row.getCell(3).getStringCellValue());
                tempClient.setAddress(row.getCell(4).getStringCellValue());
                tempClient.setTourId(isNull(tourId) ? 0 : tourId);
                tempStudentList.add(tempClient);
            }
            response = tourService.multiClientInsert(tempStudentList, tourId);
            System.out.println(tempStudentList);
        } catch (Exception e) {
            response.setResult(0);
            response.setMessage("Excel okurken hata oluştu.");
        }
        return response;
    }

    @GetMapping(value = "/download/customers.xlsx")
    public ResponseEntity excelCustomersReport(HttpSession httpSession) throws IOException {
        ByteArrayInputStream in = ExcelGenerator.clientsToExcel((List<Client>) httpSession.getAttribute("clients"));
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
