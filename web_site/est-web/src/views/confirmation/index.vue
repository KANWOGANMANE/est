<template>
  <div class="bgWhite">
    <Search :stepActive="3" :apply="true" />
    <div class="content-box">
      <div class="content">
        <img src="@/assets/imgs/fix-phone-icon.png" alt />
        <div>
          <p>预约已经成功，维修大师将按时上门，请保持电话畅通</p>
          <p>
            <span>{{ timer }}秒</span> 后将跳转至首页
          </p>
        </div>
      </div>
    </div>
    <Footer />
  </div>
</template>
<script>
import Search from "@/components/search"; //是搜索栏的整个盒子
import Footer from "@/components/footer";
import { countDown } from "@/assets/utils";
export default {
  components: {
    Search,
    Footer
  },
  data() {
    return {
      timer: 5
    };
  },
  methods: {
    refreshTimer(totalTime) {
      this.timer = totalTime;
    }
  },
  mounted() {
    countDown(this.timer, this.refreshTimer, () => this.$router.push("/"));
  }
};
</script>
<style lang="less" scoped>
@import "./confirmation.less";
</style>
