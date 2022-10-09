package com.syk.advertise.generator.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName advertise
 */
@Table(name="advertise")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Advertise implements Serializable {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 图片
     */
    private String cover;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 是否上架 0下架、1上架
     */
    @Column(name = "is_display")
    private Integer isDisplay;

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
        Advertise other = (Advertise) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCover() == null ? other.getCover() == null : this.getCover().equals(other.getCover()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getIsDisplay() == null ? other.getIsDisplay() == null : this.getIsDisplay().equals(other.getIsDisplay()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCover() == null) ? 0 : getCover().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getIsDisplay() == null) ? 0 : getIsDisplay().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cover=").append(cover);
        sb.append(", url=").append(url);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}