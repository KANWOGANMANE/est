<template>
  <div class="fullScreen">
    <div class="contentWidth bannerSetting" @mouseleave="menuLeave">
      <div class="selectMenuWarp">
        <div
          class="selectMenu"
          v-for="(item, index) in list"
          :key="item.id"
          @mouseenter="menuEnter(index)"
          :class="[index === selectIndex ? 'bgWhite' : '']"
        >
          <div class="box" :key="item.id">
            <i :class="item.icon"></i>
            <span>{{ item.classifyName }}</span>
          </div>
        </div>
      </div>

      <div v-if="selectItem" class="subMenu boxShadow">
        <p v-for="item in selectItem.brandList" :key="item.id">
          <SubMenu
            :brandList="item"
            :classifyIndex="selectIndex"
            :classifyId="selectItem.id"
            :brandClick="brandClick"
            :modelClick="modelClick"
          />
        </p>
        <p>
          <el-button class="btn" plain @click="classifyClick(selectItem.id)">
            更多{{ selectItem.classifyName }}品牌
            <i class="el-icon-arrow-right"></i>
          </el-button>
        </p>
      </div>
    </div>
  </div>
</template>
<script>
import SubMenu from "../submenu";
export default {
  props: ["list"],
  data() {
    return {
      selectItem: null,
      selectIndex: null
    };
  },
  components: {
    SubMenu
  },
  methods: {
    //菜单进入
    menuEnter(index) {
      this.selectItem = this.list[index];
      this.selectIndex = index;
    },
    //菜单离开
    menuLeave() {
      this.selectItem = null;
      this.selectIndex = null;
    },
    //点击分类下显示更多
    classifyClick(classifyId) {
      this.$router.push({
        name: "commodityList",
        query: { classifyId: classifyId }
      });
    },
    //点击品牌下更多
    brandClick(brandId, classifyIndex, classifyId) {
      this.$router.push({
        name: "commodityList",
        query: { brandId, classifyIndex, classifyId }
      });
    },
    //点击具体型号
    modelClick(modelId) {
      this.$router.push({ name: "product", query: { modelId: modelId } });
    }
  }
};
</script>

<style lang="less" scoped>
@import "./banner.less";
</style>
