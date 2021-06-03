package bmmf.turzimProje.dao;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class PersonelTourDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void  save(long tourId, long personelIds){
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO PERSONEL_TOUR values (:tid, :pid)");
        sqlQuery.setParameter("tid", tourId);
        sqlQuery.setParameter("pid", personelIds);
        sqlQuery.executeUpdate();
    }


}
