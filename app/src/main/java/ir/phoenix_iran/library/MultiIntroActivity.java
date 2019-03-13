package ir.phoenix_iran.library;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import korush.kabir.IntroHelper;

public class MultiIntroActivity extends AppCompatActivity {

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

                /*
                 * any code
                 */
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
}
