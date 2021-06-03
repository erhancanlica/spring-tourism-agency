package bmmf.turzimProje.service;

import bmmf.turzimProje.dao.StaffDao;
import bmmf.turzimProje.model.AcentaUser;
import bmmf.turzimProje.model.Staff;
import bmmf.turzimProje.model.dto.GeneralResponse;
import bmmf.turzimProje.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffService {

    @Autowired
    private StaffDao staffDao;

    public List<Staff> findAllStaff(AcentaUser acentaUser){
        return staffDao.findAllStuff(acentaUser);
    }

    public GeneralResponse save(Staff staff, AcentaUser acentaUser){
        GeneralResponse response = GeneralResponse.builder().build();
        try {
            staffDao.insert(staff, acentaUser);
            response.setResult(0);
            response.setMessage(Constants.success);
        } catch (Exception e){
            response.setResult(1);
            response.setMessage(Constants.err);
        }
       return response;
    }

    public GeneralResponse delete(Long id, AcentaUser acentaUser){
        GeneralResponse response = GeneralResponse.builder().build();
        try {
            staffDao.delete(id, acentaUser);
            response.setResult(0);
            response.setMessage(Constants.success);
        } catch (Exception e){
            response.setResult(1);
            response.setMessage(Constants.err);
        }
        return response;
    }

    public GeneralResponse update(Staff staff, AcentaUser acentaUser){
        GeneralResponse response = GeneralResponse.builder().build();
        try {
            staffDao.update(staff, acentaUser);
            response.setResult(0);
            response.setMessage(Constants.success);
        } catch (Exception e){
            response.setResult(1);
            response.setMessage(Constants.err);
        }
        return response;
    }
}
