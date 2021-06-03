package bmmf.turzimProje.model;

import bmmf.turzimProje.model.enums.TourType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tour",schema = "turizm")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_id_seq")
    @SequenceGenerator(name = "tour_id_seq",
            sequenceName = "turizm.tour_seq",
            allocationSize = 1)
    private long id;

    private int price;

    private String startDate;

    private String endDate;

    private String location;

    private int capasity;

    private String details;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private TourType tourType;

    @ManyToMany
    @JoinTable( name = "sales",
            joinColumns = { @JoinColumn(name = "tour_id")},
            inverseJoinColumns = { @JoinColumn(name = "client_id") })
    private List<Client> clients = new ArrayList<>();

    @ManyToMany
    @JoinTable( name = "personel_tour",
                joinColumns = { @JoinColumn(name = "tour_id")},
                inverseJoinColumns = { @JoinColumn(name = "staff_id") })
    private Set<Staff> staff = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "acenta_id")
    private AcentaUser acentaUser;
}
