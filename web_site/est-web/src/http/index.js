import Instance from "./http";
//获取商品类别信息
export const classifyTree = function(params) {
  return Instance.get("/api/home/classifyTree", params).then(res => {
    this.$store.commit("setClassifyTreeList", res.data.data);
    return Promise.resolve(res);
  });
};

//商品列表
export const modelSearch = params => Instance.get("/api/model/search", params);

//商品详情
export const modelDetail = params =>
  Instance.get(`/api/model/${params.id}/detail`);

//评估维度
export const evaluateSpecList = params =>
  Instance.get("/api/evaluate/specList", params);

//估价
export const evaluateAssess = params =>
  Instance.post("/api/evaluate/assess", params);

//评估信息详情
export const evaluateAssessInfo = params =>
  Instance.get(`/api/evaluate/${params.id}/assessInfo`);

//下单--维修
export const maintainSubmit = params =>
  Instance.post("/api/order/maintain/submit", params);

//发送短信验证码
export const verifyCode = params =>
  Instance.post("/api/sms/verifyCode", params);

//注册、登录（手机验证码）
export const loginSms = params => Instance.post("/api/user/login/sms", params);

//注册、登录（微信登录--同步接口）
export const loginWechat = () => location.replace("/api/user/login/wechat");

//获取用户信息
export const userInfo = () => Instance.get("/api/user/info");

//支付宝支付（同步请求）
export const payAliPay = params =>
  location.replace("/api/pay/aliPay?orderNo=" + params.orderNo);

///api/order/maintain/detail/{orderNo}维修订单详情
///api/users/logout登出
