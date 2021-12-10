<template>
  <div>
    <div class="banner"></div>
    <div class="content bgWhite">
      <img src="@/assets/imgs/logo.png" alt />

      <div class="login-mode">
        <span
          :class="{ 'selected-mode': !loginMode }"
          @click="changeLoginMode(0)"
          >手机验证登录</span
        >
        <span
          :class="{ 'selected-mode': loginMode }"
          @click="changeLoginMode(1)"
          >账号密码登录</span
        >
      </div>

      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item class="login_sms" prop="inputName">
          <el-input
            :placeholder="loginMode ? '账号' : '手机号'"
            v-model="form.inputName"
            :disabled="!!timer"
          >
            <img slot="prefix" src="@/assets/imgs/phone_number.png" />
          </el-input>
          <el-button
            v-if="!loginMode"
            type="info"
            :disabled="getSmsButton"
            @click="getVerifyCode"
            v-html="timer || '获取验证码'"
          ></el-button>
        </el-form-item>
        <el-form-item prop="inputPwd">
          <el-input
            :placeholder="loginMode ? '密码' : '短信验证码'"
            v-model="form.inputPwd"
          >
            <img slot="prefix" src="@/assets/imgs/sms.png" />
          </el-input>
        </el-form-item>
      </el-form>

      <div class="login">
        <el-button type="success" @click="login">登录</el-button>
      </div>

      <div class="considerations">
        <p>未注册二手兔账号的手机号，登录时将自动创建二手兔账号</p>
        <p>
          我已阅读并同意
          <a>《用户协议》</a>及
          <a>《隐私政策》</a>
        </p>
      </div>

      <div class="other-signin">
        <p>其他登录方式</p>
        <div>
          <div @click="wechat">
            <img src="@/assets/imgs/login_weixin.png" alt />
          </div>
          <div>
            <img src="@/assets/imgs/login_weibo.png" alt />
          </div>
          <div>
            <img src="@/assets/imgs/login_qq.png" alt />
          </div>
        </div>
      </div>
    </div>

    <div class="footer">
      <p>关于我们 | 安全保 | 帮助中心</p>
      <p>友情链接： 北京二手手机 | 北京二手手机维修 | 信用租机 | 二手手机</p>
    </div>
  </div>
</template>
<script>
import { verifyCode, loginSms, userInfo, loginWechat } from "@/http";
import {
  countDown,
  setUserData,
  validateRequire,
  validatePhone
} from "@/assets/utils";
export default {
  data() {
    return {
      rules: {
        inputName: [
          {
            validator: (...parmas) => validatePhone.call(this, ...parmas),
            trigger: ""
          }
        ],
        inputPwd: [{ validator: validateRequire, trigger: "blur" }]
      },
      form: {
        inputName: "", //用户手机号或账号
        inputPwd: "" //用户验证码或密码
      },
      loginMode: 0, //登陆模式:0手机登录,1账号登陆
      timer: false, //倒计时
      getSmsButton: true //获取短信验证码状态
    };
  },
  methods: {
    //开始倒计时
    countDownStart() {
      this.getSmsButton = true;
      this.timer = 60 + "s";
      countDown(
        60,
        res => (this.timer = res + "s"),
        () => {
          this.timer = false;
          this.getSmsButton = false;
        }
      );
    },
    //切换登陆方式
    changeLoginMode(index) {
      this.loginMode = index;
      this.$refs.form.resetFields();
    },
    getVerifyCode() {
      this.countDownStart();
      verifyCode({ phone: this.form.inputName, codeType: 0 });
    },
    //弹出框
    msgBox(msg) {
      this.$msgbox({
        title: "提示",
        type: "warning",
        center: true,
        message: this.$createElement("div", msg, { key: msg })
      });
    },
    login() {
      this.$refs.form.validate(valid => {
        if (!valid) return;
        loginSms({
          phone: this.form.inputName,
          sms: this.form.inputPwd
        })
          .then(res => {
            //判断有错误提示的情况
            if (!res.data.data) return Promise.reject(res.data.msg);

            setUserData({ token: res.data.data.token });

            //用户信息请求
            return userInfo();
          })
          .then(res => {
            setUserData({
              token: res.data.data.token,
              userInfo: res.data.data.userName
            });
            this.$router.push(this.$route.query.redirect || "/");
          })
          .catch(err => {
            this.msgBox(err);
          });
      });
    },
    wechat() {
      loginWechat();
    }
  }
};
</script>
<style lang="less" scoped>
@import "./login.less";
</style>
