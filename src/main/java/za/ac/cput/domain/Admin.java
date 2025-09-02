package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany
    @JoinColumn(name = "added_by_admin_id")
    private List<Product> products;

    @OneToMany
    @JoinColumn(name = "managed_by_admin_id")
    private List<CustomerOrder> orders;

    protected Admin() {}

    public Admin(Builder builder) {
        this.adminId = builder.adminId;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.role = builder.role;
        this.products = builder.products;
        this.orders = builder.orders;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public static class Builder {
        private int adminId;
        private String username;
        private String password;
        private String email;
        private UserRole role;
        private List<Product> products;
        private List<CustomerOrder> orders;

        public Builder adminId(int adminId) {
            this.adminId = adminId;
            return this;
        }
        public Builder username(String username) {
            this.username = username;
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
        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }
        public Builder products(List<Product> products) {
            this.products = products;
            return this;
        }
        public Builder orders(List<CustomerOrder> orders) {
            this.orders = orders;
            return this;
        }
        public Admin build() {
            return new Admin(this);
        }
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", products='" + products + '\'' +
                ", orders='" + orders + '\'' +
                '}';
    }
}
