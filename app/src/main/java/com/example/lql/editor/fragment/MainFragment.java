package com.example.lql.editor.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.activity.main.ChooseCheckTypeActivity;
import com.example.lql.editor.activity.main.NoticeActivity;
import com.example.lql.editor.activity.service.ServiceDetailsActivity;
import com.example.lql.editor.adapter.main.MainFragmentAdapter;
import com.example.lql.editor.bean.GetImglist;
import com.example.lql.editor.bean.MainGetService;
import com.example.lql.editor.bean.NoticeBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.utils.UpdateAppUtils;
import com.example.lql.editor.view.SwitchViewGroup;
import com.example.lql.editor.widget.viewpager.PagerIndicator;
import com.example.lql.editor.widget.viewpager.ViewPagerUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    View view;
    ListView mListView;
    View HeatView;//头布局
    ArrayList<MainGetService.DataBean> strList = new ArrayList<>();
    ImageView main_submission_img;
    ImageView main_check_img;
    ImageView main_flag_img;
    ImageView main_service_img;

    private SwitchViewGroup mSwitchViewGroup;
    private List<String> datas = new ArrayList<>();

    ArrayList<GetImglist.DataBean> mImageList=new ArrayList<>();//图片
    private android.support.v4.view.ViewPager indexadvertViewPager;
    private PagerIndicator indexpagerIndicator;
    private LinearLayout indexLayoutAdvert;

    MainFragmentAdapter mMainFragmentAdapter;//适配器


    int page = 1;//页数
    int roews = 10;//每页的数量

    private TextView title;
    private ImageView left;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        //检查更新
        UpdateAppUtils.UpdateAppUtils(getActivity(),false);
        initView();
        return view;
    }

    public void initView() {

        HeatView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main_top, null);
        mListView = (ListView) view.findViewById(R.id.fragment_main_recycle);
        mListView.addHeaderView(HeatView);
        main_submission_img = (ImageView) HeatView.findViewById(R.id.main_submission_img);
        main_check_img = (ImageView) HeatView.findViewById(R.id.main_check_img);
        main_flag_img = (ImageView) HeatView.findViewById(R.id.main_flag_img);
        main_service_img = (ImageView) HeatView.findViewById(R.id.main_service_img);

        indexLayoutAdvert = (LinearLayout) HeatView.findViewById(R.id.index_layout_advert);
        this.indexpagerIndicator = (PagerIndicator) HeatView.findViewById(R.id.index_pagerIndicator);
        this.indexadvertViewPager = (ViewPager) HeatView.findViewById(R.id.index_advertViewPager);

        mSwitchViewGroup = (SwitchViewGroup) HeatView.findViewById(R.id.switchViewGroup);



        title= (TextView) view.findViewById(R.id.title_title);
        title.setText("首页");
        left= (ImageView) view.findViewById(R.id.titleBar_left_img);
        left.setVisibility(View.INVISIBLE);



        main_submission_img.setOnClickListener(this);
        main_check_img.setOnClickListener(this);
        main_flag_img.setOnClickListener(this);
        main_service_img.setOnClickListener(this);

        mMainFragmentAdapter = new MainFragmentAdapter(strList, getActivity());
        mListView.setAdapter(mMainFragmentAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Type1:检测查重 2：修改降重3：编辑速审
                if(strList.get(i-1).getType()==1){
                    //1：速审  2：查重   3：降重
                    PublicStaticData.PopNumberTitle=2;
                }else  if(strList.get(i-1).getType()==2){
                    PublicStaticData.PopNumberTitle=3;
                }else{
                    PublicStaticData.PopNumberTitle=1;
                }
//                sendBrocast();
                PublicStaticData.ServiceId=strList.get(i-1).getId()+"";
                startActivity(new Intent(getActivity(), ServiceDetailsActivity.class));
            }
        });
        initdata();
    }



    public void initdata() {
        //获取轮播图
        getImageList();
        //获取首页服务
        getServiceList();
        //获取公告
        getNotice();
    }







    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_submission_img://投稿
                PublicStaticData.PopNumberTitle=1;
                PublicStaticData.MainFragmentNmuber = 1;
                sendBrocast();
                break;
            case R.id.main_check_img://查重
                startActivity(new Intent(getActivity(), ChooseCheckTypeActivity.class));
                break;
            case R.id.main_flag_img://降重
                PublicStaticData.PopNumberTitle=3;
                PublicStaticData.MainFragmentNmuber = 1;
                sendBrocast();
                break;
            case R.id.main_service_img://我的服务
                PublicStaticData.MainFragmentNmuber = 3;
                sendBrocast();
                break;
        }
    }


    @Override
    public void onResume() {
        initdata();
        sendBrocast();
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //判断该Fragment时候已经正在前台显示  通过这两个判断，就可以知道什么时候去加载数据了
        if (isVisibleToUser && isVisible()) {
            initdata();
            sendBrocast();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }



    public void sendBrocast(){
        Intent intent = new Intent();
        intent.setAction("com.lql.setview");
        getActivity().sendBroadcast(intent);
    }


    /**
     * 获取公告
     */
    private void getNotice(){
        SendRequest.Notice(new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if(response.contains("<html>")){
                    T.shortToast(getActivity(),"服务器异常");
                    return;
                }
                NoticeBean mBean=JSON.parseObject(response,NoticeBean.class);
                if(mBean.getResultCode()==0){
                    for (int i = 0; i < mBean.getData().size(); i++) {
                        datas.add(mBean.getData().get(i).getNotice());
                    }
                    mSwitchViewGroup.addData(datas);
                    mSwitchViewGroup.startScroll();
                    mSwitchViewGroup.setOnClickTabListener(new SwitchViewGroup.OnClickTabListener() {
                        @Override
                        public void onClickTab(int index) {
                            //每个条目的点击事件
                            startActivity(new Intent(getActivity(), NoticeActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }


    /**
     * 获取轮播图
     */
    public void getImageList() {
        SendRequest.ImgList(new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                Log.e("response",response);
                if(response.contains("<html>")){
                    T.shortToast(getActivity(),"服务器异常");
                    return;
                }
                GetImglist getImglist = JSON.parseObject(response, GetImglist.class);
                if (getImglist.getResultCode() == 0) {
                    mImageList.clear();
                    mImageList.addAll(getImglist.getData());
                    ViewPagerUtils mviewPagerUtils = new ViewPagerUtils(mImageList, getActivity(), indexadvertViewPager, indexpagerIndicator);
                    mviewPagerUtils.setAdvers();
                    mviewPagerUtils.setAdvertSize(indexLayoutAdvert);
                }

            }

            @Override
            public void onFailure(Throwable e) {

                T.shortToast(getActivity(), "亲，请检查网络");
            }
        });
    }

    /**
     * 获取首页服务
     */
    private void getServiceList() {
        SendRequest.ServiceList(page, roews, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if(response.contains("<html>")){
                    T.shortToast(getActivity(),"服务器异常");
                    return;
                }
                    MainGetService mainGetService=JSON.parseObject(response,MainGetService.class);
                    if(mainGetService.getResultCode()==0){
                        strList.clear();
                        strList.addAll(mainGetService.getData());
                        mMainFragmentAdapter.notifyDataSetChanged();
                    }
            }

            @Override
            public void onFailure(Throwable e) {
                T.shortToast(getActivity(), "亲，请检查网络" );
            }
        });
    }
}
