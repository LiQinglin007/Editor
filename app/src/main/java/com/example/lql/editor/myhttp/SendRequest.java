package com.example.lql.editor.myhttp;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.lql.editor.myhttp.OkHttpUtils.MyPost;

/**
 * @author Jax
 * @version V1.0.0
 * @Created on 2016/1/20 15:06.
 */
public class SendRequest {

    /**
     * 登录
     *
     * @param Telphone
     * @param Password
     * @param myCallBack
     */
    public static void Login1(String Telphone, String Password, mOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Telphone", Telphone)
                .add("Password", Password)
                .build();
        MyPost(formBody, MyUrl.UserLogin, myCallBack);
    }


    /**
     * 3、获取验证码
     *
     * @param Telphone 电话号码
     * @param Type     1:注册  2：忘记密码 3：绑定手机号
     */
    public static void getregistersms(String Telphone, String Type, mOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Telphone", Telphone)
                .add("Type", Type)
                .build();
        MyPost(formBody, MyUrl.getregistersms, myCallBack);
    }


    /**
     * 4、用户注册
     * Telphone:string				电话号码
     * Password:string				密码
     * Confirmpassword:string			确认密码
     * Registercode:string 			验证码
     * Type:int						1:注册2：忘记密码
     */
    public static void userregister(String Telphone, String Password, String Registercode, String Type, mOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Telphone", Telphone)
                .add("Type", Type)
                .add("Password", Password)
                .add("Registercode", Registercode)
                .add("Confirmpassword", Password)
                .build();
        MyPost(formBody, MyUrl.userregister, myCallBack);
    }


    /**
     * 2.	第三方登录
     *
     * @param Account 第三方返回的openid
     * @param Type    1:QQ 2：微信 3：微博
     */
    public static void OtherLogin(String Account, int Type, mOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Type", Type + "")
                .add("Account", Account)
                .build();
        MyPost(formBody, MyUrl.ThirdLogin, myCallBack);
    }


    /**
     * 5.首页获取轮播图
     */
    public static void ImgList(mOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .build();
        MyPost(formBody, MyUrl.ImgList, myCallBack);
    }


    /**
     * 6、首页服务列表（可分页）
     *
     * @param Page 页数
     * @param Rows 行数
     */
    public static void ServiceList(int Page, int Rows, mOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Page", Page + "")
                .add("Rows", Rows + "")
                .build();
        MyPost(formBody, MyUrl.ServiceList, myCallBack);
    }


    /**
     * 7.查重/速审/降重
     *
     * @param Keywords   关键字
     * @param Type       1:查重2：降重3：速审
     * @param Searchtype 1:销量2：信誉3：人气
     * @param Direction  type为1时，期刊职称，学位论文，硕博论文
     * @param Page       页数
     * @param Rows       行数
     * @param myCallBack
     */
    public static void repeatlist(String Keywords, String Type, String Searchtype, String Direction, String Page, String Rows, mOkCallBack myCallBack) {


        RequestBody formBody = new FormBody.Builder()
                .add("keywords", Keywords)
                .add("type", Type)
                .add("seachType", Searchtype)
                .add("direction", Direction)
                .add("page", Page)
                .add("rows", Rows)
                .build();
        MyPost(formBody, MyUrl.repeatlist, myCallBack);
    }


    /**
     * 8、服务详情
     *
     * @param serviceId   服务ID
     * @param mOkCallBack 回调
     */
    public static void ServiceDetail(String serviceId, String Userid, mOkCallBack mOkCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Id", serviceId)
                .add("Userid", Userid)
                .build();
        MyPost(formBody, MyUrl.ServiceDetail, mOkCallBack);

    }

    /**
     * 45、用户收藏、取消收藏服务、工作室
     *
     * @param serviceId   服务编号/工作室编号
     * @param type        1:服务2：工作室
     * @param mOkCallBack
     */
    public static void UserFavorite(String Userid, String serviceId, int type, mOkCallBack mOkCallBack) {


        RequestBody formBody = new FormBody.Builder()
                .add("Id", serviceId)
                .add("userid", Userid)
                .add("Type", type + "")
                .build();
        MyPost(formBody, MyUrl.UserFavorite, mOkCallBack);
    }

