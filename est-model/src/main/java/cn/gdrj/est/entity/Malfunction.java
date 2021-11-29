package cn.gdrj.est.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * malfunction
 * @author 
 */
@Data
public class Malfunction implements Serializable {
    private Long id;

    /**
     * 商品型号id
     */
    private Long modelId;

    /**
     * 故障名称
     */
    private String title;

    /**
     * 是否有提示（0:否,1:是）
     */
    private Integer isHint;

    /**
     * 提示信息标题
     */
    private String hintTitle;

    /**
     * 提示信息
     */
    private String hintInfo;

    /**
     * 提示图片
     */
    private String hintImg;

    /**
     * 维修最高价
     */
    private BigDecimal maintainTopPrice;

    /**
     * 创建人
     */
    private Long createdUserId;

    /**
     * 修改人
     */
    private Long updatedUserId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 是否删除(0:否,1:是)
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Malfunction other = (Malfunction) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getModelId() == null ? other.getModelId() == null : this.getModelId().equals(other.getModelId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getIsHint() == null ? other.getIsHint() == null : this.getIsHint().equals(other.getIsHint()))
            && (this.getHintTitle() == null ? other.getHintTitle() == null : this.getHintTitle().equals(other.getHintTitle()))
            && (this.getHintInfo() == null ? other.getHintInfo() == null : this.getHintInfo().equals(other.getHintInfo()))
            && (this.getHintImg() == null ? other.getHintImg() == null : this.getHintImg().equals(other.getHintImg()))
            && (this.getMaintainTopPrice() == null ? other.getMaintainTopPrice() == null : this.getMaintainTopPrice().equals(other.getMaintainTopPrice()))
            && (this.getCreatedUserId() == null ? other.getCreatedUserId() == null : this.getCreatedUserId().equals(other.getCreatedUserId()))
            && (this.getUpdatedUserId() == null ? other.getUpdatedUserId() == null : this.getUpdatedUserId().equals(other.getUpdatedUserId()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getModelId() == null) ? 0 : getModelId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getIsHint() == null) ? 0 : getIsHint().hashCode());
        result = prime * result + ((getHintTitle() == null) ? 0 : getHintTitle().hashCode());
        result = prime * result + ((getHintInfo() == null) ? 0 : getHintInfo().hashCode());
        result = prime * result + ((getHintImg() == null) ? 0 : getHintImg().hashCode());
        result = prime * result + ((getMaintainTopPrice() == null) ? 0 : getMaintainTopPrice().hashCode());
        result = prime * result + ((getCreatedUserId() == null) ? 0 : getCreatedUserId().hashCode());
        result = prime * result + ((getUpdatedUserId() == null) ? 0 : getUpdatedUserId().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modelId=").append(modelId);
        sb.append(", title=").append(title);
        sb.append(", isHint=").append(isHint);
        sb.append(", hintTitle=").append(hintTitle);
        sb.append(", hintInfo=").append(hintInfo);
        sb.append(", hintImg=").append(hintImg);
        sb.append(", maintainTopPrice=").append(maintainTopPrice);
        sb.append(", createdUserId=").append(createdUserId);
        sb.append(", updatedUserId=").append(updatedUserId);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}