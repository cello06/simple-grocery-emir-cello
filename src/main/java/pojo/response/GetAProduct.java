package pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAProduct {
    private Integer id;
    private String category;
    private String name;
    private String manufacturer;
    private Double price;

    @JsonProperty("current-stock")
    private Integer currentStock;
    private Boolean inStock;
}
