package service;

import csvutil.PromotionCSVUtil;
import model.Promotion;
import view.HomeView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TicketService {
    private final HomeView homeView = new HomeView();
    private static final String PROMOTION_FILE_PATH = "src/data/promotion.csv";
    private Map<String, Promotion> promotionData;

    private void loadData() {
        this.promotionData = PromotionCSVUtil.readPromotionFromCSV(PROMOTION_FILE_PATH);
    }



    public Set<String> getcodesPromotion() {
        loadData();
        Set<String> codesPromotion = new HashSet<>();
        String addMorePromotions = homeView.getInput("Có muốn thêm mã khuyến mãi không: ");
        while (addMorePromotions.equalsIgnoreCase("Có")) {
            String codePromotion = homeView.getInput("Nhập mã khuyến mãi: ");
            if (promotionData.containsKey(codePromotion) && promotionData.get(codePromotion).getAmount() > 0) {
                codesPromotion.add(codePromotion);
                promotionData.get(codePromotion).decreaseAmountPromotion();
                homeView.showMessage("Thêm mã khuyến mãi thành công.");
            } else {
                homeView.showMessage("Mã khuyến mãi không hợp lệ hoặc số lượng đã hết");
            }
            addMorePromotions = homeView.getInput("Có muốn thêm mã khuyến mãi nữa không: ");
        }
        return codesPromotion;
    }

    public double calculateTotalDiscount(Set<String> codesPromotion) {
        loadData();
        double totalDiscount = 0.0;
        for (String code : codesPromotion) {
            Promotion promotion = promotionData.get(code);
            if (promotion != null) {
                totalDiscount += promotion.getDiscountAmount();
            }
        }
        return totalDiscount;
    }
}
