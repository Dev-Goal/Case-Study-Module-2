package service;

import csvutil.CustomerCSVUtil;
import model.Customer;
import model.Role;
import view.HomeView;

import java.util.Map;

public class CustomerService {
    private HomeView homeView = new HomeView();
    private Map<String, Customer> customerData;
    private static final String CUSTOMER_FILE_PATH = "src/data/customer.csv";

    private void loadData() {
        this.customerData = CustomerCSVUtil.readCustomerFromCSV(CUSTOMER_FILE_PATH);
    }

    public void updateUserRoleToCustomer(String username) {
        loadData();
        Customer customer = customerData.get(username);
        if (customer != null) {
            customer.removeRole(Role.ROLE_USER);
            customer.addRole(Role.ROLE_CUSTOMER);
            homeView.showMessage("Bạn đã được thăng cấp lên CUSTOMER");

        }
        CustomerCSVUtil.writeCustomerToCSV(customerData, CUSTOMER_FILE_PATH);
    }
}