    /**
     * 12、学科列表   0:学科列表    1:学历列表   2:职称列表
     *
     * @param mOkCallBack
     */
    public static void ProjectList(mOkCallBack mOkCallBack, int type) {
        RequestBody formBody = new FormBody.Builder()
                .build();
        if (type == 0) {
            MyPost(formBody, MyUrl.ProjectList, mOkCallBack);
        } else if (type == 1) {
            MyPost(formBody, MyUrl.SchoolList, mOkCallBack);
        } else if (type == 2) {
            MyPost(formBody, MyUrl.Professtion, mOkCallBack);
        }
    }


    /**
     * 选中文件
     *
     * @param mOkCallBack
     */
    public static void DocList(String Userid, mOkCallBack mOkCallBack) {


        RequestBody formBody = new FormBody.Builder()
                .add("userid", Userid)
                .build();
        MyPost(formBody, MyUrl.DocList, mOkCallBack);
    }


    /**
     * 我要查重：
     *
     * @param Shopid      工作室编号
     * @param Productid   服务编号
     * @param Title       标题
     * @param Authorname  作者姓名
     * @param Direction   学科方向
     * @param ServiceFee  此次服务总价钱
     * @param Url         文件地址
     * @param UrlName     文件名称
     * @param mOkCallBack
     */
    public static void AddMyService1(String Userid, String Shopid, String Productid, String Title, String Authorname, String Direction, String ServiceFee, String Url, String UrlName, mOkCallBack mOkCallBack) {


        RequestBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Shopid", Shopid)
                .add("Productid", Productid)
                .add("Type", "1")
                .add("Title", Title)
                .add("Authorname", Authorname)
                .add("Direction", Direction)
                .add("ServiceFee", ServiceFee)
                .add("Url", Url)
                .add("UrlName", UrlName)
                .build();
        MyPost(formBody, MyUrl.AddMyService, mOkCallBack);
    }


    /**
     * 我要降重：
     *
     * @param Shopid      工作室编号
     * @param Productid   服务编号
     * @param ServiceFee  此次服务总价钱
     * @param Count       份数
     * @param mOkCallBack
     */
    public static void AddMyService2(String Userid, String Shopid, String Productid, String ServiceFee, String Count, mOkCallBack mOkCallBack) {


        RequestBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Shopid", Shopid)
                .add("Productid", Productid)
                .add("Type", "2")
                .add("ServiceFee", ServiceFee)
                .add("Count", Count)
                .build();
        MyPost(formBody, MyUrl.AddMyService, mOkCallBack);
    }

    /**
     * 速审参数：  Type:int						1:查重2：降重3：速审
     *
     * @param Shopid          工作室编号
     * @param Productid       服务编号
     * @param Title           标题
     * @param Authorname      作者姓名
     * @param Direction       学科方向
     * @param ServiceFee      此次服务总价钱
     * @param Url             文件地址
     * @param UrlName         文件名称
     * @param Schooling       学历
     * @param Professional    职称
     * @param Publicationtime 见刊时间
     * @param mOkCallBack
     */
    public static void AddMyService3(String Userid, String Shopid, String Productid, String Title, String Authorname, String Direction, String ServiceFee, String Url, String UrlName, String Schooling, String Professional, String Publicationtime, mOkCallBack mOkCallBack) {


        RequestBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Shopid", Shopid)
                .add("Productid", Productid)
                .add("Type", "3")
                .add("Title", Title)
                .add("Authorname", Authorname)
                .add("Direction", Direction)
                .add("ServiceFee", ServiceFee)
                .add("Url", Url)
                .add("UrlName", UrlName)
                .add("Schooling", Schooling)
                .add("Professional", Professional)
                .add("Publicationtime", Publicationtime)
                .build();
        MyPost(formBody, MyUrl.AddMyService, mOkCallBack);
    }


