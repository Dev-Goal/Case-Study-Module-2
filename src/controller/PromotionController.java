package controller;

import csvutil.PromotionCSVUtil;
import model.Promotion;
import service.ValidatorPromotion;
import view.HomeView;

import java.util.Map;

public class PromotionController {
    private final HomeView homeView = new HomeView();
    private Map<String, Promotion> promotionData;
    private static final String PROMOTION_FILE_PATH = "src/data/promotion.csv";

    private void loadData() {
        this.promotionData = PromotionCSVUtil.readPromotionFromCSV(PROMOTION_FILE_PATH);
    }

    public void showPromotionList() {
        loadData();
        homeView.showMessage("Danh sách khuyến mãi");
        for (Promotion promotion : promotionData.values()) {
            homeView.showDetailPromotion(promotion);
        }
    }

    public void addPromotion() {
        loadData();
        homeView.showMessage("Thêm mã khuyến mãi");

        ValidatorPromotion validatorPromotion = new ValidatorPromotion(promotionData);

        String codePromotion;
        String codePromotionError;
        do {
            codePromotion = homeView.getInput("Mã khuyến mãi: ");
            codePromotionError = validatorPromotion.checkCodePromotion(codePromotion);
            if (codePromotionError != null) {
                homeView.showMessage(codePromotionError);
            }
        } while (codePromotionError != null);

        String namePromotion;
        String namePromotionError;
        do {
            namePromotion = homeView.getInput("Tên khuyến mãi: ");
            namePromotionError = validatorPromotion.checkNamePromotion(namePromotion);
            if (namePromotionError != null) {
                homeView.showMessage(namePromotionError);
            }
        } while (namePromotionError != null);

        String descPromotion;
        String descPromotionError;
        do {
            descPromotion = homeView.getInput("Mô tả: ");
            descPromotionError = validatorPromotion.checkDescPromotion(descPromotion);
            if (descPromotionError != null) {
                homeView.showMessage(descPromotionError);
            }
        } while (descPromotionError != null);

        Double discountAmount = null;
        String discountAmountError;
        do {
            try {
                discountAmount = Double.parseDouble(homeView.getInput("Số tiền giảm: "));
                discountAmountError = validatorPromotion.checkDiscountAmount(discountAmount);
                if (discountAmountError != null) {
                    homeView.showMessage(discountAmountError);
                }
            } catch (NumberFormatException e) {
                homeView.showMessage("Số tiền giảm không hợp lệ!");
                discountAmountError = "Số tiền giảm không hợp lệ!";
            }
        } while (discountAmountError != null);

        Integer amountPromotion = null;
        String amountPromotionError;
        do {
            try {
                amountPromotion = Integer.parseInt(homeView.getInput("Số lượng: "));
                amountPromotionError = validatorPromotion.checkAmount(amountPromotion);
                if (amountPromotionError != null) {
                    homeView.showMessage(amountPromotionError);
                }
            } catch (NumberFormatException e) {
                homeView.showMessage("Số lượng không hợp lệ!");
                amountPromotionError = "Số lượng không hợp lệ!";
            }
        } while (amountPromotionError != null);

        Promotion promotion = new Promotion(codePromotion, namePromotion, descPromotion, discountAmount, amountPromotion);
        promotionData.put(codePromotion, promotion);
        PromotionCSVUtil.writePromotionToCSV(promotionData, PROMOTION_FILE_PATH);
        homeView.showMessage("Thêm mã khuyến mãi thành công");
    }

