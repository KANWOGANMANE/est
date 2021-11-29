package cn.gdrj.est.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 商品估计请求的参数对象
 *
 * @author jun
 * @date 2021/11/16
 */
@Data
public class AssessParam {
    @NotNull(message = "商品型号id不能为空")
    private Long modelId;

    @Size(min = 1,message ="商品故障选项id不能为空")
    private List<Long> optionIds;
}
