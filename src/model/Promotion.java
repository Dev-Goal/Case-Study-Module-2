package model;

public class Promotion {
    private String idPromotion;
    private String namePromotion;
    private String desc;
    private double discountAmount;

    public Promotion(String idPromotion, String namePromotion, String desc, double discountAmount) {
        this.idPromotion = idPromotion;
        this.namePromotion = namePromotion;
        this.desc = desc;
        this.discountAmount = discountAmount;
    }

    public String getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(String idPromotion) {
        this.idPromotion = idPromotion;
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
}
