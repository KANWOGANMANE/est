<template>
  <div
    class="el-step is-horizontal"
    :class="[active >= index ? 'step-active' : '']"
  >
    <div class="el-step__head">
      <div class="process-line"></div>
      <div class="el-step__icon is-text">
        <div class="el-step__icon-inner">{{ index }}</div>
      </div>
    </div>
    <div class="el-step__main">
      <div class="el-step__title" :class="[active >= index ? '' : 'is-wait']">
        {{ title }}
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ["active", "index", "title"]
};
</script>
<style lang="less" scoped>
@import "./steps.less";
</style>
