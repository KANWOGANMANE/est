package cn.gdrj.est.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * appointment
 * @author 
 */
@Data
public class Appointment implements Serializable {
    private Long id;

    /**
     * 预约编号
     */
    private String appointcode;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 预约手机号
     */
    private String phone;

    /**
     * 预约日期
     */
    private Date appintdate;

    /**
     * 预约时间（1:上午,2:中午,3:下午,4:晚上）
     */
    private Integer temporalinterval;

    /**
     * 预约上门维修地址
     */
    private String adress;

    /**
     * 状态((1:用户预约,2:完成)
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long createduserid;

    /**
     * 修改人
     */
    private Long updateduserid;

    /**
     * 创建时间
     */
    private Date createdtime;

    /**
     * 修改时间
     */
    private Date updatedtime;

    /**
     * 是否删除(0:否,1:是)
     */
    private Integer isdelete;

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
        Appointment other = (Appointment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppointcode() == null ? other.getAppointcode() == null : this.getAppointcode().equals(other.getAppointcode()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getAppintdate() == null ? other.getAppintdate() == null : this.getAppintdate().equals(other.getAppintdate()))
            && (this.getTemporalinterval() == null ? other.getTemporalinterval() == null : this.getTemporalinterval().equals(other.getTemporalinterval()))
            && (this.getAdress() == null ? other.getAdress() == null : this.getAdress().equals(other.getAdress()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateduserid() == null ? other.getCreateduserid() == null : this.getCreateduserid().equals(other.getCreateduserid()))
            && (this.getUpdateduserid() == null ? other.getUpdateduserid() == null : this.getUpdateduserid().equals(other.getUpdateduserid()))
            && (this.getCreatedtime() == null ? other.getCreatedtime() == null : this.getCreatedtime().equals(other.getCreatedtime()))
            && (this.getUpdatedtime() == null ? other.getUpdatedtime() == null : this.getUpdatedtime().equals(other.getUpdatedtime()))
            && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppointcode() == null) ? 0 : getAppointcode().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAppintdate() == null) ? 0 : getAppintdate().hashCode());
        result = prime * result + ((getTemporalinterval() == null) ? 0 : getTemporalinterval().hashCode());
        result = prime * result + ((getAdress() == null) ? 0 : getAdress().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateduserid() == null) ? 0 : getCreateduserid().hashCode());
        result = prime * result + ((getUpdateduserid() == null) ? 0 : getUpdateduserid().hashCode());
        result = prime * result + ((getCreatedtime() == null) ? 0 : getCreatedtime().hashCode());
        result = prime * result + ((getUpdatedtime() == null) ? 0 : getUpdatedtime().hashCode());
        result = prime * result + ((getIsdelete() == null) ? 0 : getIsdelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appointcode=").append(appointcode);
        sb.append(", userid=").append(userid);
        sb.append(", phone=").append(phone);
        sb.append(", appintdate=").append(appintdate);
        sb.append(", temporalinterval=").append(temporalinterval);
        sb.append(", adress=").append(adress);
        sb.append(", status=").append(status);
        sb.append(", createduserid=").append(createduserid);
        sb.append(", updateduserid=").append(updateduserid);
        sb.append(", createdtime=").append(createdtime);
        sb.append(", updatedtime=").append(updatedtime);
        sb.append(", isdelete=").append(isdelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}