package mycooking.funlife.com.vn.mycooking.customview.slideunlockview;

/*
 * Created by rom on 15.12.16.
 */

public interface ISlideChangeListener {

    void onSlideStart(SlideLayout slider);

    void onSlideChanged(SlideLayout slider, float percentage);

    void onSlideFinished(SlideLayout slider, boolean done);

}
