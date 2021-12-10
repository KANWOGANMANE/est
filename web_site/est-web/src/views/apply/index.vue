<template>
  <div>
    <div v-html="html"></div>
    <Search :stepActive="2" :apply="true" />
    <div>
      <Breadcrumb :breadcrumbData="breadcrumbData" />
      <div class="warpBox">
        <div class="left bgWhite">
          <el-form
            label-position="right"
            label-width="70px"
            :model="formLabelAlign"
            :rules="rules"
            ref="formLabelAlign"
          >
            <el-form-item label="我的地址" prop="adress">
              <el-input v-model="formLabelAlign.adress"></el-input>
            </el-form-item>
            <el-form-item label="预约时间" prop="appintDate">
              <el-select
                v-model="formLabelAlign.appintDate"
                placeholder="请选择"
              >
                <el-option
                  v-for="(item, index) in dateList"
                  :key="item.day + item.timeQuantum"
                  :label="
                    item.month +
                      '月' +
                      item.day +
                      '日' +
                      item.week +
                      item.timeQuantum
                  "
                  :value="index"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="手机号码" prop="phone">
              <el-input
                v-model="formLabelAlign.phone"
                :disabled="!!timer"
                placeholder="请输入您的手机号"
              ></el-input>
              <el-button
                type="info"
                plain
                :disabled="getSmsButton"
                @click="getVerifyCode"
                >{{ timer || "获取验证码" }}</el-button
              >
            </el-form-item>
            <el-form-item label="验证码" prop="sms">
              <el-input v-model="formLabelAlign.sms"></el-input>
            </el-form-item>

            <div class="formBottom">
              <el-button type="success" @click="submit">提交订单</el-button>
              <p>
                提交订单表示同意
                <a>《用户协议》</a>
                及
                <a>《隐私政策》</a>
                并确认机器来源合法
              </p>
            </div>
          </el-form>
        </div>
        <div class="right">
          <div class="bgWhite rightContent">
            <p>维修清单</p>
            <div>
              <div class="product">
                <!-- <img
                  v-if="evaluateAssess.model"
                  class="rightImg"
                  :src="evaluateAssess.model.faceImg"
                /> -->
                <div
                  class="rightImg"
                  :style="
                    evaluateAssess.model
                      ? {
                          background: `url(${evaluateAssess.model.faceImg})`,
                          'background-size': 'contain'
                        }
                      : ''
                  "
                ></div>
                <div>
                  <p>
                    {{ evaluateAssess.model && evaluateAssess.model.modelName }}
                  </p>
                  <p>
                    <span>¥{{ evaluateAssess.price }}</span>
                  </p>
                </div>
              </div>
              <!-- <span
                class="product-type"
                v-for="item in evaluateAssess.detailList"
                :key="item.id"
              >{{item.optionName}}</span> -->

              <div class="expand">
                物价详情
                <i class="el-icon-caret-bottom"></i>
              </div>
            </div>
            <div class="horizontal"></div>
            <div class="total">
              <img src="@/assets/imgs/question.png" />
              合计（1件）：
              <span>¥{{ evaluateAssess.subscription }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Footer />
  </div>
</template>
<script>
import {
  maintainSubmit,
  payAliPay,
  verifyCode,
  evaluateAssessInfo
} from "@/http";
import {
  dateList,
  validateRequire,
  validatePhone,
  countDown
} from "@/assets/utils";

import Search from "@/components/search"; //是搜索栏的整个盒子
import Footer from "@/components/footer";
import Breadcrumb from "@/components/breadcrumb"; //面包屑

export default {
  components: {
    Search,
    Breadcrumb,
    Footer
  },
  data() {
    return {
      html: "",
      evaluateAssess: {}, //评估信息详情
      rules: {
        adress: [
          { validator: validateRequire, trigger: "blur" },
          { max: 100, message: "请入如100字以内", trigger: "blur" }
        ],
        appintDate: [{ validator: validateRequire, trigger: "blur" }],
        phone: [
          {
            validator: (...parmas) => validatePhone.call(this, ...parmas),
            trigger: ""
          }
        ],
        sms: [{ validator: validateRequire, trigger: "blur" }]
      },
      formLabelAlign: {
        adress: "", //用户地址
        appintDate: "", //用户所选日期
        phone: "", //用户手机号
        sms: "" //短信验证码
      },
      dateList: dateList(),
      breadcrumbData: [
        {
          name: "维修评估",
          path: ""
        },
        {
          name: "填写信息",
          path: ""
        }
      ], //面包屑数据
      timer: false, //倒计时
      getSmsButton: true //获取短信验证码状态
    };
  },
  created() {
    //获取维修清单详情
    evaluateAssessInfo({ id: this.$route.query.id }).then(res => {
      this.evaluateAssess = res.data.data;
    });
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
    getVerifyCode() {
      this.countDownStart();
      verifyCode({ phone: this.formLabelAlign.phone, codeType: 3 });
    },
    //提交订单
    submit() {
      //验证
      this.$refs.formLabelAlign.validate(valid => {
        if (!valid) return;
        let selectedItem = this.dateList[this.formLabelAlign.appintDate];
        let obj = {
          evaluateId: this.$route.query.id,
          phone: this.formLabelAlign.phone,
          adress: this.formLabelAlign.adress,
          appintDate:
            selectedItem.year +
            "-" +
            selectedItem.month +
            "-" +
            selectedItem.day,
          temporalInterval: selectedItem.temporalInterval,
          sms: this.formLabelAlign.sms
        };
        maintainSubmit(obj).then(res => {
          //这里有可能会返回失败结果
          payAliPay({ orderNo: res.data.data.orderNo });
        });
      });
    }
  }
};
</script>
<style lang="less" scoped>
@import "./apply.less";
</style>
