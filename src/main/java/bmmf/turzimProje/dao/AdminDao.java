package bmmf.turzimProje.dao;

import bmmf.turzimProje.model.AcentaUser;
import bmmf.turzimProje.model.Admins;
import bmmf.turzimProje.model.Users;
import bmmf.turzimProje.model.dto.CreateUserDto;
import bmmf.turzimProje.model.dto.GeneralResponse;
import bmmf.turzimProje.model.enums.UserType;
import bmmf.turzimProje.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Executable;
import java.math.BigDecimal;
import java.sql.SQLData;
import java.util.List;

@Repository
@Slf4j
public class AdminDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserService userService;

    public Admins findByUser(Users user) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select ad.id,ad.adminName,ad.UserID from admin ad  join users u on ad.UserID=u.id where u.id=:userId");
        sqlQuery.setParameter("userId", user.getId());
        sqlQuery.addEntity(Admins.class);
        return (Admins) sqlQuery.getSingleResult();
    }

    public List<Admins> findAllAdmin() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select * from admin ad  join users u on ad.UserID=u.id").addEntity(Admins.class);
        List<Admins> admins = sqlQuery.getResultList();
        return admins;
    }

    public List<AcentaUser> findAllAcenta() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select * from acentauser ac  join users u on ac.UserID=u.id").addEntity(AcentaUser.class);
        List<AcentaUser> acentaUsers = sqlQuery.getResultList();
        return acentaUsers;
    }



    public void deleteUserAdmin(CreateUserDto userDto){

        Long userId = userDto.getId();
        Long adminId = userDto.getUserId();

        SQLQuery sqlQueryAdmin = sessionFactory.getCurrentSession().createSQLQuery("delete from admin where id=:adminId");
        sqlQueryAdmin.setParameter("adminId", adminId);
        sqlQueryAdmin.executeUpdate();

        SQLQuery sqlQueryUser = sessionFactory.getCurrentSession().createSQLQuery("delete from users where id=:userId");
        sqlQueryUser.setParameter("userId", userId);
        sqlQueryUser.executeUpdate();
    }


    public void createUserAdmin(CreateUserDto userDto) {

        Session session = sessionFactory.getCurrentSession();
        BigDecimal result =  (BigDecimal) session.createSQLQuery("select user_seq.nextVal from dual").getSingleResult();
        int userID = result.intValue();
        SQLQuery sqlQuery = session.createSQLQuery("INSERT INTO users values (:id, :password, :userType, :username)");
        sqlQuery.setParameter("password", userDto.getPassword());
        sqlQuery.setParameter("userType", userDto.getUserType().getDescription());
        sqlQuery.setParameter("username", userDto.getUsername());
        sqlQuery.setParameter("id", userID);
        sqlQuery.executeUpdate();

        SQLQuery sqlQuery2 = session.createSQLQuery("INSERT INTO admin(adminName, userID) values (:adminName, :userId)");
        sqlQuery2.setParameter("adminName",userDto.getRoleName());
        sqlQuery2.setParameter("userId", userID);
        sqlQuery2.executeUpdate();
    }

    public void updateUserAdmin(CreateUserDto userDto) {

        SQLQuery sqlQueryFindType = sessionFactory.getCurrentSession().createSQLQuery("select * from users where id=:id").addEntity(Users.class);
        sqlQueryFindType.setParameter("id", userDto.getId());
        Users users = (Users) sqlQueryFindType.getSingleResult();

        SQLQuery sqlQueryUser = sessionFactory.getCurrentSession().createSQLQuery("update users set password=:password, userType=:userType, username=:username where id=:id");
        sqlQueryUser.setParameter("username", userDto.getUsername());
        sqlQueryUser.setParameter("password", userDto.getPassword());
        sqlQueryUser.setParameter("userType", userDto.getUserType().getDescription());
        sqlQueryUser.setParameter("id", userDto.getId());
        sqlQueryUser.executeUpdate();

        if (users.getUserType().getDescription() == "ACENTA"){

            SQLQuery sqlQueryDelete = sessionFactory.getCurrentSession().createSQLQuery("delete from acentaUser where id =:userId");
            sqlQueryDelete.setParameter("userId", userDto.getUserId());
            sqlQueryDelete.executeUpdate();

            SQLQuery sqlQueryInsert = sessionFactory.getCurrentSession().createSQLQuery("insert into admin(adminName, UserID) values (:adminName, :userId)");
            sqlQueryInsert.setParameter("adminName", userDto.getRoleName());
            sqlQueryInsert.setParameter("userId", userDto.getId());
            sqlQueryInsert.executeUpdate();

        }
        else if (users.getUserType().getDescription() == "ADMIN"){

            SQLQuery sqlQueryAdmin = sessionFactory.getCurrentSession().createSQLQuery("update admin set adminName=:adminName where userId=:userId");
            sqlQueryAdmin.setParameter("adminName", userDto.getRoleName());
            sqlQueryAdmin.setParameter("userId", userDto.getId());
            sqlQueryAdmin.executeUpdate();

        }




    }
}
