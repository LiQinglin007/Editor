package com.example.lql.editor.myhttp;

/**
 * 网络请求相关设置,配置请求地址及参数
 *
 * @author Jax
 * @version V1.0.0
 * @Created on 2016/1/20 15:07.
 */
public class MyUrl {



    //内网
//    private static  String BaseUrl="http://192.168.29.134:66/";
//    private static  String BaseUrl="http://192.168.29.71:1000/";
//    外网
//    private static  String BaseUrl="https://222.223.251.109:66/";
//    private static  String BaseUrl="http://222.223.251.109:66/";
    private static  String BaseUrl="https://www.51cnkicheck.com/";
//    /Upload/img/20170110/20170110014625编审（01.07.01外网）(1).apkdownloadurl

    public static String Pic=BaseUrl;
    //获取验证码
    public static String getregistersms=BaseUrl+"UserLogin/getregistersms?";
    //用户注册
    public static String userregister=BaseUrl+"UserLogin/userregister?";
    //登录
    public static String UserLogin=BaseUrl+"UserLogin/UserLogin?";
    //第三方登录
    public static String ThirdLogin=BaseUrl+"UserLogin/ThirdLogin?";
    //首页轮播图
    public static String ImgList=BaseUrl+"homepage/ImgList";
    //首页服务列表
    public static String  ServiceList=BaseUrl+"homepage/ServiceList?";
    //7、查重/速审/降重
    public static String  repeatlist=BaseUrl+"serviceRecord/repeatlist?";
    //8、服务详情
    public static String  ServiceDetail=BaseUrl+"serviceRecord/ServiceDetail?";
//    45、用户收藏、取消收藏服务、工作室
    public static String  UserFavorite=BaseUrl+"usercenter/UserFavorite?";
//    12、学科列表
    public static String  ProjectList=BaseUrl+"serviceRecord/ProjectList";
//    13、学历列表
    public static String  SchoolList=BaseUrl+"serviceRecord/SchoolList";
//    14、职称列表
    public static String  Professtion=BaseUrl+"serviceRecord/Professtion";
    //16、选中文件
    public static String  DocList=BaseUrl+"usercenter/DocList";
    //9、我要查重、降重、速审
    public static String  AddMyService=BaseUrl+"serviceRecord/AddMyService";
    //10、工作室列表
    public static String  StudioList=BaseUrl+"serviceRecord/StudioList";
    //11、工作室详情
    public static String  ShopDetail=BaseUrl+"serviceRecord/ShopDetail";
    //44、工作室评价列表
    public static String  ProductCommentList=BaseUrl+"usercenter/ProductCommentList";
    //41、公告
    public static String  Notice=BaseUrl+"homepage/Notice";
//    38、完善个人资料
    public static String  UpdateInfo=BaseUrl+"UserCenter/UpdateInfo";
    //意见反馈
    public static String  AddSuggestion=BaseUrl+"UserCenter/AddSuggestion";
    //修改密码
    public static String  UpdataPassWord=BaseUrl+"UserCenter/UpdataPassWord";
    //我的评价
    public static String  CommentList=BaseUrl+"UserCenter/CommentList";
    //添加支付宝账号
    public static String  UpdateCard=BaseUrl+"Deposit/UpdateCard";
    //获取支付宝账号信息
    public static String  DepositView=BaseUrl+"Deposit/DepositView";
    //提现
    public static String  AddDeposit=BaseUrl+"Deposit/AddDeposit";
    //充值
    public static String  Recharge=BaseUrl+"UserCenter/Recharge";
    //查看绑定状态
    public static String  RelieveView=BaseUrl+"UserCenter/RelieveView";
    //用户收藏
    public static String  UserFavoriteList=BaseUrl+"usercenter/UserFavoriteList";
    //查重订单
    public static String  CheckRepeat=BaseUrl+"orderrecord/CheckRepeat";
    //速审订单
    public static String  SummaryList=BaseUrl+"orderrecord/SummaryList";
    //降重订单
    public static String  ReduceRepeat=BaseUrl+"orderrecord/ReduceRepeat";
    //24、查重，取消检测
    public  static String CancelRepeat=BaseUrl+"orderrecord/CancelRepeat";
    //服务评价
    public  static String addcomment=BaseUrl+"usercenter/addcomment";
    //速审   取消投稿   取消
    public static String SummaryCancel=BaseUrl+"orderrecord/SummaryCancel";
//    21、速审----初审的删除，复审的删除
    public static String Delete=BaseUrl+"orderrecord/Delete";
    //速审  确认收刊
    public static String Delivery=BaseUrl+"orderrecord/Delivery";
    //25、速审--确认核实
    public static String ConfirmCheck=BaseUrl+"orderrecord/ConfirmCheck";
    //降重  订单确认
    public static String Confirm=BaseUrl+"orderrecord/Confirm";
    //支付接口
    public static String  waterpay=BaseUrl+"usercenter/waterpay";
    //速审、降重详情
    public static String  SsDetail=BaseUrl+"orderrecord/SsDetail";
    //实名认证
//                                                    UserCenter/UserImgurlUpload
    public static String  UserImgurlUpload=BaseUrl+"UserCenter/UserImgurlUpload";
//    23、查重订单详情
    public static String  RepeatDetail=BaseUrl+"orderrecord/RepeatDetail";
//    工作室申请
    public static String offer=BaseUrl+"orderrecord/offer";
//    第三方登录   绑定手机号
    public static String Bind=BaseUrl+"UserLogin/Bind";
    //解除绑定
    public static String Relieve=BaseUrl+"UserCenter/Relieve";
    //版本更新
    public static String UpdateVersion=BaseUrl+"UpdateVersion/UpdateVersion";
    //充值店铺地址
    public static String shopaddress=BaseUrl+"usercenter/shopaddress";
    //查询账号余额  头像  昵称
    public static String UserDetail=BaseUrl+"usercenter/UserDetail";
    //文件上传
    public static String Upload=BaseUrl+"usercenter/Upload";



}
