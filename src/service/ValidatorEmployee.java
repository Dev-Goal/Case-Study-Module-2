package service;

import model.Employee;

import java.util.Map;

public class ValidatorEmployee {
    private Map<String, Employee> employeeData;

    public ValidatorEmployee(Map<String, Employee> employeeData) {
        this.employeeData = employeeData;
    }

    public String checkUsername(String username) {
        if (username.trim().isEmpty()) {
            return "Tên đăng nhập không được để trống";
        }
        if (username.length() < 6) {
            return "Tên đăng nhập có tối thiểu 6 ký tự";
        }
        if (!username.matches("[A-Za-z0-9]+")) {
            return "Tên đăng nhập chỉ chứa chữ và số";
        }
        if (employeeData.values().stream().anyMatch(employee -> employee.getUsername().equals(username))) {
            return "Tên đăng nhập đã được sử dụng";
        }
        return null;
    }

    public String checkPassword(String password) {
        if (password.trim().isEmpty()) {
            return "Password không được để trống";
        }
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
            return "Password tối thiểu 8 ký tự, chứa ít nhất 1 chữ hoa";
        }
        return null;
    }

    public String checkFullName(String fullName) {
        if (fullName.trim().isEmpty()) {
            return "Tên không được để trống";
        }
        if (!fullName.matches("[\\p{L} ]+")) {
            return "Tên chỉ được chứa chữ và dấu cách";
        }
        return null;
    }

    public String checkEmail(String email) {
        if (email.trim().isEmpty()) {
            return "Email không được để trống";
        }
        if (email.length() < 10) {
            return "Email có độ dài không hợp lệ";
        }
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Email không đúng định dạng";
        }
        if (employeeData.values().stream().anyMatch(employee -> employee.getEmail().equals(email))) {
            return "Email đã được sử dụng";
        }
        return null;
    }

    public String checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.trim().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        if (!phoneNumber.matches("(84|0[3|5|7|8|9])+([0-9]{8})")) {
            return "Số điện thoại không đúng định dạng";
        }
        if (employeeData.values().stream().anyMatch(employee -> employee.getPhoneNumber().equals(phoneNumber))) {
            return "Số điện thoại đã được sử dụng";
        }
        return null;
    }

    public String checkCitizen(String citizen) {
        if (citizen.trim().isEmpty()) {
            return "Căn cước công dân không được để trống";
        }
        if (!citizen.matches("\\d{12}")) {
            return "Căn cước công dân chỉ gồm 12 chữ số";
        }
        if (employeeData.values().stream().anyMatch(employee -> employee.getCitizen().equals(citizen))) {
            return "Căn cước công dân đã được sử dụng";
        }
        return null;
    }
}

