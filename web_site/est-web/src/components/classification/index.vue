<template>
  <div class="warpBox">
    <el-row type="flex" class="first-row">
      <div>分类：</div>
      <div
        v-for="(item, index) in treeList"
        :key="item.id"
        :class="[item.id == classifyId ? 'active' : '']"
        @click="classifyHandel(item.id, index)"
      >
        {{ item.classifyName }}
      </div>
    </el-row>
    <div class="horizontal"></div>
    <el-row type="flex" :class="[more ? 'showMore' : '']">
      <div>品牌：</div>
      <div class="brand-box">
        <div class="listBox" ref="listBox">
          <div
            v-for="(item, index) in brandList"
            :key="item.id"
            :class="[item.id == brandId ? 'active' : '']"
            @click="brandClick(item.id, index)"
          >
            {{ item.brandName }}
          </div>
        </div>
      </div>

      <div v-show="listBoxheight" class="more" @click="moreClick">
        更多
        <i :class="[more ? 'el-icon-arrow-up' : 'el-icon-arrow-down']"></i>
      </div>
    </el-row>
  </div>
</template>
<script>
export default {
  props: [
    "treeList",
    "brandList",
    "classifyId",
    "brandId",
    "classifyClick",
    "brandClick"
  ],
  data() {
    return {
      listBoxheight: false,
      more: false
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.listBoxShow();
    });
  },
  methods: {
    classifyHandel(id, index) {
      this.classifyClick(id, index);
      this.listBoxShow();
    },
    //获取listBox的高度
    listBoxShow() {
      if (this.$refs.listBox.clientHeight > 100) {
        this.listBoxheight = true;
      }
    },
    //更多按钮
    moreClick() {
      this.more = !this.more;
    }
  }
};
</script>
<style lang="less" scoped>
@import "./classification.less";
</style>
