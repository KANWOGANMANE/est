<template>
  <el-row type="flex" class="warpBox">
    <div class="active">旧机维修</div>
    <template v-if="showItem === 'all'">
      <div>旧机维修</div>
      <div>以旧换新</div>
    </template>
  </el-row>
</template>
<script>
export default {
  props: ["showItem"]
};
</script>
<style lang="less" scoped>
@import "./searchTab.less";
</style>
