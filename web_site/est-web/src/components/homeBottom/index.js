//首页的 以旧换新 里面的展示项目
export default {
  functional: true,
  render: function(createElement) {
    let showItems = [
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      },
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      },
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      },
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      },
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      },
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      },
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      },
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      },
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      },
      {
        price: 123456,
        img: require("@/assets/imgs/xiaomi9.jpg"),
        name: "小米9"
      }
    ];
    return showItems.map(item => {
      return createElement("div", { class: "bottomItem" }, [
        createElement("img", {
          attrs: {
            src: item.img
          }
        }),
        createElement("p", item.name),
        createElement("p", {
          domProps: {
            innerHTML: `官方售价<span>￥${item.price}</span>`
          }
        })
      ]);
    });
  }
};
