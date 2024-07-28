package controller.promotioncontroller;

import model.Promotion;
import service.CinemeService;
import view.CinemaView;

import java.util.HashMap;
import java.util.Map;

public class PromotionController {
    private CinemaView cinemaView = new CinemaView();
    private CinemeService cinemeService = new CinemeService();
    private Map<String, Promotion> promotionData = new HashMap<>();

    public void showPromotionList() {
        cinemaView.showMessage("Danh sách khuyến mãi");
        for (Promotion promotion : promotionData.values()) {
            cinemaView.showDetailPromotion(promotion);
        }
    }

    public void addPromotion() {
        cinemaView.showMessage("Thêm mã khuyến mãi");
        String codePromotion = cinemaView.getInput("Mã khuyến mãi: ");
        String namePromotion = cinemaView.getInput("Tên khuyến mãi: ");
        String desc = cinemaView.getInput("Mô tả: ");
        double discountAmount = Double.parseDouble(cinemaView.getInput("Số tiền giảm: "));
        int amountPromotion = Integer.parseInt(cinemaView.getInput("Số lượng: "));
        Promotion promotion = new Promotion(codePromotion, namePromotion, desc, discountAmount, amountPromotion);
        promotionData.put(codePromotion, promotion);
        cinemaView.showMessage("Thêm mã khuyến mãi thành công");
    }

    public void editPromotion() {
        cinemaView.showMessage("Chỉnh sửa mã khuyến mãi");
        String codePromotion = cinemaView.getInput("Mã khuyến mãi: ");
        Promotion promotion = promotionData.get(codePromotion);
        if (promotion == null) {
            cinemaView.showMessage("Mã khuyến mãi không tồn tại");
            return;
        }
        String namePromotion = cinemaView.getInput("Tên khuyến mãi: ");
        String desc = cinemaView.getInput("Mô tả: ");
        double discountAmount = Double.parseDouble(cinemaView.getInput("Số tiền giảm: "));
        int amountPromotion = Integer.parseInt(cinemaView.getInput("Số lượng: "));
        promotion.setNamePromotion(namePromotion);
        promotion.setDesc(desc);
        promotion.setDiscountAmount(discountAmount);
        promotion.setAmount(amountPromotion);
        cinemaView.showMessage("Chỉnh sửa mã khuyến mãi thành công");
    }

    public void deletePromotion() {
        cinemaView.showMessage("Xóa mã khuyến mãi");
        String codePromotion = cinemaView.getInput("Mã khuyến mãi: ");
//        Promotion promotion = promotionData.get(codePromotion);
//        if (promotion == null) {
//            cinemaView.showMessage("Mã khuyến mãi không tồn tại");
//            return;
//        }
        if (promotionData.containsKey(codePromotion)) {
            String message = cinemaView.getInput("Bạn có chắc chắn muốn xóa: ");
            if (message.equalsIgnoreCase("Có")) {
                promotionData.remove(codePromotion);
            }
            cinemaView.showMessage("Xóa mã khuyến mãi thành công");
        }
        cinemaView.showMessage("Mã khuyến mãi không tồn tại");
    }
}
