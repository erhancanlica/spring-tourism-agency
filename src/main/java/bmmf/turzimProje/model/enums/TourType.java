package bmmf.turzimProje.model.enums;

import lombok.Getter;
import lombok.Setter;

public enum  TourType {
    GEZİ("GEZİ"),
    TEKNE("TEKNE"),
    PARASUT("PARASUT"),
    JEEPSAFARI("JEEPSAFARI");


    TourType(String name) {
        this.name = name;
    }

    @Getter @Setter
    String name;
}
