package com.example.lql.editor.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.example.lql.editor.activity.main.MainActivity;
import com.example.lql.editor.activity.studio.StudioDetailsActivity;
import com.example.lql.editor.adapter.studio.StudioAdapter;
import com.example.lql.editor.bean.StudioListBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
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
 * 工作室
 */
public class StudioFragment extends Fragment implements View.OnClickListener {

    View view;
    TextView defaulttv;//默认
    TextView bondtv;//保证金
    TextView reputationtv;//信誉
    TextView salesvolumetv;//销量
    RecyclerView studio_re;//list


    EditText search;

    StudioAdapter mStudioAdapter;//工作室的适配器


    int Page = 1;
    String Searchtype = "0";

    ArrayList<StudioListBean.DataBean> mList = new ArrayList<>();

    LinearLayout loading;
    int Count = 1;
    SwipeRefreshLayout mySwipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_studio, container, false);
        initView();
        return view;
    }

    private void initView() {
        defaulttv = (TextView) view.findViewById(R.id.studio_default_tv);
        bondtv = (TextView) view.findViewById(R.id.studio_bond_tv);
        reputationtv = (TextView) view.findViewById(R.id.studio_reputation_tv);
        salesvolumetv = (TextView) view.findViewById(R.id.studio_salesvolume_tv);
        studio_re = (RecyclerView) view.findViewById(R.id.studio_re);

        search = (EditText) view.findViewById(R.id.search_ed);
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


        mStudioAdapter = new StudioAdapter(mList, getActivity());
        studio_re.setLayoutManager(new LinearLayoutManager(getActivity()));
        studio_re.addItemDecoration(new DividerItemDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        studio_re.setAdapter(mStudioAdapter);
        mStudioAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PublicStaticData.StudioId = mList.get(position).getId() + "";
                Intent intent = new Intent(getActivity(), StudioDetailsActivity.class);
                startActivity(intent);
            }
        });



        // 设置搜索框搜索取消搜索按钮
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1,
                                          KeyEvent arg2) {
                if (arg1 == 0) {
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    if (TextUtils.isEmpty(search.getText().toString().trim())) {
                        T.shortToast(getActivity(), "搜索内容不能为空");
                        return true;
                    } else {
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
                            MainActivity.mainly.setClickable(false);
                            MainActivity.servicely.setClickable(false);
                            MainActivity.studioly.setClickable(false);
                            MainActivity.mely.setClickable(false);
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

        getData(true, 1,true);
    }


    @Override
    public void onResume() {
        super.onResume();
        Page = 1;
        getData(true, 1,false);
    }

    private void getData(final boolean IsClrean, final int index, final boolean ToastType) {
        PreferenceUtils.setBoolean("Click", false);

        String Keywords = search.getText().toString().trim();
//            0:默认1：保证金2：信誉3：销量
        SendRequest.StudioList(Keywords, Searchtype, Page + "", "20", new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                PreferenceUtils.setBoolean("Click", true);

                loading.setVisibility(View.GONE);
                if (response.contains("<html>")) {
                    T.shortToast(getActivity(), "服务器异常");
                    return;
                }
                StudioListBean mbean = JSON.parseObject(response, StudioListBean.class);
                if (mbean.getResultCode() == 0) {
                    Count = mbean.getCount();
                    if (IsClrean) {
                        mList.clear();
                    }
                    if (ToastType) {
                        if (mbean.getData() == null || mbean.getData().size() == 0) {
                            T.shortToast(getActivity(), "暂无数据");
                        }
                    }

                    mList.addAll(mbean.getData());
                    mStudioAdapter.notifyDataSetChanged();
                } else {
                    T.shortToast(getActivity(), "请求失败");
                }
                if (index == 0) {
                    //停止刷新动画
                    mySwipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Throwable e) {
                PreferenceUtils.setBoolean("Click", true);
                loading.setVisibility(View.GONE);
                if (index == 0) {
                    //停止刷新动画
                    mySwipeRefresh.setRefreshing(false);
                }
                T.shortToast(getActivity(), "亲，请检查网络");
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.studio_default_tv:
                Searchtype = "0";
                setTitleView();
                defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                getData(true, 1,false);
                break;
            case R.id.studio_bond_tv:
                Searchtype = "1";
                setTitleView();
                bondtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                getData(true, 1,false);
                break;
            case R.id.studio_reputation_tv:
                Searchtype = "2";
                setTitleView();
                reputationtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                getData(true, 1,false);
                break;
            case R.id.studio_salesvolume_tv:
                Searchtype = "3";
                setTitleView();
                salesvolumetv.setTextColor(getResources().getColor(R.color.color_1AB394));
                getData(true, 1,false);
                break;
        }
    }


    //    color_8E8E8E
    private void setTitleView() {
        defaulttv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        bondtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        reputationtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        salesvolumetv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
    }




}
