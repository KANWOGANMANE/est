<template>
  <div>
    <el-row type="flex" justify="space-between" class="titleBox">
      <el-row type="flex" class="titleTextBox">
        <div class="title">{{ title.titleContent }}</div>
        <div class="subText">{{ title.subText }}</div>
      </el-row>
      <el-button v-if="title.hasBtn" type="success">免费估价</el-button>
    </el-row>
    <el-row type="flex">
      <el-col class="contentCenter" v-for="item in slogan" :key="item.id">
        <img :src="item.icon" alt />
        <span>{{ item.text }}</span>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  props: ["title", "slogan"]
};
</script>
<style lang="less" scoped>
@import "./homeContentTitle.less";
</style>
