package com.mysit.pojo;

/**
 *费用实体
 */
public class Cost {
    private int costId;
    private String costName;
    private String costDesc;
    private String costMark;

    //选择删除的对象
    private String ck;
    private int[] ids;

    public String getCk() {
        return "<input type='checkbox' value='"+this.getCostId()+"' name='ids'/>";
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getCostDesc() {
        return costDesc;
    }

    public void setCostDesc(String costDesc) {
        this.costDesc = costDesc;
    }

    public String getCostMark() {
        return costMark;
    }

    public void setCostMark(String costMark) {
        this.costMark = costMark;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "costId=" + costId +
                ", costName='" + costName + '\'' +
                ", costDesc='" + costDesc + '\'' +
                ", costMark='" + costMark + '\'' +
                '}';
    }
}
