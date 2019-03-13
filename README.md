# IntroHelper

# Step 1. Add the JitPack repository to your build file 

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  # Step 2. Add the dependency
  
  	dependencies {
	        implementation 'com.github.KORUSH-KABIR:Library:2.0.6'
	}
	

/////////////////////////////////////


# 1. Insert xml:

    <korush.kabir.IntroHelper
        android:id="@+id/intro"
        app:ihRightText="Next"
        app:ihLeftText="Skip"
        app:ihLeftTextColor="#fff"
        app:ihRightTextColor="#fff"
        app:ihLineColor="#fff"
        app:ihSelectedDrawable="@drawable/shape"
        app:ihUnselectedDrawable="@drawable/shape_outline"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

# 2. Sample Single Page:

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_intro);

        final IntroHelper introHelper = findViewById(R.id.intro);
        viewPager = introHelper.getIntroViewPager();

        final int[] pics = {
                R.drawable.icon_settings,
                R.drawable.icon_settings,
                R.drawable.icon_settings,
                R.drawable.icon_settings
        };

        final String texts[] = {
                "this is a test a",
                "this is a test b",
                "this is a test c",
                "this is a test d"
        };

        introHelper.setLayout(new IntroHelper.OnLayoutSetItems() {
            @Override
            public void onLayouts(View view, int position) {

                int finalLength = introHelper.getPagesLength();

                if(position != finalLength){
                    introHelper.setRightText("Next");
                }
                else {
                    introHelper.leftTextViewHide();
                    introHelper.setRightText("Skip");
                }

                TextView toolbar = view.findViewById(R.id.toolbar);
                ImageView img = view.findViewById(R.id.img);
                TextView txt = view.findViewById(R.id.txt);

                toolbar.setText(texts[position]);
                txt.setText(texts[position]);
                img.setImageDrawable(getResources().getDrawable(pics[position]));
            }
        } , 4 , R.layout.single_intro);

        introHelper.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem();
                int position = current + 1;
                viewPager.setCurrentItem(position);
            }
        });

        introHelper.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        introHelper.build();
    }

# 2. Sample Multi Page:

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_intro);

        final IntroHelper introHelper = findViewById(R.id.intro);
        viewPager = introHelper.getIntroViewPager();

        introHelper.setLayouts(new IntroHelper.OnLayoutSetItems() {
            @Override
            public void onLayouts(View view, int position) {

                if(viewPager.getCurrentItem() < 2){
                    introHelper.setRightText("Next");
                }
                else {
                    introHelper.leftTextViewHide();
                    introHelper.setRightText("Skip");
                }
            }
        } , R.layout.multi_intro_1 , R.layout.multi_intro_2 , R.layout.multi_intro_3);

        introHelper.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem();
                int position = current + 1;
                viewPager.setCurrentItem(position);
            }
        });

        introHelper.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        introHelper.build();
    }
