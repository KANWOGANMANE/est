<template>
  <div class="footerBox boxShadow">
    <slot></slot>
    <div class="warpbox contentWidth">
      <el-row type="flex" class="top">
        <el-col v-for="item in contentList" :key="item.id">
          <h3>{{ item.title }}</h3>
          <p v-for="(text, index) in item.list" :key="item.id + '-' + index">
            {{ text }}
          </p>
        </el-col>
      </el-row>
      <p class="bottom">
        <span>友情链接：</span>
        &nbsp;
        <span>北京二手手机</span>
        &nbsp; | &nbsp;
        <span>北京二手手机维修</span>
        &nbsp; | &nbsp;
        <span>信用相机</span>
        &nbsp; | &nbsp;
        <span>二手手机</span>
      </p>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      contentList: [
        {
          title: "交易方式",
          id: 1,
          list: ["上海交易方式", "北京交易方式", "全国交易方式"]
        },
        {
          title: "质检说明",
          id: 2,
          list: ["质检名词解释", "专业质检说明"]
        },
        {
          title: "帮助中心",
          id: 3,
          list: ["常见问题", "用户协议", "隐私政策"]
        },
        {
          title: "关于我们",
          id: 4,
          list: ["了解我们", "加入我们", "商务合作", "维修商合作", "门店加盟"]
        },
        {
          title: "合作伙伴",
          id: 5,
          list: ["京东"]
        }
      ]
    };
  }
};
</script>
<style lang="less" scoped>
@import "./footer.less";
</style>
