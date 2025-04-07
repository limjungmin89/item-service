package hello.item_service.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : hello.item_service.domain
 * fileName       : Item
 * author         : mzc01-jungminim
 * date           : 2025. 4. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 4. 7.        mzc01-jungminim       최초 생성
 */
@Getter
@Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName= itemName;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
