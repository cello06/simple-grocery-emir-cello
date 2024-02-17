package pojo.request;

import lombok.Data;

@Data
public class CreateOrder {
    private String customerName;
    private String cartId;
}