    /**
     * 10、工作室列表
     *
     * @param Keywords    关键字
     * @param Searchtype  0:默认1：保证金2：信誉3：销量
     * @param Page        页数
     * @param Rows        行数
     * @param mOkCallBack
     */
    public static void StudioList(String Keywords, String Searchtype, String Page, String Rows, mOkCallBack mOkCallBack) {
        //        http://192.168.29.134:66/serviceRecord/StudioList?keywords=&seachType=0&page=1&rows=5
        RequestBody formBody = new FormBody.Builder()
                .add("keywords", Keywords)
                .add("seachType", Searchtype)
                .add("page", Page)
                .add("rows", Rows)
                .build();
        MyPost(formBody, MyUrl.StudioList, mOkCallBack);
    }

    /**
     * 11、工作室详情
     *
     * @param Id          工作室编号
     * @param mOkCallBack
     */
    public static void ShopDetail(String Userid, String Id, mOkCallBack mOkCallBack) {

        RequestBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Id", Id)
                .build();
        MyPost(formBody, MyUrl.ShopDetail, mOkCallBack);
    }


    /**
     * 44、工作室评价列表
     *
     * @param Workid      工作室编号
     * @param Page        页数
     * @param Rows        行数
     * @param mOkCallBack
     */
    public static void ProductCommentList(String Workid, String Page, String Rows, mOkCallBack mOkCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("WorkId", Workid)
                .add("Page", Page)
                .add("Rows", Rows)
                .build();
        MyPost(formBody, MyUrl.ProductCommentList, mOkCallBack);
    }


    /**
     * 41、公告
     *
     * @param mOkCallBack
     */
    public static void Notice(mOkCallBack mOkCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .build();
        MyPost(formBody, MyUrl.Notice, mOkCallBack);
    }


