package controller;

import csvutil.EmployeeCSVUtil;
import model.Employee;
import service.ValidatorEmployee;
import view.HomeView;

import java.util.Map;

public class EmployeeController {
    private final HomeView homeView = new HomeView();
    private final MovieController movieController = new MovieController();
    private Map<String, Employee> employeeData;
    private static final String EMPLOYEE_FILE_PATH = "src/data/employee.csv";

    private void loadData() {
        this.employeeData = EmployeeCSVUtil.readEmployeeFromCSV(EMPLOYEE_FILE_PATH);
    }

    public void signUpEmployee() {
        loadData();
        ValidatorEmployee validatorEmployee = new ValidatorEmployee(employeeData);
        String username;
        String usernameError;
        do {
            username = homeView.getInput("Tên đăng nhập: ");
            usernameError = validatorEmployee.checkUsername(username);
            if (usernameError != null) {
                homeView.showMessage(usernameError);
            }
        } while (usernameError != null);
        String password;
        String passwordError;
        do {
            password = homeView.getInput("Mật khẩu: ");
            passwordError = validatorEmployee.checkPassword(password);
            if (passwordError != null) {
                homeView.showMessage(passwordError);
            }
        } while (passwordError != null);
        String fullName;
        String fullNameError;
        do {
            fullName = homeView.getInput("Họ và tên: ");
            fullNameError = validatorEmployee.checkFullName(fullName);
            if (fullNameError != null) {
                homeView.showMessage(fullNameError);
            }
        } while (fullNameError != null);
        String email;
        String emailError;
        do {
            email = homeView.getInput("Email: ");
            emailError = validatorEmployee.checkEmail(email);
            if (emailError != null) {
                homeView.showMessage(emailError);
            }
        } while (emailError != null);
        String phoneNumber;
        String phoneNumberError;
        do {
            phoneNumber = homeView.getInput("Số điện thoại: ");
            phoneNumberError = validatorEmployee.checkPhoneNumber(phoneNumber);
            if (phoneNumberError != null) {
                homeView.showMessage(phoneNumberError);
            }
        } while (phoneNumberError != null);
        String citizen;
        String citizenError;
        do {
            citizen = homeView.getInput("Căn cước công dân: ");
            citizenError = validatorEmployee.checkCitizen(citizen);
            if (citizenError != null) {
                homeView.showMessage(citizenError);
            }
        } while (citizenError != null);
        Employee employee = new Employee(username, password, fullName, email, citizen, citizen);
        employeeData.put(username, employee);
        EmployeeCSVUtil.writeEmployeeToCSV(employeeData, EMPLOYEE_FILE_PATH);
        homeView.showMessage("Đăng ký nhân viên thành công");
    }

    public void signInEmployee() {
        loadData();
        String username = homeView.getInput("Tên đăng nhập: ");
        String password = homeView.getInput("Mật khẩu: ");
        Employee employee = employeeData.get(username);
        if (employee == null || !employee.getPassword().equals(password)) {
            homeView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        homeView.showMessage("Đăng nhập thành công");
        showMenuEmployee();
    }

    private void showMenuEmployee() {
        do {
            homeView.showMessage("1. Xem danh sách phim");
            homeView.showMessage("2. Đặt vé");
            homeView.showMessage("3. Chỉnh sửa thông tin cá nhân");
            homeView.showMessage("4. Thay đổi mật khẩu");
            homeView.showMessage("5. Đăng xuất");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> movieController.showMovieList();
                case "3" -> editEmployee();
                case "4" -> changePasswordEmployee();
                case "5" -> {
                    return;
                }
            }
        } while (true);
    }

    private void editEmployee() {
        loadData();
        homeView.showMessage("Để bảo mật, vui lòng đăng nhập để thay đổi thông tin");
        String username = homeView.getInput("Tên đăng nhập: ");
        String password = homeView.getInput("Mật khẩu: ");
        Employee employee = employeeData.get(username);
        if (employee == null || !employee.getPassword().equals(password)) {
            homeView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        ValidatorEmployee validatorEmployee = new ValidatorEmployee(employeeData);
        String fullName;
        do {
            fullName = homeView.getInput("Họ và tên (bỏ qua nếu không thay đổi): ");
            if (!fullName.trim().isEmpty()) {
                String fullNameError = validatorEmployee.checkFullName(fullName);
                if (fullNameError != null) {
                    homeView.showMessage(fullNameError);
                } else {
                    employee.setFullName(fullName);
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
                String emailError = validatorEmployee.checkEmail(email);
                if (emailError != null) {
                    homeView.showMessage(emailError);
                } else {
                    employee.setEmail(email);
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
                String phoneNumberError = validatorEmployee.checkPhoneNumber(phoneNumber);
                if (phoneNumberError != null) {
                    homeView.showMessage(phoneNumberError);
                } else {
                    employee.setPhoneNumber(phoneNumber);
                    break;
                }
            } else {
                break;
            }
        } while (true);
        String citizen;
        do {
            citizen = homeView.getInput("Căn cước công dân (bỏ qua nếu không thay đổi): ");
            if (!citizen.trim().isEmpty()) {
                String citizenError = validatorEmployee.checkCitizen(citizen);
                if (citizenError != null) {
                    homeView.showMessage(citizenError);
                } else {
                    employee.setCitizen(citizen);
                    break;
                }
            } else {
                break;
            }
        } while (true);
        employeeData.put(username, employee);
        EmployeeCSVUtil.writeEmployeeToCSV(employeeData, EMPLOYEE_FILE_PATH);
        homeView.showMessage("Cập nhật thông tin thành công");
    }

    private void changePasswordEmployee() {
        loadData();
        homeView.showMessage("Để bảo mật, vui lòng đăng nhập để thay đổi thông tin");
        String username = homeView.getInput("Tên đăng nhập: ");
        String password = homeView.getInput("Mật khẩu: ");
        Employee employee = employeeData.get(username);
        if (employee == null || !employee.getPassword().equals(password)) {
            homeView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        ValidatorEmployee validatorEmployee = new ValidatorEmployee(employeeData);
        String newPassword;
        String confirmNewPassword;
        String newPasswordError;
        do {
            newPassword = homeView.getInput("Mật khẩu mới: ");
            confirmNewPassword = homeView.getInput("Xác nhận mật khẩu mới: ");
            if (!newPassword.equals(confirmNewPassword)) {
                homeView.showMessage("Xác nhận mật khẩu và mật khẩu mới không trùng khớp");
            } else {
                newPasswordError = validatorEmployee.checkPassword(newPassword);
                if (newPasswordError != null) {
                    homeView.showMessage(newPasswordError);
                } else {
                    employee.setPassword(newPassword);
                    break;
                }
            }
        }while (true);
        employeeData.put(username, employee);
        EmployeeCSVUtil.writeEmployeeToCSV(employeeData, EMPLOYEE_FILE_PATH);
        homeView.showMessage("Thay đổi mật khẩu thành công");
    }
}
