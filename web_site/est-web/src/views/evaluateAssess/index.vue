<template>
  <div>
    <Search :stepActive="1" />
    <div class="warp">
      <div class="top">
        <div class="left bgWhite">
          <img
            :src="evaluateAssess.model && evaluateAssess.model.contentImg"
            alt
            class="imgNone"
          />
          <p>{{ evaluateAssess.model && evaluateAssess.model.modelName }}</p>
          <el-button type="text">重选机型 ></el-button>
        </div>
        <div class="center bgWhite">
          <p>
            <span>评价报价</span>
            <i class="el-icon-question"></i>
          </p>
          <div>
            <b class="color-red">¥</b>
            <h4 class="color-red">{{ evaluateAssess.price }}</h4>
            <span class="color-orange"
              >(订金金额：¥{{ evaluateAssess.subscription }})</span
            >
          </div>
          <el-button type="success" @click="fixOrder">预约维修</el-button>
        </div>
        <div class="right bgWhite">
          <div class="rightFlex">
            <p>
              <i class="el-icon-circle-check"></i>
              <span>价格公道</span>
            </p>
            <p>
              <i class="el-icon-circle-check"></i>
              <span>服务快捷</span>
            </p>
            <p>
              <i class="el-icon-circle-check"></i>
              <span>隐私保护</span>
            </p>
            <p>
              <i class="el-icon-circle-check"></i>
              <span>服务快捷</span>
            </p>
          </div>
        </div>
      </div>
      <div class="bottom">
        <div class="goods-labels bgWhite">
          <!-- <span v-for="item of 20" :key="item">大陆国行</span> -->
          <span v-for="item of evaluateAssess.detailList" :key="item.id">
            {{ item.optionName }}
          </span>
        </div>
        <div class="tips">
          <img src="@/assets/imgs/safeiconsmall.png" alt />
          <span>安卓安全小贴士</span>
          <span
            >为保障你的安卓手机隐私不在快递途中、质检、交易过程中泄露，我们粉碎你的隐私，更以严格的政策来管理所有数据的处理方式。</span
          >
        </div>
      </div>
    </div>
    <Footer />
  </div>
</template>
<script>
import { evaluateAssessInfo } from "@/http";
import Search from "@/components/search"; ////是搜索栏的整个盒子
import Footer from "@/components/footer";

export default {
  components: {
    Search,
    Footer
  },
  data() {
    return {
      evaluateAssess: {}
    };
  },
  methods: {
    fixOrder() {
      this.$router.push({
        path: "/apply",
        query: { id: this.$route.query.id }
      });
    }
  },
  created() {
    evaluateAssessInfo({ id: this.$route.query.id }).then(res => {
      this.evaluateAssess = res.data.data;
    });
  }
};
</script>
<style lang="less" scoped>
@import "./evaluateAssess.less";
</style>
