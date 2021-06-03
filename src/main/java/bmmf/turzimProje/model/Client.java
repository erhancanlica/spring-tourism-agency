package bmmf.turzimProje.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "client",schema = "turizm")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedNativeQuery(
        name = "callTestp",
        query = "CALL testp(:prop,:val)",
        resultClass = Client.class
)
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
    @SequenceGenerator(name = "client_id_seq",
            sequenceName = "turizm.client_seq",
            allocationSize = 1)
    private long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;

    @ManyToMany(mappedBy = "clients")
    private List<Tour> tours;
}
