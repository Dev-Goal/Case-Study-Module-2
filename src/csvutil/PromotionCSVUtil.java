package csvutil;

import model.Promotion;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PromotionCSVUtil {
    public static Map<String, Promotion> readPromotionFromCSV(String filePath) {
        Map<String, Promotion> promotionData = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    String codePromotion = values[0];
                    String namePromotion = values[1];
                    String desc = values[2];
                    double discountAmount = Double.parseDouble(values[3]);
                    int amount = Integer.parseInt(values[4]);
                    Promotion promotion = new Promotion(codePromotion, namePromotion, desc, discountAmount, amount);
                    promotionData.put(codePromotion, promotion);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return promotionData;
    }

    public static void writePromotionToCSV(Map<String, Promotion> promotionData, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            for (Promotion promotion : promotionData.values()) {
                bufferedWriter.write(String.join(",",
                        promotion.getCodePromotion(),
                        promotion.getNamePromotion(),
                        promotion.getDesc(),
                        String.valueOf(promotion.getDiscountAmount()),
                        String.valueOf(promotion.getAmount())));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
