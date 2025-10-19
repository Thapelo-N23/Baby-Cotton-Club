/**
 * BabyCottonClub
 * PasswordResetController.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 18 October 2025
 */

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.PasswordResetToken;
import za.ac.cput.service.impl.CustomerService;
import za.ac.cput.service.impl.PasswordResetTokenService;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/password")
public class PasswordResetTokenController {

    private final CustomerService customerService;
    private final PasswordResetTokenService tokenService;

    @Autowired
    public PasswordResetTokenController(CustomerService customerService, PasswordResetTokenService tokenService) {
        this.customerService = customerService;
        this.tokenService = tokenService;
    }

    // Generate password reset token
    @PostMapping("/forgot")
    public PasswordResetToken forgotPassword(@RequestParam("email") String email) {
        Customer customer = customerService.getAll()
                .stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            return null; // email not found
        }


        // Create new token
        String tokenValue = UUID.randomUUID().toString();
        PasswordResetToken token = new PasswordResetToken.Builder()
                .setToken(tokenValue)
                .setExpiryDate(LocalDateTime.now().plusMinutes(15))
                .setCustomer(customer)
                .build();

        return tokenService.create(token);
    }

    // Reset password using token
    @PostMapping("/reset")
    public Customer resetPassword(
            @RequestParam("token") String tokenValue,
            @RequestParam("newPassword") String newPassword) {

        PasswordResetToken token = tokenService.findByToken(tokenValue);

        if (token == null || token.getExpiryDate().isBefore(LocalDateTime.now())) {
            return null; // invalid or expired token
        }

        // ✅ Fetch the existing persisted customer
        Customer existingCustomer = customerService.read(token.getCustomer().getCustomerId());
        if (existingCustomer == null) {
            throw new RuntimeException("Customer not found");
        }

        // ✅ Update password only (CustomerService.update() will encode it)
        existingCustomer.setPassword(newPassword);
        Customer updated = customerService.update(existingCustomer);

        // 🧹 Optional: Invalidate the token after use
       // tokenService.delete(token.getToken());

        return updated;
    }

}
