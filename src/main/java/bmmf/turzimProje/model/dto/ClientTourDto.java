package bmmf.turzimProje.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientTourDto {
    private long id;
    private long tourId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;


}
