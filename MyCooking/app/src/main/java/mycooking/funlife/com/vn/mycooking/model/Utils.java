package mycooking.funlife.com.vn.mycooking.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by student on 06/10/2017.
 */
@Setter
@Getter
public class Utils {
    private String name;
    private String img;


    public Utils(String name) {
        this.name = name;
    }
}
