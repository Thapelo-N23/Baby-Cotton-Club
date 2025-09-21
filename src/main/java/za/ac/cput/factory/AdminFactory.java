package za.ac.cput.factory;

import za.ac.cput.domain.Admin;

public class AdminFactory {
    public static Admin createAdmin(String password, String email) {
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password is required");
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email is required");

        return new Admin.Builder()
                .password(password)
                .email(email)
                .build();
    }
}

