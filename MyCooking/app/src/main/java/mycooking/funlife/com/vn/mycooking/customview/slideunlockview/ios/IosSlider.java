package mycooking.funlife.com.vn.mycooking.customview.slideunlockview.ios;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import mycooking.funlife.com.vn.mycooking.R;


public class IosSlider extends FrameLayout {

    private Paint mTextPaint;

    private int mPaddingRight;
    private Rect mBounds = new Rect();

    private String mTextToBeDrawn;

    private int colorBg = Color.GRAY;
    private int colorShow = Color.WHITE;

    LinearGradient[] mShaders;
    int mIndex = 0;

    boolean mActive = false;

    public IosSlider(Context context) {
        super(context);
        init();
    }

    public IosSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IosSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setTextIosSlider(String txt){
        this.mTextToBeDrawn = txt;
    }
    private void init() {
        setBackgroundResource(R.drawable.ios_back);
//        mTextToBeDrawn = getContext().getString(R.string.ios_text);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.textsize_medium));

        mPaddingRight = getResources().getDimensionPixelSize(R.dimen.margin_zezo);


        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                initShaders();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }


    public void setColor(int colorBg, int colorShow){
        this.colorBg = colorBg;
        this.colorShow = colorShow;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive = true;
        postDelayed(mRunnable, 100);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActive = false;
        removeCallbacks(mRunnable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mTextPaint.getTextBounds(mTextToBeDrawn, 0, mTextToBeDrawn.length(), mBounds);
        int startX = getWidth() - mBounds.width() - mPaddingRight;
        int startY = (int) (getHeight() - mTextPaint.descent() - mTextPaint.ascent()) / 2;
        if (mShaders.length > 0) {
            mTextPaint.setShader(mShaders[mIndex]);
        }
        canvas.drawText(mTextToBeDrawn, startX, startY, mTextPaint);
    }

    public void initShaders() {
        final int COUNT = 100;
        mShaders = new LinearGradient[COUNT];
        float stepX = getWidth() * 3 / COUNT;
        int[] colors = new int[] { colorBg, colorShow, colorBg};
        for (int i = 0; i < COUNT; i++) {
            mShaders[i] = new LinearGradient(stepX * i, 0, stepX * (i + 6), 0, colors, null, Shader.TileMode.REPEAT);
        }
    }


    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mIndex++;
            if (mIndex >= mShaders.length) {
                mIndex = 0;
            }
            invalidate();
            if (mActive) {
                postDelayed(mRunnable, 70);
            }
        }
    };
}
