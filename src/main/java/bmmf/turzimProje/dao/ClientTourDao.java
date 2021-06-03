package bmmf.turzimProje.dao;

import bmmf.turzimProje.model.dto.ClientTourDto;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientTourDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void  save(ClientTourDto clientTourDto, long clientId) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO sales VALUES (:tid, :cid)");
        sqlQuery.setParameter("tid", clientTourDto.getTourId());
        sqlQuery.setParameter("cid", clientId);
        sqlQuery.executeUpdate();
    }


    public void delete(Long clientId, Long tourId) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM sales WHERE tour_id=:tid and client_id=:cid");
        sqlQuery.setParameter("tid", tourId);
        sqlQuery.setParameter("cid", clientId);
        sqlQuery.executeUpdate();
    }
}