    /**
     * 38、完善个人资料
     *
     * @param WorkName    昵称
     * @param Sex         性别
     * @param Birthday    生日
     * @param School      学校
     * @param Job         职称
     * @param Graduate    学历
     * @param Profession  职位
     * @param mOkCallBack
     */
    public static void UpdateInfo(String Userid, String WorkName, String Sex, String Birthday, String School, String Job, String Graduate, String Profession, File picPath, OkHttpClientManager.ResultCallback mOkCallBack) {
        OkHttpClientManager.Param param = new OkHttpClientManager.Param("Id", Userid);

        if (TextUtils.isEmpty(WorkName) || WorkName.equals("")) {
            WorkName = " ";
        }

        if (TextUtils.isEmpty(School) || School.equals("")) {
            School = " ";
        }
        if (TextUtils.isEmpty(Job) || Job.equals("")) {
            Job = " ";
        }
        if (TextUtils.isEmpty(Graduate) || Graduate.equals("")) {
            Graduate = " ";
        }
        if (TextUtils.isEmpty(Profession) || Profession.equals("")) {
            Profession = " ";
        }

        OkHttpClientManager.Param param1 = new OkHttpClientManager.Param("WorkName", WorkName);
        OkHttpClientManager.Param param2 = new OkHttpClientManager.Param("Sex", Sex);
        OkHttpClientManager.Param param3 = new OkHttpClientManager.Param("Birthday", Birthday);
        OkHttpClientManager.Param param4 = new OkHttpClientManager.Param("School", School);
        OkHttpClientManager.Param param5 = new OkHttpClientManager.Param("Job", Job);
        OkHttpClientManager.Param param6 = new OkHttpClientManager.Param("Graduate", Graduate);
        OkHttpClientManager.Param param7 = new OkHttpClientManager.Param("Profession", Profession);
        if (picPath != null) {
            try {
                OkHttpClientManager.postAsyn(MyUrl.UpdateInfo, mOkCallBack, picPath, "img1", param, param1, param2, param3, param4, param5, param6, param7);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            OkHttpClientManager.postAsyn(MyUrl.UpdateInfo, mOkCallBack, param, param1, param2, param3, param4, param5, param6, param7);
        }
    }


    /**
     * 29、意见反馈
     *
     * @param Conetnt     意见
     * @param mOkCallBack
     */
    public static void AddSuggestion(String Userid, String Conetnt, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Suggestion_", Conetnt)
                .build();
        MyPost(formBody, MyUrl.AddSuggestion, mOkCallBack);
    }


    /**
     * 36、修改密码
     *
     * @param oldPwd      旧密码
     * @param newPwd      新密码
     * @param mOkCallBack
     */
    public static void UpdataPassWord(String Userid, String oldPwd, String newPwd, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder().
                add("userId", Userid).
                add("oldPwd", oldPwd).
                add("newPwd", newPwd).
                add("affirmPwd", newPwd).
                build();
        MyPost(formBody, MyUrl.UpdataPassWord, mOkCallBack);
    }


    /**
     * 37、我的评价
     *
     * @param mOkCallBack
     */
    public static void CommentList(String Userid, int Page, int rows, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder().
                add("userId", Userid).
                add("page", Page + "").
                add("rows", rows + "").
                build();
        MyPost(formBody, MyUrl.CommentList, mOkCallBack);
    }


    /**
     * 33、账户设置
     *
     * @param alipayCard  支付宝账号
     * @param alipayName  支付宝姓名
     * @param mOkCallBack
     */
    public static void UpdateCard(String Userid, String alipayCard, String alipayName, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder()
                .add("userId", Userid)
                .add("alipayCard", alipayCard)
                .add("alipayName", alipayName)
                .build();

        MyPost(formBody, MyUrl.UpdateCard, mOkCallBack);

    }


    /**
     * 31、提现页面
     *
     * @param mOkCallBack
     */
    public static void DepositView(String Userid, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder()
                .add("userId", Userid)
                .build();
        MyPost(formBody, MyUrl.DepositView, mOkCallBack);
    }


    /**
     * 提现
     *
     * @param DepositMoney 提现金额（两位小数的）
     * @param mOkCallBack
     */
    public static void AddDeposit(String Userid, String DepositMoney, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder()
                .add("depositMoney", DepositMoney)
                .add("Userid", Userid)
                .build();
        MyPost(formBody, MyUrl.AddDeposit, mOkCallBack);
    }


    /**
     * 39、充值
     *
     * @param cardNumber  卡号
     * @param password    密码
     * @param mOkCallBack
     */
    public static void Recharge(String Userid, String cardNumber, String password, mOkCallBack mOkCallBack) {

        FormBody formBody = new FormBody.Builder()
                .add("userId", Userid)
                .add("cardNumber", cardNumber)
                .add("password", password)
                .build();
        MyPost(formBody, MyUrl.Recharge, mOkCallBack);

    }

    /**
     * 绑定账号
     *
     * @param mOkCallBack
     */
    public static void RelieveView(String Userid, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder()
                .add("userId", Userid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.RelieveView, mOkCallBack);
    }

    /**
     * 解除绑定
     *
     * @param type        1解绑QQ    2解绑微信   3解绑微博
     * @param mOkCallBack
     */
    public static void Relieve(String Userid, int type, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder()
                .add("userId", Userid)
                .add("type", type + "")
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.Relieve, mOkCallBack);
    }


    /**
     * 46、用户收藏列表
     *
     * @param type        1：服务2：工作室
     * @param mOkCallBack
     */
    public static void UserFavoriteList(String Userid, int type, int page, int rows, mOkCallBack mOkCallBack) {
//        String Userid="";
//        if(PreferenceUtils.getBoolean("IsLogin",false)){
//            Userid="0";
//        }else{
//            Userid=PreferenceUtils.getString("UserId","");
//        }


        FormBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("type", type + "")
                .add("page", page + "")
                .add("rows", rows + "")
                .build();

        OkHttpUtils.MyPost(formBody, MyUrl.UserFavoriteList, mOkCallBack);
    }


