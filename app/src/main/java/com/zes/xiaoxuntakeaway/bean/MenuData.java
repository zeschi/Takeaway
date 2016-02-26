package com.zes.xiaoxuntakeaway.bean;

/**
 * Created by zes on 16-2-21.
 */
public class MenuData {
    private Menu menu;
    private int menuCount;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(int menuCount) {
        this.menuCount = menuCount;
    }

    @Override
    public String toString() {
        return "MenuList{" +
                "menu=" + menu +
                ", menuCount=" + menuCount +
                '}';
    }
}
