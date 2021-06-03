package bmmf.turzimProje.service;

import bmmf.turzimProje.dao.AcentaUserDao;
import bmmf.turzimProje.dao.AdminDao;
import bmmf.turzimProje.dao.UserDao;
import bmmf.turzimProje.model.AcentaUser;
import bmmf.turzimProje.model.Admins;
import bmmf.turzimProje.model.Users;
import bmmf.turzimProje.model.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AcentaUserDao acentaUserDao;

    @Autowired
    private AdminDao adminDao;

    public Users findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}
