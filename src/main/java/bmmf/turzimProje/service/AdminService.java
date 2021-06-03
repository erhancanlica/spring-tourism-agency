package bmmf.turzimProje.service;

import bmmf.turzimProje.dao.AcentaUserDao;
import bmmf.turzimProje.dao.AdminDao;
import bmmf.turzimProje.model.Admins;
import bmmf.turzimProje.model.Users;
import bmmf.turzimProje.model.dto.CreateUserDto;
import bmmf.turzimProje.model.dto.GeneralResponse;
import bmmf.turzimProje.model.enums.UserType;
import bmmf.turzimProje.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserService userService;
    @Autowired
    private AcentaUserService acentaService;
    @Autowired
    private AcentaUserDao acentaUserDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Admins findByUser(Users user) {
        return adminDao.findByUser(user);
    }

    public GeneralResponse createAcentaUser(CreateUserDto userDto) {

        GeneralResponse response = null;

        Users user = userService.findByUserName(userDto.getUsername());
        if (nonNull(user)) {
            return GeneralResponse
                    .builder()
                    .message("Kullanıcı ismi Kullanılıyor.")
                    .result(1)
                    .build();
        }
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));


        if (UserType.ACENTA.equals(userDto.getUserType())){
          response = acentaService.createAcenteUser(userDto);
        }

        else if (UserType.ADMIN.equals(userDto.getUserType())){
            response = GeneralResponse.builder().build();

            try {
                adminDao.createUserAdmin(userDto);
                response.setMessage(Constants.success);
                response.setResult(0);

            } catch (Exception ex) {
                response.setMessage(Constants.err);
                response.setResult(1);
            }
        }
        return response;

    }

    private List<CreateUserDto> getAllAdmin(){
        List<Admins> admins = adminDao.findAllAdmin();
            List<CreateUserDto> userDto = admins.stream().map((Admins admin) -> CreateUserDto.builder()
                               .id(admin.getUser().getId())
                               .userId(admin.getId())
                               .username(admin.getUser().getUsername())
                               .password(admin.getUser().getPassword())
                               .userType(admin.getUser().getUserType())
                               .roleName(admin.getAdminName())
                               .build())
                               .collect(Collectors.toList());
        return userDto;
    }

    public List<CreateUserDto> getAllAdminAcenta(){

        List<CreateUserDto> admin = getAllAdmin();
        List<CreateUserDto> acenta = acentaService.getAllAcenta();
        admin.addAll(acenta);

        return admin;
    }

    public GeneralResponse deleteUser(CreateUserDto userDto) {

        GeneralResponse generalResponse = new GeneralResponse();

        if (userDto.getUserType().getDescription() == "ACENTA"){

            try{

                acentaUserDao.deleteAcenta(userDto);
                generalResponse.setMessage("Silme İşlemi Başarılı...");
                generalResponse.setResult(0);

            }catch (Exception e){

                generalResponse.setMessage("Silme İşlemi Başarısız...");
                generalResponse.setResult(1);
            }

        }

        else if (userDto.getUserType().getDescription() == "ADMIN"){

            try{

                adminDao.deleteUserAdmin(userDto);
                generalResponse.setMessage("Silme İşlemi Başarılı...");
                generalResponse.setResult(0);

            }catch (Exception e){

                generalResponse.setMessage("Silme İşlemi Başarısız...");
                generalResponse.setResult(1);
            }

        }

        return generalResponse;

    }

    public GeneralResponse updateUser(CreateUserDto userDto) {

        GeneralResponse generalResponse = new GeneralResponse();

        if (userDto.getUserType().getDescription() == "ACENTA") {

            try {

                acentaUserDao.updateUserAcenta(userDto);
                generalResponse.setMessage("Güncelleme İşlemi Başarılı...");
                generalResponse.setResult(0);

            } catch (Exception e) {

                generalResponse.setMessage("Güncelleme İşlemi Başarısız...");
                generalResponse.setResult(1);
            }

        } else if (userDto.getUserType().getDescription() == "ADMIN") {

            try {

                adminDao.updateUserAdmin(userDto);
                generalResponse.setMessage("Güncelleme İşlemi Başarılı...");
                generalResponse.setResult(0);

            } catch (Exception e) {

                generalResponse.setMessage("Güncelleme İşlemi Başarısız...");
                generalResponse.setResult(1);
            }
        }

        return generalResponse;
    }
}
