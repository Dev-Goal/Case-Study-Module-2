package service;

import view.CinemaView;

import java.util.function.Predicate;

public class CinemeService {
    private CinemaView cinemaView = new CinemaView();

    public String checkValidatedInput(String prompt, Predicate<String> validate, Predicate<String> checkDuplicate, String errorMessage) {
        String input;
        do {
            input = cinemaView.getInput(prompt);
            if (!validate.test(input)) {
                cinemaView.showMessage("Thông tin không hợp lệ hoặc không được để trống");
            } else if (checkDuplicate != null && checkDuplicate.test(input)) {
                cinemaView.showMessage(errorMessage);
            } else {
                return input;
            }
        } while (true);
    }

}
