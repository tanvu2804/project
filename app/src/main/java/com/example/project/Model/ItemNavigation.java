package com.example.project.Model;

public class ItemNavigation {
    String tieuDe;
    int icon;

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public ItemNavigation(String tieuDe, int icon) {
        this.tieuDe = tieuDe;
        this.icon = icon;
    }
    public ItemNavigation() {

    }
}
