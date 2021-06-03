package bmmf.turzimProje.dao;

import bmmf.turzimProje.model.AcentaUser;
import bmmf.turzimProje.model.Admins;
import bmmf.turzimProje.model.Users;
import bmmf.turzimProje.model.dto.CreateUserDto;
import bmmf.turzimProje.model.dto.GeneralResponse;
import bmmf.turzimProje.model.enums.UserType;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Repository
public class AcentaUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public AcentaUser findByUser(Users user) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select au.id,au.acentaName,au.UserID from acentauser au  join users u on au.UserID=u.id where u.id=:userId");
        sqlQuery.setParameter("userId", user.getId());
        sqlQuery.addEntity(AcentaUser.class);
        return (AcentaUser) sqlQuery.getSingleResult();
    }

    public void createAcenteUser(CreateUserDto userDto){
        Session session = sessionFactory.getCurrentSession();
        BigDecimal result =  (BigDecimal) session.createSQLQuery("select user_seq.nextVal from dual").getSingleResult();
        int userID = result.intValue();
        SQLQuery sqlQuery = session.createSQLQuery("INSERT INTO users values (:id, :password, :userType, :username)");
        sqlQuery.setParameter("password", userDto.getPassword());
        sqlQuery.setParameter("userType", userDto.getUserType().getDescription());
        sqlQuery.setParameter("username", userDto.getUsername());
        sqlQuery.setParameter("id", userID);
        sqlQuery.executeUpdate();

        SQLQuery sqlQuery2 = session.createSQLQuery("INSERT INTO acentauser(acentaName, UserID) values (:acentaName, :userId)");
        sqlQuery2.setParameter("acentaName", userDto.getRoleName());
        sqlQuery2.setParameter("userId",userID);
        sqlQuery2.executeUpdate();
    }


    public void deleteAcenta(CreateUserDto userDto){

        Long userId = userDto.getId();
        Long acentaId = userDto.getUserId();

        SQLQuery sqlQueryAcenta = sessionFactory.getCurrentSession().createSQLQuery("delete from acentaUser where id=:acentaId");
        sqlQueryAcenta.setParameter("acentaId", acentaId);
        sqlQueryAcenta.executeUpdate();

        SQLQuery sqlQueryUser = sessionFactory.getCurrentSession().createSQLQuery("delete from users where id=:userId");
        sqlQueryUser.setParameter("userId", userId);
        sqlQueryUser.executeUpdate();

    }

    public void updateUserAcenta(CreateUserDto userDto) {

        SQLQuery sqlQueryFindType = sessionFactory.getCurrentSession().createSQLQuery("select * from users where id=:id").addEntity(Users.class);
        sqlQueryFindType.setParameter("id", userDto.getId());
        Users users = (Users) sqlQueryFindType.getSingleResult();

        SQLQuery sqlQueryUser = sessionFactory.getCurrentSession().createSQLQuery("update users set  password=:password, userType=:userType, username=:username where id=:id");
        sqlQueryUser.setParameter("username", userDto.getUsername());
        sqlQueryUser.setParameter("password", userDto.getPassword());
        sqlQueryUser.setParameter("userType", userDto.getUserType().getDescription());
        sqlQueryUser.setParameter("id", userDto.getId());
        sqlQueryUser.executeUpdate();


        if (users.getUserType().getDescription() == "ADMIN"){

            SQLQuery sqlQueryDelete = sessionFactory.getCurrentSession().createSQLQuery("delete from admin where id=:userId");
            sqlQueryDelete.setParameter("userId", userDto.getUserId());
            sqlQueryDelete.executeUpdate();

            SQLQuery sqlQueryInsert = sessionFactory.getCurrentSession().createSQLQuery("insert into acentauser(acentaName, UserID) values (:acentaName, :userId)");
            sqlQueryInsert.setParameter("acentaName", userDto.getRoleName());
            sqlQueryInsert.setParameter("userId", userDto.getId());
            sqlQueryInsert.executeUpdate();

        }
        else if (users.getUserType().getDescription()  == "ACENTA"){

            SQLQuery sqlQueryAdmin = sessionFactory.getCurrentSession().createSQLQuery("update acentaUser set acentaName=:acentaName where userId=:userId");
            sqlQueryAdmin.setParameter("acentaName", userDto.getRoleName());
            sqlQueryAdmin.setParameter("userId", userDto.getId());
            sqlQueryAdmin.executeUpdate();

        }




    }
}
