import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/commodity",
      name: "commodityList",
      component: () => import("./views/commodityList")
    },
    {
      path: "/product",
      name: "product",
      component: () => import("./views/product"),
      meta: {
        requireAuth: true // 只要此字段为true，必须做鉴权处理
      }
    },
    {
      path: "/inquiry",
      name: "inquiry",
      component: () => import("./views/evaluateAssess"),
      meta: {
        requireAuth: true // 只要此字段为true，必须做鉴权处理
      }
    },
    {
      path: "/apply",
      name: "apply",
      component: () => import("./views/apply"),
      meta: {
        requireAuth: true // 只要此字段为true，必须做鉴权处理
      }
    },
    {
      path: "/confirmation",
      name: "confirmation",
      component: () => import("./views/confirmation")
    },
    {
      path: "/login",
      name: "login",
      component: () => import("./views/login")
    },
    {
      path: "/redirect",
      name: "redirect"
    },
    {
      path: "*",
      name: "home",
      component: Home
    }
  ]
});
