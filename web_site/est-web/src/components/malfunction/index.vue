<template>
  <div class="rightWight">
    <div class="rightTitle" type="flex" @click="selectedClick">
      <div class="titleSpan">{{ title }}</div>
      <div class="optionName">{{ selectData.optionName }}</div>
      <div v-if="selectData.optionName" class="changeSapn">修改</div>
    </div>
    <div class="horizontal" v-if="!selectData.optionName"></div>
    <div class="rightButton" v-if="!selectData.optionName">
      <el-button
        v-for="(item, index) in optionsList"
        :key="item.id"
        @click="optionclick(item.id, index)"
        >{{ item.optionName }}</el-button
      >
    </div>
  </div>
</template>
<script>
export default {
  /**
   * optionsList 选项列表
   * title 选项标题
   * optionSelect 选中事件
   * renderIndex 渲染到的行的下标
   */
  props: ["optionsList", "title", "optionSelect", "renderIndex"],
  data() {
    return {
      selectData: {} //所选中项的内容
    };
  },
  methods: {
    //选中后点击修改事件
    selectedClick() {
      this.selectData = {};
    },
    //故障选择事件
    optionclick(id, index) {
      this.selectData = this.optionsList[index];
      this.optionSelect(id, index, this.renderIndex);
    }
  }
};
</script>
