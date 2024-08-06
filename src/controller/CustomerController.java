package controller;

import csvutil.CustomerCSVUtil;
import model.Customer;
import service.ValidatorCustomer;
import view.HomeView;

import java.util.Map;

public class CustomerController {
    private final HomeView homeView = new HomeView();
    private final MovieController movieController = new MovieController();
    private final TicketController ticketController = new TicketController();
    private Map<String, Customer> customerData;
    private static final String CUSTOMER_FILE_PATH = "src/data/customer.csv";

    private void loadData() {
        this.customerData = CustomerCSVUtil.readCustomerFromCSV(CUSTOMER_FILE_PATH);
    }

    public void signUpCustomer() {
        loadData();
        ValidatorCustomer validatorCustomer = new ValidatorCustomer(customerData);
        String username;
        String usernameError;
        do {
            username = homeView.getInput("Tên đăng nhập: ");
            usernameError = validatorCustomer.checkUsername(username);
            if (usernameError != null) {
                homeView.showMessage(usernameError);
            }
        } while (usernameError != null);
        String password;
        String passwordError;
        do {
            password = homeView.getInput("Mật khẩu: ");
            passwordError = validatorCustomer.checkPassword(password);
            if (passwordError != null) {
                homeView.showMessage(passwordError);
            }
        } while (passwordError != null);
        String fullName;
        String fullNameError;
        do {
            fullName = homeView.getInput("Họ và tên: ");
            fullNameError = validatorCustomer.checkFullName(fullName);
            if (fullNameError != null) {
                homeView.showMessage(fullNameError);
            }
        } while (fullNameError != null);
        String email;
        String emailError;
        do {
            email = homeView.getInput("Email: ");
            emailError = validatorCustomer.checkEmail(email);
            if (emailError != null) {
                homeView.showMessage(emailError);
            }
        } while (emailError != null);
        String phoneNumber;
        String phoneNumberError;
        do {
            phoneNumber = homeView.getInput("Số điện thoại: ");
            phoneNumberError = validatorCustomer.checkPhoneNumber(phoneNumber);
            if (phoneNumberError != null) {
                homeView.showMessage(phoneNumberError);
            }
        } while (phoneNumberError != null);
        Customer customer = new Customer(username, password, fullName, email, phoneNumber);
        customerData.put(username, customer);
        CustomerCSVUtil.writeCustomerToCSV(customerData, CUSTOMER_FILE_PATH);
        homeView.showMessage("Đăng ký khách hàng thành công");
    }

    public void signInCustomer() {
        loadData();
        String username = homeView.getInput("Tên đăng nhập: ");
        String password = homeView.getInput("Mật khẩu: ");
        Customer customer = customerData.get(username);
        if (customer == null || !customer.getPassword().equals(password)) {
            homeView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        homeView.showMessage("Đăng nhập thành công");
        showMenuCustomer();
    }

    private void showMenuCustomer() {
        do {
            homeView.showMessage("1. Xem danh sách phim");
            homeView.showMessage("2. Đặt vé");
            homeView.showMessage("3. Chỉnh sửa thông tin cá nhân");
            homeView.showMessage("4. Thay đổi mật khẩu");
            homeView.showMessage("5. Đăng xuất");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> movieController.showMovieList();
                case "2" -> ticketController.bookTicket();
                case "3" -> editCustomer();
                case "4" -> changePasswordCustomer();
                case "5" -> {
                    return;
                }
            }
        } while (true);
    }

    private void editCustomer() {
        loadData();
        homeView.showMessage("Để bảo mật, vui lòng đăng nhập để thay đổi thông tin");
        String username = homeView.getInput("Tên đăng nhập: ");
        String password = homeView.getInput("Mật khẩu: ");
        Customer customer = customerData.get(username);
        if (customer == null || !customer.getPassword().equals(password)) {
            homeView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        ValidatorCustomer validatorCustomer = new ValidatorCustomer(customerData);
        String fullName;
        do {
            fullName = homeView.getInput("Họ và tên (bỏ qua nếu không thay đổi): ");
            if (!fullName.trim().isEmpty()) {
                String fullNameError = validatorCustomer.checkFullName(fullName);
                if (fullNameError != null) {
                    homeView.showMessage(fullNameError);
                } else {
                    customer.setFullName(fullName);
                    break;
                }
            } else {
                break;
            }
        } while (true);
        String email;
        do {
            email = homeView.getInput("Email (bỏ qua nếu không thay đổi): ");
            if (!email.trim().isEmpty()) {
                String emailError = validatorCustomer.checkEmail(email);
                if (emailError != null) {
                    homeView.showMessage(emailError);
                } else {
                    customer.setEmail(email);
                    break;
                }
            } else {
                break;
            }
        } while (true);
        String phoneNumber;
        do {
            phoneNumber = homeView.getInput("Số điện thoại (bỏ qua nếu không thay đổi): ");
            if (!phoneNumber.trim().isEmpty()) {
                String phoneNumberError = validatorCustomer.checkPhoneNumber(phoneNumber);
                if (phoneNumberError != null) {
                    homeView.showMessage(phoneNumberError);
                } else {
                    customer.setPhoneNumber(phoneNumber);
                    break;
                }
            } else {
                break;
            }
        } while (true);
        customerData.put(username, customer);
        CustomerCSVUtil.writeCustomerToCSV(customerData, CUSTOMER_FILE_PATH);
        homeView.showMessage("Cập nhật thông tin thành công");
    }

    private void changePasswordCustomer() {
        loadData();
        homeView.showMessage("Để bảo mật, vui lòng đăng nhập để thay đổi thông tin");
        String username = homeView.getInput("Tên đăng nhập: ");
        String password = homeView.getInput("Mật khẩu: ");
        Customer customer = customerData.get(username);
        if (customer == null || !customer.getPassword().equals(password)) {
            homeView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        ValidatorCustomer validatorCustomer = new ValidatorCustomer(customerData);
        String newPassword;
        String confirmNewPassword;
        String newPasswordError;
        do {
            newPassword = homeView.getInput("Mật khẩu mới: ");
            confirmNewPassword = homeView.getInput("Xác nhận mật khẩu mới: ");
            if (!newPassword.equals(confirmNewPassword)) {
                homeView.showMessage("Xác nhận mật khẩu và mật khẩu mới không trùng khớp");
            } else {
                newPasswordError = validatorCustomer.checkPassword(newPassword);
                if (newPasswordError != null) {
                    homeView.showMessage(newPasswordError);
                } else {
                    customer.setPassword(newPassword);
                    break;
                }
            }
        }while (true);
        customerData.put(username, customer);
        CustomerCSVUtil.writeCustomerToCSV(customerData, CUSTOMER_FILE_PATH);
        homeView.showMessage("Thay đổi mật khẩu thành công");
    }
}
