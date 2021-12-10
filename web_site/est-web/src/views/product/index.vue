<template>
  <div>
    <Search :product="true" />
    <Breadcrumb :breadcrumbData="breadcrumbData" />
    <div class="warpBox">
      <div class="left">
        <div class="detail bgWhite">
          <img
            :src="modelDetail.faceImg || modelDetail.contentImg"
            alt
            class="imgNone"
          />
          <p>{{ modelDetail.modelName }}</p>
          <el-button type="text">重选机型 ></el-button>

          <div class="safe-tips">
            <div class="title">
              <img src="@/assets/imgs/safeicon.png" />
              <span class="text">苹果安全小贴士</span>
            </div>
            <div class="content">
              <p class="brief">
                苹果手机只要恢复出厂设置，是无须担心数据泄露风险的，请放心回收。
                这是真的！美国FBI都破解不了。
              </p>
              <p>
                1.苹果的磁盘是基于单个文件进行加密的，因此，在iPhone上所存储的每个文件都有单独随机生成的密钥进行加密。经过多层加密之后，文件才得以安全地存储在iPhone的磁盘上。
              </p>
              <p>
                2.用户抹掉数据以及所有设置之后，就等于抹掉了所有文件的密钥。如果要想恢复数据，就需要破解逐层加密。因此通过iPhone官方提供的抹掉数据和设置后被恢复的可能几乎为零。
              </p>
              <p class>
                3.此前FBI也遇到了破解iPhone的难题。作为普通用户，
                iPhone的信息安全值得信赖。
              </p>
              <div class="whiteSpace"></div>
            </div>

            <div class="arrow">
              <i class="el-icon-arrow-down"></i>
            </div>
          </div>
        </div>
        <div class="bgWhite qrcode">
          <img src="@/assets/imgs/qrcode.png" alt />
          <div>
            <h4>扫码关注小程序</h4>
            <span>更多活动更高价</span>
          </div>
        </div>
      </div>

      <div>
        <div class="right bgWhite">
          <Malfunction
            v-for="(item, index) in showList"
            :key="item.id"
            :optionsList="item.optionsList"
            :title="item.title"
            :optionSelect="optionSelect"
            :renderIndex="index"
          />
          <div
            v-show="
              optionIds.length && optionIds.length >= evaluateSpecList.length
            "
            class="fixOrder"
          >
            <!-- <div class="fixOrder"> -->
            <el-button type="success" @click="fixOrder">预约维修</el-button>
          </div>
        </div>
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import { modelDetail, evaluateSpecList, evaluateAssess } from "@/http";
import Search from "@/components/search"; //是搜索栏的整个盒子
import Footer from "@/components/footer";
import Breadcrumb from "@/components/breadcrumb"; //面包屑
import Malfunction from "@/components/malfunction"; //故障选项

export default {
  components: {
    Footer,
    Search,
    Breadcrumb,
    Malfunction
  },
  data() {
    return {
      breadcrumbData: [], //面包屑数据
      modelDetail: {},
      evaluateSpecList: [], //故障列表
      showIndex: 1, //已经被选中过的下标
      optionIds: [] //选项ids（逗号拼接的评估信息选项ids）
    };
  },
  computed: {
    showList() {
      return this.evaluateSpecList.slice(0, this.showIndex);
    }
  },
  created() {
    modelDetail({ id: this.$route.query.modelId }).then(res => {
      this.modelDetail = res.data.data;
      //为面包屑数据赋值
      this.breadcrumbData = this.$store.getters.getNavData(this.modelDetail);
    });
    evaluateSpecList({ modelId: this.$route.query.modelId }).then(res => {
      this.evaluateSpecList = res.data.data;
    });
  },
  methods: {
    //传递给子组件用来逐级显示故障选项的
    optionSelect(id, index, renderIndex) {
      renderIndex + 1 >= this.showIndex && this.showIndex++;
      this.optionIds[renderIndex] = id;
    },
    fixOrder() {
      evaluateAssess({
        modelId: this.$route.query.modelId,
        optionIds: this.optionIds.join(",")
      }).then(res => {
        this.$router.push({
          name: "inquiry",
          query: { id: res.data.data.id, modelId: res.data.data.modelId }
        });
      });
    }
  }
};
</script>
<style lang="less" scoped src="./product.less"></style>
