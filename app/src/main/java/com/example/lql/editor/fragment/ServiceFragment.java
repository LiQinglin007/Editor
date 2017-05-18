package com.example.lql.editor.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.activity.service.ServiceDetailsActivity;
import com.example.lql.editor.adapter.service.ServiceAdapter;
import com.example.lql.editor.bean.ServiceBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PopuWindowUtils;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.RecyclerView.DividerItemDecoration;
import com.example.lql.editor.widget.RecyclerView.MyDecoration;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.example.lql.editor.widget.RecyclerView.IsBottom.isSlideToBottom;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment implements View.OnClickListener {

    View view;
    TextView defaulttv;//默认
    TextView bondtv;//销量
    TextView reputationtv;//信誉
    TextView salesvolumetv;//人气
    RecyclerView studio_re;//list


    EditText search;
    TextView titleright;

    TextView service_screen_tv;
    LinearLayout service_screen_lv;
    int Page = 1;
    String seachtype = "0";
    String Direction = "";

    ArrayList<ServiceBean.DataBean> mList = new ArrayList<>();

    ServiceAdapter mServiceAdapter;
    int AdapterType = 1;

    LinearLayout loading;
    int Count = 1;
    SwipeRefreshLayout mySwipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service, container, false);
        registBroadcastReceiver();
        initView();
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //判断该Fragment时候已经正在前台显示  通过这两个判断，就可以知道什么时候去加载数据了
        if (isVisibleToUser && isVisible()) {
            Direction="";
            Page = 1;
            getData(true, 0,false);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }




    @Override
    public void onResume() {
        super.onResume();
        Page = 1;
        getData(true, 0,false);
    }

    private void registBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lql.setview");
        getActivity().registerReceiver(mFreshReceiver, filter);
    }

    private BroadcastReceiver mFreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.lql.setview".equals(action)) {
                setView();
            }
        }
    };


    private void initView() {
        defaulttv = (TextView) view.findViewById(R.id.service_default_tv);
        bondtv = (TextView) view.findViewById(R.id.service_bond_tv);
        reputationtv = (TextView) view.findViewById(R.id.service_reputation_tv);
        salesvolumetv = (TextView) view.findViewById(R.id.service_salesvolume_tv);

        studio_re = (RecyclerView) view.findViewById(R.id.service_re);
        service_screen_lv = (LinearLayout) view.findViewById(R.id.service_screen_lv);


        search = (EditText) view.findViewById(R.id.service_search_ed);
        titleright = (TextView) view.findViewById(R.id.service_search_tv);
        loading = (LinearLayout) view.findViewById(R.id.loading);
        mySwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.mySwipeRefresh);
        mySwipeRefresh.setColorSchemeResources(android.R.color.holo_red_light);
        //设置下拉刷新监听
        mySwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Page = 1;
                        getData(true, 0,false);
                    }
                }, 0);
            }
        });


        defaulttv.setOnClickListener(this);
        bondtv.setOnClickListener(this);
        reputationtv.setOnClickListener(this);
        salesvolumetv.setOnClickListener(this);
        service_screen_lv.setOnClickListener(this);
        titleright.setOnClickListener(this);

        setView();

        // 设置搜索框搜索取消搜索按钮
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1,
                                          KeyEvent arg2) {
                if (arg1 == 0) {
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    if(TextUtils.isEmpty(search.getText().toString().trim())){
                        T.shortToast(getActivity(),"搜索内容不能为空");
                        return true;
                    }else{
                        getData(true, 1,true);
                        return true;
                    }
                }
                return false;
            }
        });



        studio_re.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy != 0) {
                    if (isSlideToBottom(recyclerView)) {
                        if (Count > 0) {
                            Page++;
                            loading.setVisibility(View.VISIBLE);
                            getData(false, 1,false);
                        } else {
                            Toast.makeText(getActivity(), "没有更多内容了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    public void setView() {
        //  1:速审   2:查重   3:降重
        if (PublicStaticData.PopNumberTitle == 1) {
            Direction = "";
            //        Type1:检测查重 2：修改降重3：编辑速审
//        1、只有速审没有原价
            PublicStaticData.ServiceType=3;
            //速审
            AdapterType = 1;
            titleright.setText("速审");
            service_screen_lv.setVisibility(View.GONE);
        } else if (PublicStaticData.PopNumberTitle == 2) {
            PublicStaticData.ServiceType=1;
            //查重
            AdapterType = 2;
            titleright.setText("查重");
            if(PublicStaticData.PopNumber==1){
                Direction = "期刊职称论文查重";
            }else if(PublicStaticData.PopNumber==2){
                Direction = "学位论文分段查重";
            }else if(PublicStaticData.PopNumber==3){
                Direction = "本科学位PMLC查重";
            }else if(PublicStaticData.PopNumber==4){
                Direction = "硕博论文VIP系统查重";
            }
            service_screen_lv.setVisibility(View.VISIBLE);
        } else if (PublicStaticData.PopNumberTitle == 3) {
            Direction = "";
            PublicStaticData.ServiceType=2;
            //降重
            AdapterType = 3;
            titleright.setText("降重");
            service_screen_lv.setVisibility(View.GONE);
        }

        studio_re.setLayoutManager(new LinearLayoutManager(getActivity()));
        mServiceAdapter = new ServiceAdapter(mList, getActivity(), AdapterType);
        studio_re.addItemDecoration(new DividerItemDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        studio_re.setAdapter(mServiceAdapter);

        mServiceAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PublicStaticData.ServiceId = mList.get(position).getId() + "";
                Intent intent = new Intent(getActivity(), ServiceDetailsActivity.class);
                startActivity(intent);
            }
        });

        getData(true, 1,true);
    }


    /**
     *
     * @param IsClearn  需要不需要清除之前的
     * @param index
     * @param ToastType   是不是要提示语句
     */
    private void getData(final boolean IsClearn, final int index,final boolean ToastType) {
        PreferenceUtils.setBoolean("Click",false);
        sendBrocast();
//        Keywords:string			关键字
//        Type:int					1:查重2：降重3：速审
//        Searchtype:int			0:默认	1:销量2：信誉3：人气
//        Direction:string			type为1时，期刊职称，学位论文，硕博论文
//        Page:int					页数   1
//        Rows:int					行数   10
        String Keywords = search.getText().toString().trim();
        String Type = "";

        //1：速审  2：查重   3：降重
        if (PublicStaticData.PopNumberTitle == 1) {
            Type = "3";
            PublicStaticData.ServiceType=3;
        } else if (PublicStaticData.PopNumberTitle == 2) {
            Type = "1";
            PublicStaticData.ServiceType=1;
        } else if (PublicStaticData.PopNumberTitle == 3) {
            Type = "2";
            PublicStaticData.ServiceType=2;
        }
        SendRequest.repeatlist(Keywords, Type, seachtype, Direction, Page + "", "20", new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                PreferenceUtils.setBoolean("Click",true);
                Log.e("==onSuccess",response);
                loading.setVisibility(View.GONE);
                sendBrocast();

                if (response.contains("<html>")) {
                    T.shortToast(getActivity(), "服务器异常");
                    return;
                }
                ServiceBean mService = JSON.parseObject(response, ServiceBean.class);
                if (mService.getResultCode() == 0) {
                    Count = mService.getCount();
                    if (IsClearn) {
                        mList.clear();
                    }

                    if(ToastType){
                        if(mService.getData()==null||mService.getData().size()==0){
                            T.shortToast(getActivity(), "暂无数据");
                        }
                    }

                    mList.addAll(mService.getData());
                    mServiceAdapter.notifyDataSetChanged();
                } else {
                    T.shortToast(getActivity(), mService.getMsg());
                }

                if (index == 0) {
                    //停止刷新动画
                    mySwipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Throwable e) {
                PreferenceUtils.setBoolean("Click",true);
                loading.setVisibility(View.GONE);
                sendBrocast();
                T.shortToast(getActivity(), "亲，请检查网络");
                if (index == 0) {
                    //停止刷新动画
                    mySwipeRefresh.setRefreshing(false);
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_default_tv://m默认
                seachtype = "0";
                setTitleView();
                defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                getData(true, 1,true);
                break;
            case R.id.service_bond_tv://销量
                seachtype = "1";
                setTitleView();
                bondtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                getData(true, 1,true);
                break;
            case R.id.service_reputation_tv://信誉
                seachtype = "2";
                setTitleView();
                reputationtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                getData(true, 1,true);
                break;
            case R.id.service_salesvolume_tv://人气
                seachtype = "3";
                setTitleView();
                salesvolumetv.setTextColor(getResources().getColor(R.color.color_1AB394));
                getData(true, 1,true);
                break;
            case R.id.service_screen_lv://筛选
                PopuWindowUtils.showSinglePopup(getActivity(), service_screen_lv, new PopuWindowUtils.SingePopwCallback() {
                    @Override
                    public void SingePopCallback(int number) {
                        if (number == 1) {
                            //期刊职称论文查重
                            Direction = "期刊职称论文查重";
                            PublicStaticData.PopNumber = 1;
                            getData(true, 1,true);
                        } else if (number == 2) {
                            //学位分段论文查重
                            Direction = "学位论文分段查重";
                            PublicStaticData.PopNumber = 2;
                            getData(true, 1,true);
                        } else if (number == 3) {
                            //本科学位PMLC查重
                            Direction = "本科学位PMLC查重";
                            PublicStaticData.PopNumber = 3;
                            getData(true, 1,true);
                        } else if (number == 4) {
                            //硕博论文VIP系统查重
                            Direction = "硕博论文VIP系统查重";
                            PublicStaticData.PopNumber = 4;
                            getData(true, 1,true);
                        }else{
                            Direction = "";
                            PublicStaticData.PopNumber = 0;
                            getData(true, 1,true);
                        }
                    }
                });
                break;
            case R.id.service_search_tv://顶部筛选

                PopuWindowUtils.showSinglePopupTitle(getActivity(), titleright, new PopuWindowUtils.SingePopwCallback() {
                    @Override
                    public void SingePopCallback(int number) {
                        if (number == 1) {
                            //        Type1:检测查重 2：修改降重3：编辑速审
//        1、只有速审没有原价
                            PublicStaticData.ServiceType=3;
                            //1：速审  2：查重   3：降重
                            PublicStaticData.PopNumberTitle = 1;
                            //速审
                            AdapterType = 1;
                            titleright.setText("速审");
                            service_screen_lv.setVisibility(View.GONE);
                            seachtype = "0";
                            Direction = "";
                            setTitleView();
                            defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                            PublicStaticData.PopNumber = 0;
                            getData(true, 1,true);
                        } else if (number == 2) {
                            //查重
                            PublicStaticData.ServiceType=1;
                            PublicStaticData.PopNumberTitle = 2;
                            AdapterType = 2;
                            titleright.setText("查重");
                            Direction = "";
                            service_screen_lv.setVisibility(View.VISIBLE);

                            seachtype = "0";
                            setTitleView();
                            defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                            PublicStaticData.PopNumber = 0;
                            getData(true, 1,true);
                        } else if (number == 3) {
                            //降重
                            PublicStaticData.ServiceType=2;
                            PublicStaticData.PopNumberTitle = 3;
                            AdapterType = 3;
                            titleright.setText("降重");
                            service_screen_lv.setVisibility(View.GONE);
                            seachtype = "0";
                            Direction = "";
                            setTitleView();
                            defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                            PublicStaticData.PopNumber = 0;
                            getData(true, 1,true);
                        }
                    }
                });
                break;
        }
    }


    private void setTitleView() {
        defaulttv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        bondtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        reputationtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        salesvolumetv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
    }

    public void sendBrocast(){
        Intent intent = new Intent();
        intent.setAction("com.lql.MainActivity.setClick");
        getActivity().sendBroadcast(intent);
    }

}
