import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    showNavBar: false, //控制nav bar 是否显示
    classifyTreeList: [] //保存商品类别信息
  },
  mutations: {
    showNavBar(state, bool) {
      return (state.showNavBar = bool);
    },
    /**
     * 用来保存商品类别信息list
     * @param {*} state
     * @param {Array} list //储存的商品类别信息list
     */
    setClassifyTreeList(state, list) {
      return (state.classifyTreeList = list);
    }
  },
  actions: {
    showNavBar(context, bool) {
      context.commit("showNavBar", bool);
    }
  },
  getters: {
    /**
     * 获取面包屑数据
     * classifyId 商品类型
     * brandId 品牌
     */
    getNavData: state => modelDetail => {
      let { classifyId, brandId, modelName } = modelDetail;
      let classify = getName(state.classifyTreeList, classifyId);
      let brand = getName(classify.brandList, brandId);
      return [
        {
          name: "首页",
          path: "/"
        },
        {
          name: classify.classifyName,
          path: ""
        },
        {
          name: brand.brandName,
          path: ""
        },
        {
          name: modelName,
          path: ""
        }
      ];
    }
  }
});
function getName(list, id) {
  for (let item of list) {
    // console.log('getName times');
    if (item.id === id) {
      return item;
    }
  }
}
