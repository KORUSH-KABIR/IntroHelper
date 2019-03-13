package korush.kabir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

final class IntroAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private OnInstantiateItem onInstantiateItem;

    IntroAdapter(Context context , OnInstantiateItem onInstantiateItem) {
        this.onInstantiateItem = onInstantiateItem;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return onInstantiateItem.layoutSize();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = onInstantiateItem.createView(layoutInflater,container,position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    interface OnInstantiateItem {
        View createView(LayoutInflater layoutInflater , ViewGroup container , int position);
        int layoutSize();
    }
}
