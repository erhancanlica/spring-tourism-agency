package bmmf.turzimProje.model.dto;

import bmmf.turzimProje.model.enums.UserType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private Long id;
    private Long userId;
    private String username;
    private String password;
    private UserType userType;
    private String roleName;
 }



