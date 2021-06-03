package bmmf.turzimProje.dao;

import bmmf.turzimProje.model.Client;
import bmmf.turzimProje.model.ClientConverter;
import bmmf.turzimProje.model.dto.ClientFilterRequest;
import bmmf.turzimProje.model.dto.ClientTourDto;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ClientConverter clientConverter;

    public Client findByPhone(String phone) {
        String query = "select * from client where phone=:phone";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("phone", phone);
        sqlQuery.addEntity(Client.class);
        return (Client) sqlQuery.getSingleResult();
    }

    public List<Client> findAllClient(ClientFilterRequest clientFilterRequest){
        List<Object[]> reponse = new ArrayList<>();
        if(StringUtils.isNotEmpty(clientFilterRequest.getProperty()) && StringUtils.isNotEmpty(clientFilterRequest.getValue())){
            StoredProcedureQuery query = sessionFactory.getCurrentSession().createStoredProcedureCall("clientFilterProcedure")
                    .registerStoredProcedureParameter(1, String.class,
                            ParameterMode.IN)
                    .registerStoredProcedureParameter(2, String.class,
                            ParameterMode.IN)
                    .registerStoredProcedureParameter(3,Class.class,ParameterMode.REF_CURSOR)
                    .setParameter(1, clientFilterRequest.getProperty())
                    .setParameter(2,clientFilterRequest.getValue().toLowerCase());
            reponse = query.getResultList();
        } else {
            StoredProcedureQuery query = sessionFactory.getCurrentSession().createStoredProcedureCall("clientSelectProcedure")
                    .registerStoredProcedureParameter(1,Class.class,ParameterMode.REF_CURSOR);
            reponse = query.getResultList();
        }

        return clientConverter.toModelList(reponse);
    }

    public List<Client> findTourClient(Long id) {
        String query = "select * from client c INNER JOIN sales s on s.client_id=c.id where s.tour_id=:tour_id";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("tour_id", id);
        sqlQuery.addEntity(Client.class);
        return sqlQuery.list();
    }

    public long insert(ClientTourDto client) {
        Session session = sessionFactory.getCurrentSession();
        BigDecimal result =  (BigDecimal) session.createSQLQuery("select client_seq.nextVal from dual").getSingleResult();
        long clientId = result.longValue();

        String query = "insert into client(id, address, email, name, phone, surname) values(:id,:address, :email, :name, :phone, :surname)";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("address", client.getAddress());
        sqlQuery.setParameter("email", client.getEmail());
        sqlQuery.setParameter("name", client.getName());
        sqlQuery.setParameter("phone", client.getPhone());
        sqlQuery.setParameter("id", clientId);
        sqlQuery.setParameter("surname", client.getSurname());
        sqlQuery.setParameter("address", client.getAddress());
        sqlQuery.executeUpdate();

        return clientId;
    }

    public void delete(Long id) {
        String query = "delete from client where id=:id";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("id", id);
        sqlQuery.executeUpdate();
    }

    public void update(Client client) {
        String query = "update client set name=:name, surname=:surname, email=:email, phone=:phone, address=:address where id=:id";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("address", client.getAddress());
        sqlQuery.setParameter("email", client.getEmail());
        sqlQuery.setParameter("name", client.getName());
        sqlQuery.setParameter("phone", client.getPhone());
        sqlQuery.setParameter("surname", client.getSurname());
        sqlQuery.setParameter("address", client.getAddress());
        sqlQuery.setParameter("id", client.getId());
        sqlQuery.executeUpdate();
    }

}
