<template>
  <div class="home">
    <Search :littlePhone="true">
      <SearchTab showItem="all" />
    </Search>
    <Banner :list="treeList" />
    <ContentBox>
      <HomeContentTitle
        :title="{
          titleContent: '旧机维修',
          subText: '维修 / 便捷 / 安全',
          hasBtn: true
        }"
        :slogan="sloganTop"
      />
      <HomeTop />
      <!-- <TypeChange v-bind="$attrs" v-on="$listeners"  :treelist="treeList" /> -->
    </ContentBox>
    <ContentBox>
      <HomeContentTitle
        :title="{
          titleContent: '以旧换新',
          subText: '旧机回收抵扣心机款',
          hasBtn: false
        }"
        :slogan="sloganBottom"
      />
      <el-row type="flex" class="bottomBox">
        <Homebottom />
      </el-row>
    </ContentBox>
    <Footer>
      <FooterPlugn />
    </Footer>
  </div>
</template>

<script>
// @ is an alias to /src
import { classifyTree } from "@/http";
import Banner from "@/components/banner"; //banner区域组件
import ContentBox from "@/components/contentBox"; //宽度限制容器
import HomeContentTitle from "@/components/homeContentTitle"; //首页各个大类别的标题
// import TypeChange from "@/components/typeChange";
import Homebottom from "@/components/homeBottom"; //以旧换新 里面的展示项目
import HomeTop from "@/components/homeTop"; //旧机维修
import Footer from "@/components/footer";
import FooterPlugn from "@/components/homeFooterPlugn"; //是底部多出来的那个绿色的
import Search from "@/components/search"; //是搜索栏的整个盒子
import SearchTab from "@/components/searchTab"; //是搜索栏的那个旧机维修的那几个东西

export default {
  name: "home",
  data() {
    return {
      treeList: null,
      sloganTop: [
        {
          icon: require("@/assets/imgs/home_icon1.png"),
          text: "旧机维修",
          id: "1"
        },
        {
          icon: require("@/assets/imgs/home_icon2.png"),
          text: "在线下单",
          id: "2"
        },
        {
          icon: require("@/assets/imgs/home_icon3.png"),
          text: "专业质检",
          id: "3"
        },
        {
          icon: require("@/assets/imgs/home_icon4.png"),
          text: "上门服务",
          id: "4"
        }
      ],
      sloganBottom: [
        {
          icon: require("@/assets/imgs/home_icon5.png"),
          text: "正品自营 全国联保",
          id: "1"
        },
        {
          icon: require("@/assets/imgs/home_icon6.png"),
          text: "任意品牌 旧机抵扣",
          id: "2"
        },
        {
          icon: require("@/assets/imgs/home_icon7.png"),
          text: "2小时闪 电直送",
          id: "3"
        },
        {
          icon: require("@/assets/imgs/home_icon8.png"),
          text: "专业擦除 隐私保障",
          id: "4"
        }
      ]
    };
  },
  components: {
    Banner,
    ContentBox,
    HomeContentTitle,
    // TypeChange,
    Homebottom,
    HomeTop,
    Footer,
    FooterPlugn,
    Search,
    SearchTab
  },
  created() {
    classifyTree.call(this, "").then(res => {
      this.treeList = res.data.data;
      // console.log(this.$store);
    });
  }
};
</script>
