package com.example.myapplication;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;

public class MainActivity extends FragmentActivity implements OnClickListener,
        OnPageChangeListener {

    /**
     * ViewPager 如其名所述，是负责翻页的一个 View。准确说是一个 ViewGroup，
     * 包含多个 View 页，在手指横向滑动屏幕时，其负责对 View 进行切换。
     * 为了生成这些 View 页，需要提供一个 PagerAdapter 来进行和数据绑定以及生成最终的 View 页。
     嘎嘎
     */
    ViewPager mViewPager;
    /**
     * FragmentPagerAdapter是PagerAdapter中的其中一种实现
     */
    FragmentPagerAdapter mAdapter;
//	FragmentStatePagerAdapter mAdapter;

    /**
     * 存放Fragment的集合
     */
    List<Fragment> mTabs = new ArrayList<Fragment>();

    /**
     * 在Fragment中显示的文字
     */
    String[] mTitles = new String[] { "first Fragment !", "Second Fragment !","Third Fragment !" };

    /**
     * 界面底下小图片集合
     */
    List<ChangeColorText> mTabIndicators = new ArrayList<ChangeColorText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //如果ActionBar右边的设置图标不显示，取消以下注释
        // setOverflowButtonAlways();
        // 不显示action bar icon图标
        // getActionBar().setDisplayShowHomeEnabled(false);

        initView();
        initDatas();
        mViewPager.setAdapter(mAdapter);  //设置Adapter，则每一页显示相应View
        initEvent();
    }

    /** 初始化所有事件 */
    private void initEvent() {
        //ViewPager在处理滑动事件的时候要用到OnPageChangeListener
        mViewPager.setOnPageChangeListener(this);
    }

    private void initDatas() {
//		//循环遍历mTitles里面的元素，每个元素的值放入title中。
//		for (String title : mTitles) {
//			TabFragment tabFragment = new TabFragment();
//			Bundle bundle = new Bundle();
//			bundle.putString("title", title);
//			tabFragment.setArguments(bundle);  //调用Fragment的setArgument方法将Bundle数据包传给Fragment
//			mTabs.add(tabFragment);
//		}

        //页面1
        Fragment_hk Fragment_01 = new Fragment_hk();
        mTabs.add(Fragment_01);
        Fragment_two Fragment_02 = new Fragment_two();
        mTabs.add(Fragment_02);
        Fragment_three Fragment_03 = new Fragment_three();
        mTabs.add(Fragment_03);
        Fragment_four Fragment_04 = new Fragment_four();
        mTabs.add(Fragment_04);



//		mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()){
//			@Override
//			public int getCount() {
//				return mTabs.size();
//			}
//			@Override
//			public Fragment getItem(int position) {
//				return mTabs.get(position);
//			}
//		};

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()){
            @Override
            public int getCount() {
                return mTabs.size();
            }
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }
        };


    }

    private void initView() {
        mViewPager = (ViewPager)findViewById(R.id.vp);

        ChangeColorText one = (ChangeColorText) findViewById(R.id.id_indicator_one);
        ChangeColorText two = (ChangeColorText) findViewById(R.id.id_indicator_two);
        ChangeColorText three = (ChangeColorText) findViewById(R.id.id_indicator_three);
        ChangeColorText four = (ChangeColorText) findViewById(R.id.id_indicator_four);

        mTabIndicators.add(one);
        mTabIndicators.add(two);
        mTabIndicators.add(three);
        mTabIndicators.add(four);

        //循环遍历mTabIndicators里面的元素，每个元素的值放入item中。
        for (View item : mTabIndicators) {
            item.setOnClickListener(this);
        }

        one.setIconAlpha(1.0f);  //将第一个颜色加深
    }

    /**
     * 长按菜单键 或 界面右上角的 + 号键 出现的菜单
     */


    @SuppressWarnings("unused")
    private void setOverflowButtonAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKey = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*修改menu显示icon*/
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }


    @Override
    public void onClick(View v) {
        if (v instanceof ChangeColorText) {

            resetOtherTabs();   //将所有的Tab设为透明
            ChangeColorText current = (ChangeColorText)v;  //将特定的Tab颜色加深
            current.setIconAlpha(1.0f);

            //设置当前显示的页面
            mViewPager.setCurrentItem(mTabIndicators.indexOf(current),false);
        }
    }

    /**
     * 重置其他的TabIndicator的颜色 ，设为透明
     *
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }




    /**
     * onPageScrolled(int arg0,float arg1,int arg2)，当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用。
     * 其中三个参数的含义分别为：
     * arg0 :当前页面，及你点击滑动的页面
     * arg1:当前页面偏移的百分比
     * arg2:当前页面偏移的像素位置
     */
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

        if (positionOffset > 0) {
            ChangeColorText left = mTabIndicators.get(position);
            ChangeColorText right = mTabIndicators.get(position + 1);

            //切换时，图标的透明度转变
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);

            // 以下代码是切换Fragment中View的透明度，不需要可以注释掉
            mTabs.get(position).getView().setAlpha(1 - positionOffset);
            mTabs.get(position + 1).getView().setAlpha(positionOffset);
        }
    }

    /**
     * 此方法是在状态改变的时候调用，其中arg0这个参数
     * 有三种状态（0，1，2）。arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
     * 当页面开始滑动的时候，三种状态的变化顺序为（1，2，0）
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    /**
     * onPageSelected(int arg0) ：
     * 此方法是页面跳转完后得到调用，arg0是你当前选中的页面的Position（位置编号）
     */
    @Override
    public void onPageSelected(int arg0) {
    }
}