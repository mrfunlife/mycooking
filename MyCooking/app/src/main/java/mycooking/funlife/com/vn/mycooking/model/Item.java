package mycooking.funlife.com.vn.mycooking.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by funlife on 8/29/17.
 */
@Setter
@Getter
public class Item {
    private int id;
    private String title;
    private String body;
    private int image;
    private boolean starSave;

    public Item(int id, String title ,String body,int image,boolean starSave) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.body = body;
        this.starSave = starSave;
    }

    public Item() {
    }

}
