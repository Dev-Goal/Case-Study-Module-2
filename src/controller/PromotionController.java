package controller;

import model.Promotion;
import service.HomeService;
import view.HomeView;

import java.util.HashMap;
import java.util.Map;

public class PromotionController {
    private HomeView homeView = new HomeView();
    private HomeService homeService = new HomeService();
    private Map<String, Promotion> promotionData = new HashMap<>();

    public void showPromotionList() {
        homeView.showMessage("Danh sách khuyến mãi");
        for (Promotion promotion : promotionData.values()) {
            homeView.showDetailPromotion(promotion);
        }
    }

    public void addPromotion() {
        homeView.showMessage("Thêm mã khuyến mãi");
        String codePromotion = homeView.getInput("Mã khuyến mãi: ");
        String namePromotion = homeView.getInput("Tên khuyến mãi: ");
        String desc = homeView.getInput("Mô tả: ");
        double discountAmount = Double.parseDouble(homeView.getInput("Số tiền giảm: "));
        int amountPromotion = Integer.parseInt(homeView.getInput("Số lượng: "));
        Promotion promotion = new Promotion(codePromotion, namePromotion, desc, discountAmount, amountPromotion);
        promotionData.put(codePromotion, promotion);
        homeView.showMessage("Thêm mã khuyến mãi thành công");
    }

    public void editPromotion() {
        homeView.showMessage("Chỉnh sửa mã khuyến mãi");
        String codePromotion = homeView.getInput("Mã khuyến mãi: ");
        Promotion promotion = promotionData.get(codePromotion);
        if (promotion == null) {
            homeView.showMessage("Mã khuyến mãi không tồn tại");
            return;
        }
        String namePromotion = homeView.getInput("Tên khuyến mãi: ");
        String desc = homeView.getInput("Mô tả: ");
        double discountAmount = Double.parseDouble(homeView.getInput("Số tiền giảm: "));
        int amountPromotion = Integer.parseInt(homeView.getInput("Số lượng: "));
        promotion.setNamePromotion(namePromotion);
        promotion.setDesc(desc);
        promotion.setDiscountAmount(discountAmount);
        promotion.setAmount(amountPromotion);
        homeView.showMessage("Chỉnh sửa mã khuyến mãi thành công");
    }

    public void deletePromotion() {
        homeView.showMessage("Xóa mã khuyến mãi");
        String codePromotion = homeView.getInput("Mã khuyến mãi: ");
//        Promotion promotion = promotionData.get(codePromotion);
//        if (promotion == null) {
//            homeView.showMessage("Mã khuyến mãi không tồn tại");
//            return;
//        }
        if (promotionData.containsKey(codePromotion)) {
            String message = homeView.getInput("Bạn có chắc chắn muốn xóa: ");
            if (message.equalsIgnoreCase("Có")) {
                promotionData.remove(codePromotion);
            }
            homeView.showMessage("Xóa mã khuyến mãi thành công");
        }
        homeView.showMessage("Mã khuyến mãi không tồn tại");
    }
}
