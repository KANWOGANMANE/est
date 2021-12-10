<template>
  <div class="warpBox">
    <el-row type="flex" class="contentWidth">
      <el-col v-for="item in contentList" :key="item.id">
        <img :src="item.img" alt />
        <div>
          <h3>{{ item.title }}</h3>
          <span>{{ item.subText }}</span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      contentList: [
        {
          title: "高价",
          img: require("@/assets/imgs/footer1.png"),
          subText: "百家竞价 价格公道",
          id: 1
        },
        {
          title: "便捷",
          img: require("@/assets/imgs/footer2.png"),
          subText: "交易便利 节省时间",
          id: 2
        },
        {
          title: "安全",
          img: require("@/assets/imgs/footer3.png"),
          subText: "放心维修 保障有我",
          id: 3
        },
        {
          title: "环保",
          img: require("@/assets/imgs/footer4.png"),
          subText: "综合利用 避免污染",
          id: 4
        }
      ]
    };
  }
};
</script>
<style lang="less" scoped>
@import "./homeFooterPlugn.less";
</style>
