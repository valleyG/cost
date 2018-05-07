package com.mysit.pojo;

public class User {
    private int userId;
    private int roleId;
    private String userName;
    private String userSex;
    private int userAge;
    private String userAccount;
    private String userPwd;
    private float userBasic;
    private String userMark;
    private String userPhone;

    private String userRole;

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    //删除的用户编号
    private String ck;
    private int[] ids;

    public String getCk() {
        return "<input type='checkbox' value='"+this.getUserId()+"' name='ids'/>";
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public float getUserBasic() {
        return userBasic;
    }

    public void setUserBasic(float userBasic) {
        this.userBasic = userBasic;
    }

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge=" + userAge +
                ", userAccount='" + userAccount + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userBasic=" + userBasic +
                ", userMark='" + userMark + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
