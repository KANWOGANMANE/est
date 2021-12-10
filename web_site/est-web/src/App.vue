<template>
  <div id="app">
    <!-- nav bar -->
    <NavBar v-if="showNavBar" />
    <router-view />
  </div>
</template>
<script>
import NavBar from "@/components/navBar";
export default {
  components: {
    NavBar
  },
  computed: {
    showNavBar() {
      return this.$store.state.showNavBar;
    }
  }
};
</script>
<style lang="less">
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  font-size: 14px;
}
/* 清除内外边距 */
body, h1, h2, h3, h4, h5, h6, hr, p, blockquote, /* structural elements 结构元素 */
dl, dt, dd, ul, ol, li, /* list elements 列表元素 */
pre, /* text formatting elements 文本格式元素 */
fieldset, lengend, button, input, textarea, /* form elements 表单元素 */
th, td {
  /* table elements 表格元素 */
  margin: 0;
  padding: 0;
}
body {
  background: rgba(245, 245, 245);
}
.contentWidth {
  margin: 0 auto;
  width: 1176px;
}
.bgWhite {
  background: #fff;
}
.boxShadow {
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}
.imgNone {
  display: block;
  background: olivedrab;
  height: 150px;
  width: 150px;
}
</style>
