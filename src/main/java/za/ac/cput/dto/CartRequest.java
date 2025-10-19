package za.ac.cput.dto;

import java.util.List;

public class CartRequest {
    private CustomerDto customer;
    private List<CartItemRequest> items;
    private boolean isCheckedOut;

    public CustomerDto getCustomer() { return customer; }
    public void setCustomer(CustomerDto customer) { this.customer = customer; }

    public List<CartItemRequest> getItems() { return items; }
    public void setItems(List<CartItemRequest> items) { this.items = items; }

    public boolean isCheckedOut() { return isCheckedOut; }
    public void setCheckedOut(boolean isCheckedOut) { this.isCheckedOut = isCheckedOut; }

    public static class CustomerDto {
        private int customerId;
        private String firstName;
        private String lastName;
        private String email;

        public int getCustomerId() { return customerId; }
        public void setCustomerId(int customerId) { this.customerId = customerId; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
