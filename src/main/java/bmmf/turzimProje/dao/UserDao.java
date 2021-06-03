package bmmf.turzimProje.dao;

import bmmf.turzimProje.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Slf4j
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Users findByUserName(String userName){
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select * from users where username=:name");
        sqlQuery.setParameter("name", userName);
        sqlQuery.addEntity(Users.class);
        Users user = null;
        try {
            user = (Users) sqlQuery.getSingleResult();
        } catch (NoResultException nre){
            log.info("user not found");
        }
        return user;
    }
}
