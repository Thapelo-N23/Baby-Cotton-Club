//package za.ac.cput.factory;
//
//import za.ac.cput.domain.Admin;
//import za.ac.cput.domain.UserRole;
//
//public class AdminFactory {
//    public static Admin createAdmin(String username, String password, String email, UserRole role) {
//        if (username == null || username.isEmpty()) throw new IllegalArgumentException("Username is required");
//        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password is required");
//        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email is required");
//        if (role == null) throw new IllegalArgumentException("Role is required");
//
//        return new Admin.Builder()
//                .username(username)
//                .password(password)
//                .email(email)
//                .role(role)
//                .build();
//    }
//}
//
