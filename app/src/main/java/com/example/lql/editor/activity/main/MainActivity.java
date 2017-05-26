package com.example.lql.editor.activity.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lql.editor.R;
import com.example.lql.editor.fragment.MainFragment;
import com.example.lql.editor.fragment.MeFragment;
import com.example.lql.editor.fragment.ServiceFragment;
import com.example.lql.editor.fragment.StudioFragment;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;


public class MainActivity extends FragmentActivity implements OnClickListener {


//	private FragmentManager manager;
//	FragmentTransaction transaction;




    private FrameLayout fragmently;

    MainFragment mMainFragment;
    ServiceFragment mServiceFragment;
    StudioFragment mStudioFragment;
    MeFragment mMeFragment;

    public static LinearLayout mainly;
    public  static LinearLayout servicely;
    public static LinearLayout studioly;
    public static LinearLayout mely;

    ImageView mainimg;
    ImageView serviceimg;
    ImageView studioimg;
    ImageView meimg;

    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_main2);
        initView();

        manager = this.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        showFragment(0, transaction);
        transaction.commit();
//        MyStatusBarUtil.setStatusTransparent(MainActivity.this,false);
//		PublicStaticData.MainFragmentNmuber = 0;
//		setView();
        registBroadcastReceiver();
    }


    @Override
    protected void onResume() {
        setView();
        super.onResume();
    }

    // 初始化 实例化控件、设置监听
    private void initView() {
        this.fragmently = (FrameLayout) findViewById(R.id.fragment_ly);
        this.mely = (LinearLayout) findViewById(R.id.me_ly);
        this.studioly = (LinearLayout) findViewById(R.id.studio_ly);
        this.servicely = (LinearLayout) findViewById(R.id.service_ly);
        this.mainly = (LinearLayout) findViewById(R.id.main_ly);


        mainimg = (ImageView) findViewById(R.id.main_img);
        serviceimg = (ImageView) findViewById(R.id.service_img);
        studioimg = (ImageView) findViewById(R.id.studio_img);
        meimg = (ImageView) findViewById(R.id.me_img);


        this.mainly.setOnClickListener(this);
        this.servicely.setOnClickListener(this);
        this.studioly.setOnClickListener(this);
        this.mely.setOnClickListener(this);

        // 点击事件
        mainly.setOnClickListener(this);
        servicely.setOnClickListener(this);
        studioly.setOnClickListener(this);
        mely.setOnClickListener(this);
    }

    // 点击事件 点击相应的fragment 显示相应的页面
    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (v.getId()) {
            // 首页
            case R.id.main_ly:
                showFragment(0, transaction);
                break;
            case R.id.service_ly:
                showFragment(1, transaction);
                break;
            case R.id.studio_ly:
                showFragment(2, transaction);
                break;
            case R.id.me_ly:
                showFragment(3, transaction);
                break;
        }
        transaction.commit();
    }

    // 隐藏Fragment
    public void hideFragment(FragmentTransaction transaction) {
        if (mMainFragment != null) {
            transaction.hide(mMainFragment);
        }
        if (mServiceFragment != null) {
            transaction.hide(mServiceFragment);
        }
        if (mStudioFragment != null) {
            transaction.hide(mStudioFragment);
        }
        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
    }


    // 显示Fragment
    public void showFragment(int num, FragmentTransaction transaction) {
        switch (num) {
            case 0:
                PublicStaticData.MainFragmentNmuber = 0;
                if (mMainFragment != null) {
                    transaction.show(mMainFragment);
                } else {
                    mMainFragment = new MainFragment();
                    transaction.add(R.id.fragment_ly2, mMainFragment);
                }
                setViewBottom();
                break;
            case 1:
                PublicStaticData.MainFragmentNmuber = 1;
                if (mServiceFragment != null) {
                    transaction.show(mServiceFragment);
                } else {
                    mServiceFragment = new ServiceFragment();
                    transaction.add(R.id.fragment_ly2, mServiceFragment);
                }
                setViewBottom();
                break;
            case 2:
                PublicStaticData.MainFragmentNmuber = 2;
                if (mStudioFragment != null) {
                    transaction.show(mStudioFragment);
                } else {
                    mStudioFragment = new StudioFragment();
                    transaction.add(R.id.fragment_ly2, mStudioFragment);
                }
                setViewBottom();
                break;
            case 3:
                PublicStaticData.MainFragmentNmuber = 3;
                if (mMeFragment != null) {
                    transaction.show(mMeFragment);
                } else {
                    mMeFragment = new MeFragment();
                    transaction.add(R.id.fragment_ly2, mMeFragment);
                }
                setViewBottom();
                break;
            default:
                break;
        }
    }

    public void setView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        if (PublicStaticData.MainFragmentNmuber == 0) {
            showFragment(0, transaction);
            mainimg.setImageResource(R.drawable.icon_home_sel);
            serviceimg.setImageResource(R.drawable.icon_sevice_nor);
            studioimg.setImageResource(R.drawable.icon_stor_nor);
            meimg.setImageResource(R.drawable.icon_mine_nor);
        } else if (PublicStaticData.MainFragmentNmuber == 1) {
            showFragment(1, transaction);
            mainimg.setImageResource(R.drawable.icon_home_nor);
            serviceimg.setImageResource(R.drawable.icon_sevice_sel);
            studioimg.setImageResource(R.drawable.icon_stor_nor);
            meimg.setImageResource(R.drawable.icon_mine_nor);
        } else if (PublicStaticData.MainFragmentNmuber == 2) {
//			studioly.setSelected(true);
            showFragment(2, transaction);
            mainimg.setImageResource(R.drawable.icon_home_nor);
            serviceimg.setImageResource(R.drawable.icon_sevice_nor);
            studioimg.setImageResource(R.drawable.icon_stor_sel);
            meimg.setImageResource(R.drawable.icon_mine_nor);
        } else {
//			mely.setSelected(true);
            showFragment(3, transaction);
            mainimg.setImageResource(R.drawable.icon_home_nor);
            serviceimg.setImageResource(R.drawable.icon_sevice_nor);
            studioimg.setImageResource(R.drawable.icon_stor_nor);
            meimg.setImageResource(R.drawable.icon_mine_sel);
        }
