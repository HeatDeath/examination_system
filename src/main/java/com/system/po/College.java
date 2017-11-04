package com.system.po;

import java.io.Serializable;

/**
 * @author 
 */
public class College implements Serializable {
    private Integer collegeid;

    /**
     * 课程名
     */
    private String collegename;

    private static final long serialVersionUID = 1L;

    public Integer getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(Integer collegeid) {
        this.collegeid = collegeid;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

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
        College other = (College) that;
        return (this.getCollegeid() == null ? other.getCollegeid() == null : this.getCollegeid().equals(other.getCollegeid()))
            && (this.getCollegename() == null ? other.getCollegename() == null : this.getCollegename().equals(other.getCollegename()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCollegeid() == null) ? 0 : getCollegeid().hashCode());
        result = prime * result + ((getCollegename() == null) ? 0 : getCollegename().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", collegeid=").append(collegeid);
        sb.append(", collegename=").append(collegename);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}