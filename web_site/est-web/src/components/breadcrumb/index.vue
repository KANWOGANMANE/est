<template>
  <div class="breadcrumbBox">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <template v-for="(item, index) in breadcrumbData">
        <el-breadcrumb-item
          v-if="item.path"
          :to="{ path: item.path }"
          :key="index"
          >{{ item.name }}</el-breadcrumb-item
        >
        <el-breadcrumb-item v-else :key="index">{{
          item.name
        }}</el-breadcrumb-item>
      </template>
    </el-breadcrumb>
  </div>
</template>
<script>
export default {
  props: ["breadcrumbData"]
};
</script>
<style lang="less" scoped>
@import "./breadcrumb.less";
</style>
