<template>
  <div>
    <Search :littlePhone="true" :getSearchData="getSearchData">
      <SearchTab />
      <Breadcrumb :breadcrumbData="breadcrumbData" />
    </Search>

    <div class="contentWidth">
      <img :src="img" alt class="helpImg" />
      <ContentBox>
        <Classification
          v-if="treeList && brandList"
          :treeList="treeList"
          :brandList="brandList"
          :classifyId="classifyId"
          :brandId="brandId"
          :classifyClick="classifyClick"
          :brandClick="brandClick"
        />
      </ContentBox>

      <div class="searchItemBox">
        <SearchItem
          v-for="item in itemList"
          :key="item.id"
          :item="item"
          :itemClick="itemClick"
        />
      </div>

      <el-pagination
        background
        layout="prev, pager, next"
        :page-count="pageCount"
        @current-change="currentPage => getSearchData(currentPage)"
      ></el-pagination>
    </div>

    <Footer></Footer>
  </div>
</template>
<script>
import { classifyTree, modelSearch } from "@/http";
import Search from "@/components/search"; //是搜索栏的整个盒子
import SearchTab from "@/components/searchTab"; //是搜索栏的那个旧机维修的那几个东西
import ContentBox from "@/components/contentBox"; //限制宽度容器
import Footer from "@/components/footer";
import Classification from "@/components/classification"; //商品类别和品牌
import SearchItem from "@/components/searchItem"; //商品列表 页面中的每一个 结果项 -->就是iphonex什么的
import Breadcrumb from "@/components/breadcrumb"; //面包屑

export default {
  data() {
    return {
      img: require("@/assets/imgs/commodity_banner.png"),
      treeList: null, //类别数据
      brandList: null, //品牌数据
      itemList: null, //商品数据
      modelName: this.$route.query.name || "",
      classifyId: this.$route.query.classifyId || 1, //类别id
      classifyIndex: this.$route.query.classifyIndex || 0, //类别下标
      brandId: this.$route.query.brandId || 1, //品牌id
      brandindex: 0, //品牌下标
      pageCount: 0, //分页
      breadcrumbData: [
        {
          name: "首页",
          path: "/"
        },
        {
          name: "全部维修分类",
          path: ""
        },
        {
          name: "手机",
          path: ""
        }
      ] //面包屑数据
    };
  },
  components: {
    Footer,
    Search,
    SearchTab,
    ContentBox,
    Classification,
    Breadcrumb,
    SearchItem
  },
  computed: {
    searchQueryData() {
      // console.log('this.$route.query.name',this.$route.query.name,this.modelName);
      if (this.$route.query.name) {
        return {
          modelName: this.$route.query.name
        };
      } else {
        return {
          classifyId: this.classifyId,
          brandId: this.brandId
        };
      }
    }
  },
  methods: {
    //点击具体商品事件
    itemClick(id) {
      this.$router.push({ name: "product", query: { modelId: id } });
    },
    //类别选择点击事件
    classifyClick(id, index) {
      this.classifyId = id;
      this.classifyIndex = index;
      this.modelName = "";
      this.brandList = this.treeList[index]["brandList"];
      this.getSearchData();
    },
    //品牌选择点击事件
    brandClick(id, index) {
      this.brandId = id;
      this.brandindex = index;
      this.modelName = "";
      this.getSearchData();
    },
    //获取展示数据
    getSearchData(pageNo = 1) {
      modelSearch({ pageNo, ...this.searchQueryData }).then(res => {
        this.itemList = res.data.data.list;
        this.pageCount = res.data.data.pages;
      });
    }
  },
  created() {
    //获取类别信息
    classifyTree.call(this, "").then(res => {
      this.treeList = res.data.data;
      this.brandList = this.treeList[this.classifyIndex]["brandList"];
    });
    //获取默认展示数据
    this.getSearchData();
  }
};
</script>
<style lang="less" scoped>
@import "./commodityList.less";
</style>
