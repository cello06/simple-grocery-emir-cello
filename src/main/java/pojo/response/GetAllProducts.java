package pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProducts {

    private Integer id;

    private String category;

    private String name;

    private Boolean inStock;
}
