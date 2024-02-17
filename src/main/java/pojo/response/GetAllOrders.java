package pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrders {
    private String id;

    private GetItems[] items;

    private String customerName;

    private String created;

    private String comment;
}
