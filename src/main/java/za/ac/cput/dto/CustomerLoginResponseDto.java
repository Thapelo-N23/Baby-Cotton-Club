package za.ac.cput.dto;

public class CustomerLoginResponseDto {
    private int customerId;
    private String email;
    private String firstName;
    private String lastName;

    public CustomerLoginResponseDto(int customerId, String email, String firstName, String lastName) {
        this.customerId = customerId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}
