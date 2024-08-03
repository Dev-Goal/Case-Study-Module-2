package service;

import view.HomeView;

import java.util.function.Predicate;

public class HomeService {
    private HomeView homeView = new HomeView();

    public String checkValidatedInput(String prompt, Predicate<String> validate, Predicate<String> checkDuplicate, String errorMessage) {
        String input;
        do {
            input = homeView.getInput(prompt);
            if (!validate.test(input)) {
                homeView.showMessage("Thông tin không hợp lệ hoặc không được để trống");
            } else if (checkDuplicate != null && checkDuplicate.test(input)) {
                homeView.showMessage(errorMessage);
            } else {
                return input;
            }
        } while (true);
    }

}
