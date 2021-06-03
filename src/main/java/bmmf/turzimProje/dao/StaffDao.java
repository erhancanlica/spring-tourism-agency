package bmmf.turzimProje.dao;

import bmmf.turzimProje.model.AcentaUser;
import bmmf.turzimProje.model.Staff;
import bmmf.turzimProje.model.Users;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Staff> findAllStuff(AcentaUser acentaUser){
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select * from staff where acenta_ID=:id and status=1");
        sqlQuery.setParameter("id",acentaUser.getId());
        sqlQuery.addEntity(Staff.class);
        return sqlQuery.list();
    }

    public void insert(Staff staff, AcentaUser acentaUser){
        String query = "insert into staff(firstname, job, lastname, price ,acenta_ID, status) values(:firstname, :job, :lastname, :price,  :acentaId, :status)";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("firstname",staff.getFirstName());
        sqlQuery.setParameter("job",staff.getJob());
        sqlQuery.setParameter("lastname",staff.getLastName());
        sqlQuery.setParameter("price",staff.getPrice());
        sqlQuery.setParameter("acentaId",acentaUser.getId());
        sqlQuery.setParameter("status", true);
        sqlQuery.executeUpdate();
    }

    public void delete(Long id, AcentaUser acentaUser){
        String query = "delete from vw_staff where id=:id and acenta_ID=:aid";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("id",id);
        sqlQuery.setParameter("aid",acentaUser.getId());
        sqlQuery.executeUpdate();
    }

    public void update(Staff staff, AcentaUser acentaUser){
        String query = "update staff set firstname=:firstname, job=:job, lastname=:lastname, price=:price where id=:id and acenta_ID=:acentaId";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("firstname",staff.getFirstName());
        sqlQuery.setParameter("job",staff.getJob());
        sqlQuery.setParameter("lastname",staff.getLastName());
        sqlQuery.setParameter("price",staff.getPrice());
        sqlQuery.setParameter("acentaId",acentaUser.getId());
        sqlQuery.setParameter("id",staff.getId());
        sqlQuery.executeUpdate();
    }
}
