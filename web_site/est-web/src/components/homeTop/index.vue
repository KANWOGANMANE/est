<template>
  <div class="warpBox">
    <el-row type="flex" class="top">
      <el-col class="active">手机</el-col>
      <el-col>笔记本</el-col>
      <el-col>平板电脑</el-col>
      <el-col>摄影摄像</el-col>
      <el-col>智能数码</el-col>
    </el-row>
    <el-row type="flex" class="center">
      <el-col v-for="(item, index) in iconList" :key="index">
        <img :src="item" alt />
      </el-col>
      <el-col>
        <i class="el-icon-more"></i>
      </el-col>
    </el-row>
    <el-row type="flex" class="bottom">
      <i class="arrow left"></i>
      <el-col v-for="(item, index) in phoneList" :key="index">
        <img :src="item.img" alt="" />
        <p>{{ item.name }}</p>
      </el-col>
      <i class="arrow right"></i>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      iconList: [
        require("@/assets/imgs/icon1.png"),
        require("@/assets/imgs/icon2.png"),
        require("@/assets/imgs/icon3.png"),
        require("@/assets/imgs/icon4.png"),
        require("@/assets/imgs/icon5.png"),
        require("@/assets/imgs/icon6.png"),
        require("@/assets/imgs/icon7.png"),
        require("@/assets/imgs/icon8.png")
      ],
      phoneList: [
        {
          img: require("@/assets/imgs/xiaomi9.jpg"),
          name: "小米9"
        },
        {
          img: require("@/assets/imgs/xiaomi9.jpg"),
          name: "小米9"
        },
        {
          img: require("@/assets/imgs/xiaomi9.jpg"),
          name: "小米9"
        },
        {
          img: require("@/assets/imgs/xiaomi9.jpg"),
          name: "小米9"
        },
        {
          img: require("@/assets/imgs/xiaomi9.jpg"),
          name: "小米9"
        }
      ]
    };
  }
};
</script>
<style lang="less" scoped>
@import "./homeTop.less";
</style>
