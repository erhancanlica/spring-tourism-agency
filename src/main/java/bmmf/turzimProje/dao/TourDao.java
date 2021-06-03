package bmmf.turzimProje.dao;

import bmmf.turzimProje.model.AcentaUser;
import bmmf.turzimProje.model.Client;
import bmmf.turzimProje.model.Tour;
import bmmf.turzimProje.model.Users;
import bmmf.turzimProje.model.dto.GeneralResponse;
import bmmf.turzimProje.model.dto.QueryParam;
import bmmf.turzimProje.model.dto.TourDto;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class TourDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Tour> findByAll(){

        return null;
    }

    public long insert (TourDto tour, AcentaUser acentaUser){
        Session session = sessionFactory.getCurrentSession();
        BigDecimal result =  (BigDecimal) session.createSQLQuery("select tour_seq.nextVal from dual").getSingleResult();
        long tourId = result.longValue();

        String query = "insert into tour(id, price, startDate, endDate, location,capasity, details, acenta_Id, tourtype) values(:id, :price, :startDate, :endDate, :location,:capasity, :details, :acentaId, :tourType)";
        SQLQuery sqlQuery = session.createSQLQuery(query);
        sqlQuery.setParameter("price",tour.getPrice());
        sqlQuery.setParameter("startDate",tour.getStartDate());
        sqlQuery.setParameter("endDate",tour.getEndDate());
        sqlQuery.setParameter("location",tour.getLocation());
        sqlQuery.setParameter("capasity",tour.getCapasity());
        sqlQuery.setParameter("id",tourId);
        sqlQuery.setParameter("details",tour.getDetails());
        sqlQuery.setParameter("acentaId",acentaUser.getId());
        sqlQuery.setParameter("tourType",tour.getTourType());

        sqlQuery.executeUpdate();
        return  tourId;
    }

    public List<Tour> findByTour(String query2, List<QueryParam> queryParams) {
        String query = "select * from turizm.tour where " + query2;
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(query);
        queryParams.forEach(s -> {
            System.out.println(s.getFieldName()+ " " + s.getValue());
            sqlQuery.setParameter(s.getFieldName(),s.getValue());
        });
        sqlQuery.addEntity(Tour.class);
        return (List<Tour>) sqlQuery.list();
    }


}
