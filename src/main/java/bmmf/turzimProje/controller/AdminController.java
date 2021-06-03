package bmmf.turzimProje.controller;

import bmmf.turzimProje.model.Admins;
import bmmf.turzimProje.model.Users;
import bmmf.turzimProje.model.dto.CreateUserDto;
import bmmf.turzimProje.model.dto.GeneralResponse;
import bmmf.turzimProje.model.enums.UserType;
import bmmf.turzimProje.service.AdminService;
import bmmf.turzimProje.service.UserService;
import bmmf.turzimProje.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping("/listUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getUserList(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("listUsers");
        List<UserType> userTypes = Arrays.asList(UserType.values());
        List<CreateUserDto> users = adminService.getAllAdminAcenta();
        modelAndView.addObject("users", users);
        modelAndView.addObject("userRoles",userTypes);
        return modelAndView;
    }

    @GetMapping("/createUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getCreateUser(HttpSession httpSession){

        ModelAndView modelAndView = new ModelAndView("createUser");
        List<UserType> userTypes = Arrays.asList(UserType.values());
        modelAndView.addObject("userRoles",userTypes);
        modelAndView.addObject("users", new Users());

        return modelAndView;
    }

    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public GeneralResponse postCreateUser(@RequestBody CreateUserDto userDto, HttpSession httpSession){
        GeneralResponse response = adminService.createAcentaUser(userDto);
        return response;
    }


    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public GeneralResponse deleteUser(@RequestBody CreateUserDto userDto){
        GeneralResponse response = adminService.deleteUser(userDto);
            return response;
    }


    @PutMapping("/updateUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public GeneralResponse updateUser(@RequestBody CreateUserDto userDto){

        GeneralResponse response = adminService.updateUser(userDto);
        return response;

    }

    @GetMapping("/backup")
    public ModelAndView backupSQL(Map<String, Object> map){
        ModelAndView modelAndView = new ModelAndView("backup");
        return modelAndView;

    }

    @GetMapping("/create-backup")
    public ModelAndView backUpProcess(){
        ModelAndView modelAndView = new ModelAndView("backup");
        String fileName = "backup_" + new Date().getTime();
        String cmd = String.format("expdp system/237529 directory=DUMP_DIR SCHEMAS=turizm logfile=%s.log dumpfile=%s.dmp",fileName,fileName);
        try {
            Runtime.getRuntime().exec(cmd);
        }catch (Exception e){
            log.error("[Backup database] failed:{}", e.getMessage());
            modelAndView.addObject("backupMsg","Hata oluştu");
        }
        log.info("[Backup database] successful, SQL Document:{}", fileName);
        modelAndView.addObject("backupMsg","Database backup successful");

        return modelAndView;
    }

    @PostMapping("/restore")
    public ModelAndView restoreDatabase(@RequestParam("backup") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView("backup");

        String fileName = file.getOriginalFilename();
        String logName = fileName.split("\\.")[0]+".log";

        String cmd = String.format("impdp system/237529 schemas=turizm directory=DUMP_DIR dumpfile=%s logfile=%s TABLE_EXISTS_ACTION=REPLACE",fileName,logName);
        try {
            Runtime.getRuntime().exec(cmd);
        }catch (Exception e){
            log.error("[Backup database] failed:{}", e.getMessage());
            modelAndView.addObject("backupMsg","Hata oluştu");
        }
        log.info("[Backup database] successful, SQL Document:{}", fileName);
        modelAndView.addObject("backupMsg","Yedek Başarıyla yüklendi");

        return modelAndView;
    }



}
