package service;

import model.Promotion;

import java.util.Map;
import java.util.regex.Pattern;

public class ValidatorPromotion {
    private final Map<String, Promotion> promotionData;

    public ValidatorPromotion(Map<String, Promotion> promotionData) {
        this.promotionData = promotionData;
    }

    public String checkCodePromotion(String codePromotion) {
        if (codePromotion.trim().isEmpty()) {
            return "Mã khuyến mãi không được để trống";
        }
        if (codePromotion.length() < 5) {
            return "Mã khuyến mãi tối thiểu 5 ký tự";
        }
        if (!codePromotion.matches("^[A-Z0-9]{5,10}$")) {
            return "Mã khuyến mãi gồm chữ hoa và số";
        }
        if (promotionData.values().stream().anyMatch(promotion -> promotion.getCodePromotion().equals(codePromotion))) {
            return "Mã khuyến mãi đã có";
        }
        return null;
    }

    public String checkNamePromotion(String namePromotion) {
        if (namePromotion.trim().isEmpty()) {
            return "Tên mã khuyến mãi không được để trống";
        }
        if (namePromotion.length() > 100) {
            return "Tên mã khuyến mãi quá dài";
        }
        if (!namePromotion.matches("[\\p{L} ]+")) {
            return "Tên khuyến mãi chỉ chứa chữ và dấu cách";
        }
        return null;
    }

    public String checkDescPromotion(String desc) {
        if (desc.trim().isEmpty()) {
            return "Mô tả không được để trống";
        }
        if (desc.length() > 1000) {
            return "Mô tả không quá 1000 ký tự";
        }
        if (!desc.matches("[\\p{L} ]+")) {
            return "Mô tả chỉ chứa chữ và dấu cách";
        }
        return null;
    }

    public String checkDiscountAmount(Double discountAmount) {
        if (discountAmount == null) {
            return "Số tiền giảm không được để trống";
        }
        if (!Pattern.matches("\\d+(\\.\\d+)?", discountAmount.toString())) {
            return "Số tiền giảm phải là số hợp lệ";
        }
        if (discountAmount <= 0) {
            return "Số tiền giảm phải lớn hơn 0";
        }
        return null;
    }

    public String checkAmount(Integer amount) {
        if (amount == null) {
            return "Số lượng không được để trống";
        }
        if (!Pattern.matches("\\d+", amount.toString())) {
            return "Số lượng phải là số nguyên hợp lệ";
        }
        if (amount <= 0) {
            return "Số lượng phải lớn hơn 0";
        }
        return null;
    }
}
