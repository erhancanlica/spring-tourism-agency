package bmmf.turzimProje.model.dto;

import bmmf.turzimProje.model.enums.TourType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {
    private Long id;
    private String startDate;
    private String endDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Long> personels;
    private String tourType;
    private String details;
    private String location;
    private Integer capasity;
    private Integer price;
    private Integer totalPrice;
    private Long acenta_Id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String personelNames;
}
