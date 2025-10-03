package za.ac.cput.dto;

public class AdminLoginResponseDto {
    private int adminId;
    private String email;

    public AdminLoginResponseDto(int adminId, String email) {
        this.adminId = adminId;
        this.email = email;
    }

    public int getAdminId() { return adminId; }
    public String getEmail() { return email; }
}

