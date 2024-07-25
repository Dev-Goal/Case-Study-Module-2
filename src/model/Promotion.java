package model;

public class Promotion {
    private String codePromotion;
    private String namePromotion;
    private String desc;
    private double discountAmount;
    private int amount;

    public Promotion(String codePromotion, String namePromotion, String desc, double discountAmount, int amount) {
        this.codePromotion = codePromotion;
        this.namePromotion = namePromotion;
        this.desc = desc;
        this.discountAmount = discountAmount;
        this.amount = amount;
    }

    public String getCodePromotion() {
        return codePromotion;
    }

    public void setCodePromotion(String codePromotion) {
        this.codePromotion = codePromotion;
    }

    public String getNamePromotion() {
        return namePromotion;
    }

    public void setNamePromotion(String namePromotion) {
        this.namePromotion = namePromotion;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void decreaseAmountPromotion() {
        if (amount > 0) {
            amount--;
        }
    }
}
