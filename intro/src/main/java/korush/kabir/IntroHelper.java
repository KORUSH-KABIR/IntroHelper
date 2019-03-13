package korush.kabir;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public final class IntroHelper extends RelativeLayout {

    private Context context;
    private ViewPager viewPager;
    private View line;
    private RelativeLayout layoutBottomBar;
    private TextView txtLeft , txtRight;
    private LinearLayout layoutDots;
    private String ihRightText = null;
    private String ihLeftText = null;
    private int ihLeftTextColor;
    private int ihRightTextColor;
    private int ihLineColor;
    private Drawable ihSelectedDrawable;
    private Drawable ihUnselectedDrawable;
    private int layoutDotsLength;
    private int[] layouts = null;
    private int layout = 0;
    private OnLayoutSetItems onLayoutSetItems;

    public IntroHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs){

        View root = inflate(context, R.layout.intro_helper, this);
        initViews(root);
        initObjectValue(attrs);
        initDefaultValueXml();
    }

    private void initViews(View view){
        this.viewPager       = view.findViewById(R.id.viewPager);
        this.line            = view.findViewById(R.id.line);
        this.layoutBottomBar = view.findViewById(R.id.layoutBottomBar);
        this.txtLeft         = view.findViewById(R.id.txtLeft);
        this.txtRight        = view.findViewById(R.id.txtRight);
        this.layoutDots      = view.findViewById(R.id.layoutDots);
    }

    private void initObjectValue(AttributeSet attrs){
        TypedArray typedArray      = context.getTheme().obtainStyledAttributes(attrs, R.styleable.intro ,0 , 0);
        this.ihRightText           = typedArray  .getString(R.styleable.intro_ihRightText);
        this.ihLeftText            = typedArray  .getString(R.styleable.intro_ihLeftText);
        this.ihLeftTextColor       = typedArray   .getColor(R.styleable.intro_ihLeftTextColor,0);
        this.ihRightTextColor      = typedArray   .getColor(R.styleable.intro_ihRightTextColor,0);
        this.ihLineColor           = typedArray   .getColor(R.styleable.intro_ihLineColor,0);
        this.ihSelectedDrawable    = typedArray.getDrawable(R.styleable.intro_ihSelectedDrawable);
        this.ihUnselectedDrawable  = typedArray.getDrawable(R.styleable.intro_ihUnselectedDrawable);
        typedArray.recycle();
    }

    private void initDefaultValueXml(){

        if(this.ihLeftText != null){
            if(!this.ihLeftText.trim().equals("")){
                this.txtLeft.setText(ihLeftText);
            }
        }

        if(this.ihLeftTextColor != 0){
            this.txtLeft.setTextColor(ihLeftTextColor);
        }

        if(this.ihRightText != null){
            if(!this.ihRightText.trim().equals("")){
                this.txtRight.setText(ihRightText);
            }
        }

        if(this.ihRightTextColor != 0){
            this.txtRight.setTextColor(ihRightTextColor);
        }

        if(this.ihLineColor != 0){
            this.line.setBackgroundColor(ihLineColor);
        }

        if(this.ihSelectedDrawable == null){
            this.ihSelectedDrawable = getResources().getDrawable(R.drawable.shape_circle);
        }

        if(this.ihUnselectedDrawable == null){
            this.ihUnselectedDrawable = getResources().getDrawable(R.drawable.shape_circle_outline);
        }
    }

    private void setCurrentPositionDots(int position){

        ImageView[] dots = new ImageView[layoutDotsLength];
        this.layoutDots.removeAllViews();
        for(int i = 0; i < layoutDotsLength; i++){
            dots[i] = new ImageView(context);
            int w_h = 18;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w_h , w_h);
            params.setMargins(5,5,5,5);
            dots[i].setLayoutParams(params);
            dots[i].setImageDrawable(ihUnselectedDrawable);
            this.layoutDots.addView(dots[i]);
        }
        dots[position].setImageDrawable(ihSelectedDrawable);
    }

    public void setLayouts(OnLayoutSetItems onLayoutSetItems , @LayoutRes int... layouts){
        this.onLayoutSetItems = onLayoutSetItems;
        this.layoutDotsLength = layouts.length;
        this.layouts = layouts;
    }

    public void setLayout(OnLayoutSetItems onLayoutSetItems , int layoutLength , @LayoutRes int layout){
        this.onLayoutSetItems = onLayoutSetItems;
        this.layoutDotsLength = layoutLength;
        this.layout = layout;
    }

    public void setOnLeftClickListener(OnClickListener onLeftClickListener){
        this.txtLeft.setOnClickListener(onLeftClickListener);
    }

    public void setOnRightClickListener(OnClickListener onRightClickListener){
        this.txtRight.setOnClickListener(onRightClickListener);
    }

    public void leftTextViewHide(){
        txtLeft.setVisibility(INVISIBLE);
    }

    public void leftTextViewShow(){
        txtLeft.setVisibility(VISIBLE);
    }

    public void rightTextViewHide(){
        txtRight.setVisibility(INVISIBLE);
    }

    public void rightTextViewShow(){
        txtRight.setVisibility(VISIBLE);
    }

    public void setLeftText(String text){
        this.txtLeft.setText(text);
    }

    public void setLeftTextColor(int color){
        this.txtLeft.setTextColor(color);
    }

    public void setRightText(String text){
        this.txtRight.setText(text);
    }

    public void setRightTextColor(int color){
        this.txtRight.setTextColor(color);
    }

    public void setLineColor(int color){
        this.line.setBackgroundColor(color);
    }

    public void setSelectedShape(@DrawableRes int shape){
        this.ihSelectedDrawable = getResources().getDrawable(shape);
    }

    public void setUnselectedShape(@DrawableRes int shape){
        this.ihUnselectedDrawable = getResources().getDrawable(shape);
    }

    public void setShowBottomBar(boolean show){
        if(show){
            this.layoutBottomBar.setVisibility(View.VISIBLE);
        }
        else {
            this.layoutBottomBar.setVisibility(View.GONE);
        }
    }

    public void setBottomBarBackground(int color){
        this.layoutBottomBar.setBackgroundColor(color);
    }

    public TextView getLeftTextView(){
        return txtLeft;
    }

    public TextView getRightTextView(){
        return txtRight;
    }

    public View getLine(){
        return line;
    }

    public ViewPager getIntroViewPager(){
        return this.viewPager;
    }

    public int getPagesLength(){

        if(layouts != null){
            return layouts.length;
        }
        else {
            return layoutDotsLength;
        }
    }

    public int getCurrentPageSelected(){
        return this.viewPager.getCurrentItem();
    }

    public interface OnLayoutSetItems {
        void onLayouts(View view , int position);
    }

    public void build(){

        setCurrentPositionDots(0);

        IntroAdapter adapter = new IntroAdapter(context, new IntroAdapter.OnInstantiateItem() {
            @Override
            public View createView(LayoutInflater layoutInflater , ViewGroup container , int position) {

                View view;

                if(layouts != null){
                    view = layoutInflater.inflate(layouts[position] , container , false);
                    onLayoutSetItems.onLayouts(view , position);
                }
                else {
                    view = layoutInflater.inflate(layout , container , false);
                    onLayoutSetItems.onLayouts(view , position);
                }
                return view;
            }

            @Override
            public int layoutSize() {
                if(layouts != null){
                    return layouts.length;
                }
                else{
                    return layoutDotsLength;
                }
            }
        });

        this.viewPager.setAdapter(adapter);

        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentPositionDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
