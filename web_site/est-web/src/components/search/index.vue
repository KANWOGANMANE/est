<template>
  <div class="bgWhite">
    <div class="contentWidth">
      <div class="warpClass">
        <el-row type="flex" align="center" class="logeBox">
          <el-col>
            <img :src="Logo" alt @click="$router.push('/')" />
          </el-col>

          <el-col v-if="littlePhone">
            <img :src="LittlePhone" alt />
          </el-col>
          <el-col v-if="littlePhone">
            <p>1分钟快速备份手机数据</p>
            <p>轻松操作快如闪电</p>
          </el-col>

          <el-col v-if="product">旧机维修</el-col>
          <el-col v-if="product">以旧换新</el-col>

          <el-col v-if="apply" class="apply">旧机维修</el-col>
        </el-row>

        <el-row v-if="!stepActive" type="flex" class="inputBox">
          <el-input v-model="name" placeholder="请输入机器型号"></el-input>
          <el-button type="primary" @click="onSubmit">
            <img :src="searchIcon" />
          </el-button>
        </el-row>

        <div v-else class="my-step">
          <Steps
            v-for="(item, index) in stepList"
            :key="item"
            :active="stepActive"
            :index="index + 1"
            :title="item"
          />
        </div>
      </div>

      <slot></slot>
    </div>
  </div>
</template>
<script>
import Steps from "@/components/steps";
export default {
  props: ["stepActive", "littlePhone", "product", "apply", "getSearchData"],
  components: { Steps },
  data() {
    return {
      name: "",
      Logo: require("@/assets/imgs/logo.png"),
      LittlePhone: require("@/assets/imgs/littlePhone.png"),
      searchIcon: require("@/assets/imgs/searchIcon.png"),
      stepList: ["维修估价", "填写信息", "预约下单"]
    };
  },
  methods: {
    onSubmit() {
      if (this.getSearchData) {
        this.$router.push({ query: { name: this.name || "" } });
        this.getSearchData();
      } else {
        this.$router.push({
          name: "commodityList",
          query: { name: this.name || "" }
        });
      }
    }
  }
};
</script>
<style lang="less" scoped>
@import "./search.less";
</style>
