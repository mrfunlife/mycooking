package mycooking.funlife.com.vn.mycooking.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by funlife on 9/6/17.
 */
@Getter
@Setter
public class HistoryMenu {
    private String imgMenu;
    private String titleMenu;
    private String dateUseMenu;
    private String listFood;

    public HistoryMenu() {
    }

    public HistoryMenu(String imgMenu, String titleMenu, String dateUseMenu, String listFood) {
        this.imgMenu = imgMenu;
        this.titleMenu = titleMenu;
        this.dateUseMenu = dateUseMenu;
        this.listFood = listFood;
    }
}
