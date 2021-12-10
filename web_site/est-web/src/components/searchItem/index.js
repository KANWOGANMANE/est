//商品列表 页面中的每一个 结果项 -->就是iphonex什么的
export default {
  functional: true,
  render: function(createElement, context) {
    return createElement(
      "div",
      {
        class: "searchItem bgWhite",
        on: {
          click: () => context.props.itemClick(context.props.item.id)
        }
      },
      [
        createElement("img", {
          attrs: {
            src:
              context.props.item.faceImg || require("@/assets/imgs/xiaomi9.jpg")
          }
        }),
        createElement("p", context.props.item.modelName)
      ]
    );
  }
};
