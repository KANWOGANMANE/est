<template>
  <div id="navBar" class="bg-color">
    <div class="wrap-box">
      <div>北京市</div>
      <div class="right-items">
        <a @click="() => $router.push('/login')">{{ userInfo || "登录" }}</a>
        <a>购物车<span>0</span>件</a>
        <a><span>关于我们</span></a>
        <a>企业回收</a>
        <a>开放平台</a>
        <a>帮助中心</a>
        <a>网站导航</a>
      </div>
    </div>
  </div>
</template>
<script>
import { getUserData } from "@/assets/utils";
export default {
  data() {
    return {
      userInfo: getUserData().userInfo //从方法中获取了一下用户信息
    };
  }
};
</script>
<style lang="less" scoped>
@import "navBar.less";
</style>
