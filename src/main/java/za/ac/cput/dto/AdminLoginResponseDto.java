package za.ac.cput.dto;

public class AdminLoginResponseDto {
    private int adminId;
    private String email;
    private String token; // JWT token

    public AdminLoginResponseDto(int adminId, String email) {
        this.adminId = adminId;
        this.email = email;
        this.token = null;
    }

    public AdminLoginResponseDto(int adminId, String email, String token) {
        this.adminId = adminId;
        this.email = email;
        this.token = token;
    }

    public int getAdminId() { return adminId; }
    public String getEmail() { return email; }
    public String getToken() { return token; }
}
