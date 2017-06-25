package mycooking.funlife.com.vn.mycooking.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by funlife on 6/25/17.
 */
@Setter
@Getter
public class DetailMenu {
    private String nameFood;
    private String materialFood;
    private String makingFood;
    private String noteFood;
    private Long detailFoodId;
    private Integer detailFoodStatus;

    public DetailMenu() {
    }
}
