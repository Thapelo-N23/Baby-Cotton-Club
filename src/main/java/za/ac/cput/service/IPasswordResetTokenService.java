package za.ac.cput.service;

import za.ac.cput.domain.PasswordResetToken;

public interface IPasswordResetTokenService extends IService <PasswordResetToken, Long>{
    PasswordResetToken findByToken(String token);

}
