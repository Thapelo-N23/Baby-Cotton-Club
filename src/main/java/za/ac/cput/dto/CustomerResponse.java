package za.ac.cput.dto;

import java.util.List;

public class CustomerResponse {
        private int customerId;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private List<CustomerOrderResponse> orders;

        // Add getters and setters
        public int getCustomerId() { return customerId; }
        public void setCustomerId(int customerId) { this.customerId = customerId; }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhoneNumber() { return phoneNumber; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

        public List<CustomerOrderResponse> getOrders() { return orders; }
        public void setOrders(List<CustomerOrderResponse> orders) { this.orders = orders; }
}
