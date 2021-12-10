<template>
  <div class="contentWidth boxSetting bgWhite boxShadow">
    <slot></slot>
  </div>
</template>

<script>
export default {
  props: [],
  data() {
    return {};
  },
  mounted() {}
};
</script>

<style lang="less" scoped>
@import "./contentBox.less";
</style>