    /**
     * 17、查重列表
     *
     * @param Flag        1:待检测 2：检测中 3：已完成
     * @param Page        页数
     * @param Rows        行数
     * @param mOkCallBack
     */
    public static void CheckRepeat(String Userid, int Flag, int Page, int Rows, mOkCallBack mOkCallBack) {
//        String Userid="";
//        if(PreferenceUtils.getBoolean("IsLogin",false)){
//            Userid="0";
//        }else{
//            Userid=PreferenceUtils.getString("UserId","");
//        }


        FormBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Flag", Flag + "")
                .add("Page", Page + "")
                .add("Rows", Rows + "")
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.CheckRepeat, mOkCallBack);
    }


    /**
     * 18、速审列表
     *
     * @param Flag        1:初审中2：复审中3：查稿中4：样刊跟踪5：已完成
     * @param Page        页数
     * @param Rows        行数
     * @param mOkCallBack
     */
    public static void SummaryList(String Userid, int Flag, int Page, int Rows, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Flag", Flag + "")
                .add("Page", Page + "")
                .add("Rows", Rows + "")
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.SummaryList, mOkCallBack);
    }


    /**
     * 19、降重订单
     *
     * @param Flag        1:进行中2：待确认3：已完成
     * @param Page        页数
     * @param Rows        行数
     * @param mOkCallBack
     */
    public static void ReduceRepeat(String Userid, int Flag, int Page, int Rows, mOkCallBack mOkCallBack) {

        FormBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Flag", Flag + "")
                .add("Page", Page + "")
                .add("Rows", Rows + "")
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.ReduceRepeat, mOkCallBack);
    }


    /**
     * 支付
     *
     * @param MyServiceid 提交服务订单编号
     * @param money       支付金额
     * @param type        速审复审通过付款传2   其它服务付款传1
     * @param mOkCallBack
     */
    public static void waterpay(String Userid, String MyServiceid, String money, String type, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("MyServiceid", MyServiceid)
                .add("money", money)
                .add("type", type)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.waterpay, mOkCallBack);

    }


    /**
     * 24、查重，取消检测
     *
     * @param serviceid
     * @param mOkCallBack
     */
    public static void CancelRepeat(String serviceid, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("serviceid", serviceid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.CancelRepeat, mOkCallBack);
    }


    /**
     * 15、服务评价
     *
     * @param Comcontent  评价内容
     * @param Grades      1:好评0：差评
     * @param serviceid   服务编号
     * @param mOkCallBack
     */
    public static void addcomment(String Userid, String Comcontent, int Grades, String serviceid, mOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Comcontent", Comcontent)
                .add("Grades", Grades + "")
                .add("Serviceid", serviceid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.addcomment, mOkCallBack);
    }


    /**
     * 22、速审---初审的取消投稿、取消
     *
     * @param Serviceid   服务编号
     * @param mOkCallBack
     */
    public static void SummaryCancel(String Serviceid, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("Serviceid", Serviceid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.SummaryCancel, mOkCallBack);
    }

    /**
     * 21、速审----初审的删除，复审的删除
     *
     * @param Serviceid   服务编号
     * @param mOkCallBack
     */
    public static void Delete(String Serviceid, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("Serviceid", Serviceid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.Delete, mOkCallBack);
    }


    /**
     * 26、速审--确认收刊
     *
     * @param Serviceid   服务编号
     * @param mOkCallBack
     */
    public static void Delivery(String Serviceid, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("Serviceid", Serviceid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.Delivery, mOkCallBack);
    }


    /**
     * 25、速审--确认核实
     *
     * @param Serviceid   服务编号
     * @param mOkCallBack
     */
    public static void ConfirmCheck(String Serviceid, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("myserviceid", Serviceid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.ConfirmCheck, mOkCallBack);
    }


    /**
     * 降重   确认订单
     *
     * @param Serviceid
     * @param mOkCallBack
     */
    public static void Confirm(String Serviceid, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("Serviceid", Serviceid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.Confirm, mOkCallBack);
    }


    /**
     * 30、实名认证
     *
     * @param file1
     * @param file2
     * @param file3
     * @param mResultCallback
     */
    public static void UserImgurlUpload(String Userid, String name,File file1, File file2, File file3, OkHttpClientManager.ResultCallback mResultCallback) {

        File[] files = {file1, file2, file3};
        String[] filesName = {"img1", "img2", "img3"};

        OkHttpClientManager.Param param = new OkHttpClientManager.Param("userId", Userid);
        OkHttpClientManager.Param param1 = new OkHttpClientManager.Param("username", name);
        try {
            OkHttpClientManager.postAsyn(MyUrl.UserImgurlUpload, mResultCallback, files, filesName, param,param1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 27、速审、降重详情
     *
     * @param serviceId
     * @param mOkCallBack
     */
    public static void SsDetail(String serviceId, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("serviceId", serviceId)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.SsDetail, mOkCallBack);
    }


    /**
     * 23、查重订单详情
     *
     * @param serviceId
     * @param mOkCallBack
     */
    public static void RepeatDetail(String serviceId, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("serviceId", serviceId)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.RepeatDetail, mOkCallBack);
    }

    /**
     * 工作室申请
     */
    public static void offer(String Userid, String PhoneString, ArrayList<File> file, OkHttpClientManager.ResultCallback mResultCallback) {

        File[] mFile = new File[file.size()];
        String[] filesName = new String[file.size()];

        for (int i = 0; i < file.size(); i++) {
            if (file.get(i) != null) {
                mFile[i] = file.get(i);
                filesName[i] = "img" + i;
            }
        }

        OkHttpClientManager.Param param = new OkHttpClientManager.Param("Telphone", PhoneString);
        OkHttpClientManager.Param param1 = new OkHttpClientManager.Param("userid", Userid);
        try {
            OkHttpClientManager.postAsyn(MyUrl.offer, mResultCallback, mFile, filesName, param, param1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    openId:string
//    Telphone:string			电话号码
//    Registercode:string			验证码
//    Type:int					1:QQ 2:微信 3：微博

    /**
     * @param openId
     * @param Telphone     电话号码
     * @param Registercode 验证码
     * @param type         1:QQ 2:微信 3：微博
     */
    public static void Bind(String openId, String Telphone, String Registercode, int type, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("openId", openId)
                .add("Telphone", Telphone)
                .add("Registercode", Registercode)
                .add("type", type + "")
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.Bind, mOkCallBack);
    }


    /**
     * 版本更新
     *
     * @param mOkCallBack
     */
    public static void UpdateVersion(mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.UpdateVersion, mOkCallBack);
    }


//    public static

    /**
     * @param response
     * @param FileName
     * @param mSaveFileCallBack
     * @return
     * @throws IOException
     */

    /**
     *
     * @param type   1:文档   2：apk
     * @param response
     * @param FileName
     * @param mSaveFileCallBack
     * @return
     * @throws IOException
     */
    public static File saveFile(int type,Response response, String FileName, SaveFileCallBack mSaveFileCallBack) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String str = sdf.format(date);
            String name = "";
            if (FileName.contains(".docx")) {
                name = FileName.substring(0, FileName.length() - 5);
            } else {
                name = FileName.substring(0, FileName.length() - 4);
            }

            File dir = new File(Environment.getExternalStorageDirectory() + "/学术圈OSS");
            if (!dir.exists()) dir.mkdirs();
            File file;
            if(type==1){
                file = new File(dir, name + str + ".zip");
            }else{
                file=new File(dir,"学术圈.apk");
            }

            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
            }
            fos.flush();
//            mSaveFileCallBack.myFileCallBack(0);
            return file;
        } finally {
            try {
                if (is != null) is.close();
                if (fos != null) fos.close();
                mSaveFileCallBack.myFileCallBack(0);
            } catch (IOException e) {
                mSaveFileCallBack.myFileCallBack(1);
            }
        }
    }


    /**
     * 48、充值店铺地址
     *
     * @param mOkCallBack
     */
    public static void shopaddress(mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.shopaddress, mOkCallBack);
    }


    /**
     * 43、查询用户余额、头像、昵称
     *
     * @param userid
     * @param mOkCallBack
     */
    public static void UserDetail(String userid, mOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("userid", userid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.UserDetail, mOkCallBack);
    }


    /**
     * 上传文件
     * @param Userid    id
     * @param mFile     需要上传的文件  File
     * @param mOkCallBack   回调
     */
    public static void Upload(String Userid,File mFile,OkHttpClientManager.ResultCallback mOkCallBack){
        OkHttpClientManager.Param param = new OkHttpClientManager.Param("Userid", Userid);
        try {
           OkHttpClientManager.postAsyn(MyUrl.Upload, mOkCallBack, mFile, "file", param);
         } catch (IOException e) {
           e.printStackTrace();
         }
    }


}
