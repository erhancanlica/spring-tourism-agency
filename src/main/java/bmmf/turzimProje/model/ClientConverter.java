package bmmf.turzimProje.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ClientConverter {

    public List<Client> toModelList(List<Object[]> objects){
        List<Client> clients = new ArrayList<>();
        objects.forEach(object -> {
            BigDecimal bigDecimal = (BigDecimal) object[0];
         clients.add(
                 Client
                         .builder()
                         .id(bigDecimal.longValue())
                         .name((String) object[3])
                         .surname((String) object[5])
                         .phone((String) object[4])
                         .address((String) object[1])
                         .email((String) object[2])
                         .build()
         );
        });
        return clients;
    }
}
