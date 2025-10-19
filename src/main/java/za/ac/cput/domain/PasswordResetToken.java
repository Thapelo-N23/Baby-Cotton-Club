/**
 * BabyCottonClub
 * PasswordResetToken.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 18 October 2025
 */

package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;

    public PasswordResetToken() {}

    public PasswordResetToken(Builder builder) {
        this.tokenId = builder.tokenId;
        this.token = builder.token;
        this.expiryDate = builder.expiryDate;
        this.customer = builder.customer;
    }

    public Long getTokenId() { return tokenId; }
    public String getToken() { return token; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public Customer getCustomer() { return customer; }

    public void setToken(String token) { this.token = token; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "tokenId=" + tokenId +
                ", token='" + token + '\'' +
                ", expiryDate=" + expiryDate +
                ", customerId=" + (customer != null ? customer.getCustomerId() : null) +
                '}';
    }

    public static class Builder {
        private Long tokenId;
        private String token;
        private LocalDateTime expiryDate;
        private Customer customer;

        public Builder setTokenId(Long tokenId) { this.tokenId = tokenId; return this; }
        public Builder setToken(String token) { this.token = token; return this; }
        public Builder setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; return this; }
        public Builder setCustomer(Customer customer) { this.customer = customer; return this; }

        public Builder copy(PasswordResetToken tokenObj) {
            this.tokenId = tokenObj.tokenId;
            this.token = tokenObj.token;
            this.expiryDate = tokenObj.expiryDate;
            this.customer = tokenObj.customer;
            return this;
        }

        public PasswordResetToken build() { return new PasswordResetToken(this); }
    }
}
