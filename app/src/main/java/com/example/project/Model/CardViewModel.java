package com.example.project.Model;

public class CardViewModel {
    String ma;
    String cardName;

    @Override
    public String toString() {
        return "CardViewModel{" +
                "ma='" + ma + '\'' +
                ", cardName='" + cardName + '\'' +
                '}';
    }

    public String getCardName() {
        return cardName;
    }

    public String getMa() {
        return ma;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

}
