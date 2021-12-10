import Vue from "vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import "./assets/theme/index.css";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { getUserData, setUserData } from "./assets/utils";

Vue.use(ElementUI);

Vue.config.productionTip = false;

router.beforeEach((to, from, next) => {
  //是否显示头部nav bar
  if (to.name !== "login") {
    store.dispatch("showNavBar", true);
  } else {
    store.dispatch("showNavBar", false);
  }

  //微信登陆重定向
  if (to.name === "redirect") {
    setUserData(to.query.token);
    next({
      path: to.query.redirect || "/"
    });
  }

  // 路由进入下一个路由对象前，判断是否需要登陆
  if (to.matched.some(res => res.meta.requireAuth)) {
    let userData = getUserData();
    // 未登录和已经登录的处理
    if (userData.token === undefined) {
      // 执行到此处说明没有登录，君可按需处理之后再执行如下方法去登录页面
      next({
        path: "/login",
        query: {
          redirect: to.fullPath
        }
      });
    } else {
      // 用户信息是否过期验证
      let overdueTime = userData.overdueTime;
      let nowTime = +new Date();
      // 登陆过期和未过期
      if (nowTime > overdueTime) {
        next({
          path: "/login",
          query: {
            redirect: to.fullPath
          }
        });
      } else {
        next();
      }
    }
  } else {
    next();
  }
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
