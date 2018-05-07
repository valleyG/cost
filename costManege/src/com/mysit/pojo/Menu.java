package com.mysit.pojo;

import java.io.Serializable;

/**
 * 菜单实体类
 */
public class Menu implements Serializable {
    private int menuId;
    private String menuName;
    private String menuUrl;
    private int pMenuId;
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String manuUrl) {
        this.menuUrl = manuUrl;
    }

    public int getpMenuId() {
        return pMenuId;
    }

    public void setpMenuId(int pMenuId) {
        this.pMenuId = pMenuId;
    }
}