    public void editPromotion() {
        loadData();
        homeView.showMessage("Chỉnh sửa mã khuyến mãi");
        String codePromotion = homeView.getInput("Mã khuyến mãi: ");
        if (!promotionData.containsKey(codePromotion)) {
            homeView.showMessage("Mã khuyến mãi không tồn tại.");
            return;
        }
        Promotion promotion = promotionData.get(codePromotion);
        ValidatorPromotion validatorPromotion = new ValidatorPromotion(promotionData);
        String namePromotion;
        String namePromotionError;
        do {
            namePromotion = homeView.getInput("Tên khuyến mãi (bỏ qua nếu không thay đổi): ");
            if (namePromotion.isEmpty()) {
                namePromotionError = null;
            } else {
                namePromotionError = validatorPromotion.checkNamePromotion(namePromotion);
                if (namePromotionError != null) {
                    homeView.showMessage(namePromotionError);
                }
            }
        } while (namePromotionError != null);
        if (!namePromotion.isEmpty()) {
            promotion.setNamePromotion(namePromotion);
        }
        String descPromotion;
        String descPromotionError;
        do {
            descPromotion = homeView.getInput("Mô tả mới (để trống nếu không thay đổi): ");
            if (descPromotion.isEmpty()) {
                descPromotionError = null;
            } else {
                descPromotionError = validatorPromotion.checkDescPromotion(descPromotion);
                if (descPromotionError != null) {
                    homeView.showMessage(descPromotionError);
                }
            }
        } while (descPromotionError != null);
        if (!descPromotion.isEmpty()) {
            promotion.setDesc(descPromotion);
        }
        Double discountAmount = null;
        String discountAmountError;
        do {
            String discountAmountStr = homeView.getInput("Số tiền giảm mới (để trống nếu không thay đổi): ");
            if (discountAmountStr.isEmpty()) {
                discountAmountError = null;
            } else {
                try {
                    discountAmount = Double.parseDouble(discountAmountStr);
                    discountAmountError = validatorPromotion.checkDiscountAmount(discountAmount);
                    if (discountAmountError != null) {
                        homeView.showMessage(discountAmountError);
                    }
                } catch (NumberFormatException e) {
                    homeView.showMessage("Số tiền giảm không hợp lệ!");
                    discountAmountError = "Số tiền giảm không hợp lệ!";
                }
            }
        } while (discountAmountError != null);
        if (discountAmount != null) {
            promotion.setDiscountAmount(discountAmount);
        }
        Integer amountPromotion = null;
        String amountPromotionError;
        do {
            String amountPromotionStr = homeView.getInput("Số lượng mới (để trống nếu không thay đổi): ");
            if (amountPromotionStr.isEmpty()) {
                amountPromotionError = null;
            } else {
                try {
                    amountPromotion = Integer.parseInt(amountPromotionStr);
                    amountPromotionError = validatorPromotion.checkAmount(amountPromotion);
                    if (amountPromotionError != null) {
                        homeView.showMessage(amountPromotionError);
                    }
                } catch (NumberFormatException e) {
                    homeView.showMessage("Số lượng không hợp lệ!");
                    amountPromotionError = "Số lượng không hợp lệ!";
                }
            }
        } while (amountPromotionError != null);
        if (amountPromotion != null) {
            promotion.setAmount(amountPromotion);
        }
        PromotionCSVUtil.writePromotionToCSV(promotionData, PROMOTION_FILE_PATH);
        homeView.showMessage("Chỉnh sửa mã khuyến mãi thành công");
    }

    public void deletePromotion() {
        loadData();
        homeView.showMessage("Xóa mã khuyến mãi");
        String codePromotion = homeView.getInput("Mã khuyến mãi: ");
        Promotion promotion = promotionData.get(codePromotion);
        if (promotion == null) {
            homeView.showMessage("Mã khuyến mãi không tồn tại");
            return;
        }
        String message = homeView.getInput("Bạn có chắc chắn muốn xóa (Có/Không): ");
        if (message.equalsIgnoreCase("Có")) {
            promotionData.remove(codePromotion);
            PromotionCSVUtil.writePromotionToCSV(promotionData, PROMOTION_FILE_PATH);
            homeView.showMessage("Xóa mã khuyến mãi thành công");
        } else {
            homeView.showMessage("Hủy xóa mã khuyến mãi");
        }
    }
}
