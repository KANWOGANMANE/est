//是首页商品种类右侧的的弹出的那个白框 -->里面有品牌商品的那个
export default {
  functional: true,
  render: function(createElement, context) {
    let name = null;
    if (context.props.brandList) {
      name = context.props.brandList.modelList.map(item => {
        return createElement(
          "span",
          {
            on: {
              click: () => context.props.modelClick(item.id)
            }
          },
          item.modelName
        );
      });

      name.unshift(
        createElement(
          "span",
          {
            on: {
              click: () =>
                context.props.brandClick(
                  context.props.brandList.id,
                  context.props.classifyIndex,
                  context.props.classifyId
                )
            }
          },
          context.props.brandList.brandName
        )
      );
      name.unshift(
        createElement("span", {
          domProps: {
            innerHTML: '更多<i class="el-icon-arrow-right"></i>'
          },
          on: {
            click: () =>
              context.props.brandClick(
                context.props.brandList.id,
                context.props.classifyIndex,
                context.props.classifyId
              )
          }
        })
      );
    }
    return name;
  }
};
