package bmmf.turzimProje.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryParam {
    private String fieldName;
    private Object value;
    private Class<?>type;
}
