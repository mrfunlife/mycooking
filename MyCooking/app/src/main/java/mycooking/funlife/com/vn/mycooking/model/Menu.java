package mycooking.funlife.com.vn.mycooking.model;

import android.content.Context;
import android.content.SharedPreferences;

import vn.com.funlife.ApplicationController;

/**
 * Created by funlife on 8/29/17.
 */

public class Menu {
    private static final String STORAGE = "MENU";
    public  static Menu get(){
        return new Menu();
    }

    private SharedPreferences storage;

    private Menu(){
        storage = ApplicationController.getInstance().getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }

//    public  List<Item> getData(Context context){
//        return Arrays.asList(
//                new Item(1,context.getString(R.string.title_menu),context.getString(R.string.fake_data_menu),R.drawable.shop1),
//                new Item(2,context.getString(R.string.title_menu),context.getString(R.string.fake_data_menu),R.drawable.shop2),
//                new Item(3,context.getString(R.string.title_menu),context.getString(R.string.fake_data_menu),R.drawable.shop3),
//                new Item(4,context.getString(R.string.title_menu),context.getString(R.string.fake_data_menu),R.drawable.shop4),
//                new Item(5,context.getString(R.string.title_menu),context.getString(R.string.fake_data_menu),R.drawable.shop5),
//                new Item(6,context.getString(R.string.title_menu),context.getString(R.string.fake_data_menu),R.drawable.shop6)
//        );
//    }

    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}
