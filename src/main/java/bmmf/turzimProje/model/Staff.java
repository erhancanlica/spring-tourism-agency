package bmmf.turzimProje.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff",schema = "turizm")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private long price;

    private String job;

    private boolean status;

    @ManyToMany(mappedBy = "staff")
    private Set<Tour> tours = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "acenta_id")
    private AcentaUser acentaUser;



}
