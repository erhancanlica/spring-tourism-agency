package bmmf.turzimProje.service;

import bmmf.turzimProje.dao.ClientDao;
import bmmf.turzimProje.dao.ClientTourDao;
import bmmf.turzimProje.model.Client;
import bmmf.turzimProje.model.Tour;
import bmmf.turzimProje.model.dto.ClientFilterRequest;
import bmmf.turzimProje.model.dto.ClientTourDto;
import bmmf.turzimProje.model.dto.GeneralResponse;
import bmmf.turzimProje.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ClientTourDao clientTourDao;

    public List<Client> findAllClient(ClientFilterRequest clientFilterRequest) {
        return clientDao.findAllClient(clientFilterRequest);
    }

    public List<Client> findTourClient(Long id) {
        return clientDao.findTourClient(id);
    }

    public GeneralResponse save(ClientTourDto client) {
        GeneralResponse generalResponse = GeneralResponse.builder().build();
        Client validClient = null;

        try {
            validClient = clientDao.findByPhone(client.getPhone());
        } catch(NoResultException nre ) {
            log.info("Client Not Found");
        } catch(Exception ex ) {
            log.info(ex.getMessage());
        }

        if (validClient == null){
            if (client.getTourId() != 0) {
                long clientId = clientDao.insert(client);
                clientTourDao.save(client, clientId);
                generalResponse.setResult(0);
                generalResponse.setMessage(Constants.success);
            } else {
                clientDao.insert(client);
                generalResponse.setResult(0);
                generalResponse.setMessage(Constants.success);
            }
        }

        else{
            List<Long> validClientTourIds =
                    validClient.getTours().stream().map(Tour::getId).collect(Collectors.toList());
            if (client.getTourId() != 0) {
                for (long id : validClientTourIds) {
                    if (id == client.getTourId()) {
                        generalResponse.setResult(1);
                        generalResponse.setMessage(Constants.valid);
                        return generalResponse;
                    }
                }
                clientTourDao.save(client, validClient.getId());
                generalResponse.setResult(0);
                generalResponse.setMessage(Constants.success);
            }
            else{
                generalResponse.setResult(1);
                generalResponse.setMessage(Constants.valid);
            }
        }

        return generalResponse;
    }


    public GeneralResponse delete(Long clientId, Long tourId) {
        GeneralResponse generalResponse = GeneralResponse.builder().build();
        try {
            if (tourId != null) {
                clientTourDao.delete(clientId, tourId);
                generalResponse.setResult(0);
                generalResponse.setMessage(Constants.success);
            } else {
                clientDao.delete(clientId);
                generalResponse.setResult(0);
                generalResponse.setMessage(Constants.success);
            }

        } catch (Exception e) {
            generalResponse.setResult(1);
            generalResponse.setMessage(Constants.err);
        }
        return generalResponse;
    }

    public GeneralResponse update(Client client) {
        GeneralResponse generalResponse = GeneralResponse.builder().build();
        try {
            clientDao.update(client);
            generalResponse.setResult(0);
            generalResponse.setMessage(Constants.success);
        } catch (Exception e) {
            generalResponse.setResult(1);
            generalResponse.setMessage(Constants.err);
        }
        return generalResponse;
    }

}
