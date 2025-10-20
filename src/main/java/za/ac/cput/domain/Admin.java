package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany
    @JoinColumn(name = "added_by_admin_id")
    private List<Product> products;

    protected Admin() {
    }

    public Admin(Builder builder) {
        this.adminId = builder.adminId;
        this.password = builder.password;
        this.email = builder.email;
        this.products = builder.products;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", email='" + email + '\'' +
                ", products=" + products +
                '}';
    }

    public static class Builder {
        private int adminId;
        private String password;
        private String email;
        private List<Product> products;

        public Builder adminId(int adminId) {
            this.adminId = adminId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder products(List<Product> products) {
            this.products = products;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}