//        transaction.commit();
        transaction. commitAllowingStateLoss();
    }


    public void setViewBottom() {
        if (PublicStaticData.MainFragmentNmuber == 0) {
            mainimg.setImageResource(R.drawable.icon_home_sel);
            serviceimg.setImageResource(R.drawable.icon_sevice_nor);
            studioimg.setImageResource(R.drawable.icon_stor_nor);
            meimg.setImageResource(R.drawable.icon_mine_nor);
        } else if (PublicStaticData.MainFragmentNmuber == 1) {
            mainimg.setImageResource(R.drawable.icon_home_nor);
            serviceimg.setImageResource(R.drawable.icon_sevice_sel);
            studioimg.setImageResource(R.drawable.icon_stor_nor);
            meimg.setImageResource(R.drawable.icon_mine_nor);
        } else if (PublicStaticData.MainFragmentNmuber == 2) {
            mainimg.setImageResource(R.drawable.icon_home_nor);
            serviceimg.setImageResource(R.drawable.icon_sevice_nor);
            studioimg.setImageResource(R.drawable.icon_stor_sel);
            meimg.setImageResource(R.drawable.icon_mine_nor);
        } else {
            mainimg.setImageResource(R.drawable.icon_home_nor);
            serviceimg.setImageResource(R.drawable.icon_sevice_nor);
            studioimg.setImageResource(R.drawable.icon_stor_nor);
            meimg.setImageResource(R.drawable.icon_mine_sel);
        }
    }


    private void registBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lql.setview");
        filter.addAction("com.lql.toMain");
        filter.addAction("com.lql.MainActivity.setClick");
        registerReceiver(mFreshReceiver, filter);
    }

    private BroadcastReceiver mFreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.lql.setview".equals(action)||"com.lql.toMain".equals(action)) {
                setView();
            }
//            if ("com.lql.toMain".equals(action)) {
//                setView();
//            }
            if("com.lql.MainActivity.setClick".equals(action)){
                setClick();
            }
        }
    };
    private boolean CanClickable=true;

    private void setClick(){
        CanClickable=PreferenceUtils.getBoolean("Click",true);
        if(CanClickable){
            mainly.setClickable(true);
            servicely.setClickable(true);
            studioly.setClickable(true);
            mely.setClickable(true);
        }else{
            mainly.setClickable(false);
            servicely.setClickable(false);
            studioly.setClickable(false);
            mely.setClickable(false);
        }
    }

    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        unregisterReceiver(mFreshReceiver);
        super.onDestroy();
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                if (!TextUtils.isEmpty(PublicStaticData.Name)) {
                    Platform mPlatform = ShareSDK.getPlatform(PublicStaticData.Name);
                    mPlatform.removeAccount(true);
                }
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
