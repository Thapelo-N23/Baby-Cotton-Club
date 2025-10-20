package za.ac.cput.dto;

public class CustomerLoginResponseDto {
    private int customerId;
    private String email;
    private String firstName;
    private String lastName;
    private String token; // JWT token

    public CustomerLoginResponseDto(int customerId, String email, String firstName, String lastName) {
        this.customerId = customerId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = null;
    }

    public CustomerLoginResponseDto(int customerId, String email, String firstName, String lastName, String token) {
        this.customerId = customerId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }

    public int getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getToken() { return token; }
}
