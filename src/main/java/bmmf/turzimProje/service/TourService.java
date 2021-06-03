package bmmf.turzimProje.service;

import bmmf.turzimProje.dao.ClientDao;
import bmmf.turzimProje.dao.ClientTourDao;
import bmmf.turzimProje.dao.PersonelTourDao;
import bmmf.turzimProje.dao.TourDao;
import bmmf.turzimProje.model.AcentaUser;
import bmmf.turzimProje.model.Staff;
import bmmf.turzimProje.model.Tour;
import bmmf.turzimProje.model.dto.ClientTourDto;
import bmmf.turzimProje.model.dto.GeneralResponse;
import bmmf.turzimProje.model.dto.QueryParam;
import bmmf.turzimProje.model.dto.TourDto;
import bmmf.turzimProje.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@Transactional
public class TourService {

    @Autowired
    private TourDao tourDao;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PersonelTourDao personelTourDao;

    public GeneralResponse save(TourDto tourDto, AcentaUser acentaUser){
        GeneralResponse response = GeneralResponse.builder().build();
        try{
            if(StringUtils.isEmpty(tourDto.getStartDate())){
                response.setResult(1);
                response.setMessage("Başlangıc Tarihi giriniz");
            }
            if(StringUtils.isEmpty(tourDto.getEndDate())){
                response.setResult(1);
                response.setMessage("Bitiş Tarihi giriniz");
            }
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.from(f.parse(tourDto.getStartDate()));
            LocalDate endDate = LocalDate.from(f.parse(tourDto.getEndDate()));
            if (startDate.isAfter(endDate)){
                response.setMessage("Başlangıç tarihi, bitiş tarihinden büyük");
                response.setResult(1);
                return response;
            }
        }catch (Exception e){
            response.setResult(1);
            response.setMessage("Girdiğiniz Tarih Hatalı");
            return response;
        }
        try {
            long tId = tourDao.insert(tourDto, acentaUser);
            tourDto.getPersonels().forEach(id -> personelTourDao.save(tId,id));
            response.setResult(0);
            response.setMessage(Constants.success);
        } catch (Exception e){
            response.setResult(1);
            response.setMessage(Constants.err);
        }
        return response;
    }

    public List<TourDto> findByTour(TourDto tourDto) throws Exception{

        List<Field> fields = Arrays.asList(TourDto.class.getDeclaredFields());
        List<QueryParam> queryParams = fields.stream().map(s -> {
            s.setAccessible(true);
            QueryParam param = new QueryParam();
            try {
                param.setFieldName(s.getName());
                param.setType(s.getType());
                param.setValue(s.get(tourDto));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return param;
        }).filter(s-> nonNull(s) && nonNull(s.getValue())).collect(Collectors.toList());
        String query = createQueryParam(queryParams);
        List<Tour> tours = tourDao.findByTour(query, queryParams);

        return tours.stream().map(tour ->
                TourDto.builder()
                        .id(tour.getId())
                        .capasity(tour.getCapasity())
                        .details(tour.getDetails())
                        .endDate(tour.getEndDate())
                        .startDate(tour.getStartDate())
                        .location(tour.getLocation())
                        .price(tour.getPrice())
                        .totalPrice(tour.getTotalPrice())
                        .tourType(tour.getTourType().getName())
                        .build()
        ).collect(Collectors.toList());
    }

    public GeneralResponse multiClientInsert(List<ClientTourDto> clientTourDtos, Long tourId){
        GeneralResponse response = GeneralResponse.builder().result(1).message(Constants.err).build();
        try {
            clientTourDtos.forEach(clientTourDto -> clientService.save(clientTourDto));
            response.setResult(0);
            response.setMessage(Constants.success);
        } catch (Exception ex){
            log.error(ex.getMessage());
        }
        return response;
    }

    private String createQueryParam(List<QueryParam> queryParams){

        StringBuilder stringBuilder = new StringBuilder();
        if(queryParams.size()>1){
            for(int i = 0; i<queryParams.size(); i++){
                if(i == 0){
                    stringBuilder.append(getQuery(queryParams.get(i)));
                }else {
                    stringBuilder.append(" and ");
                    stringBuilder.append(getQuery(queryParams.get(i)));
                }
            }
        } else if (queryParams.size() == 1){
            stringBuilder.append(getQuery(queryParams.get(0)));
        }
        return stringBuilder.toString();
    }

    private String getQuery(QueryParam queryParam){
        String query = "";
        if(queryParam.getType().equals(String.class)){
            query = "LOWER("+queryParam.getFieldName()+")"+ " LIKE :"+queryParam.getFieldName();
            queryParam.setValue("%"+queryParam.getValue().toString().toLowerCase()+"%");
        } else {
            query = queryParam.getFieldName() + "=:"+queryParam.getFieldName();
        }
        return query;
    }
}
