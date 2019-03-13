package ir.phoenix_iran.library;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import korush.kabir.IntroHelper;

public class SingleIntroActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private int[] pics = {
            R.drawable.icon_settings,
            R.drawable.icon_settings,
            R.drawable.icon_settings,
            R.drawable.icon_settings
    };

    private String texts[] = {
            "this is a test a",
            "this is a test b",
            "this is a test c",
            "this is a test d"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_intro);

        final IntroHelper introHelper = findViewById(R.id.intro);
        viewPager = introHelper.getIntroViewPager();

        introHelper.setLayout(new IntroHelper.OnLayoutSetItems() {
            @Override
            public void onLayouts(View view, int position) {

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
}
