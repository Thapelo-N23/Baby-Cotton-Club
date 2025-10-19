/**
 * BabyCottonClub
 * PasswordResetTokenService.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 18 October 2025
 */

package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.PasswordResetToken;
import za.ac.cput.repository.PasswordResetTokenRepository;
import za.ac.cput.service.IPasswordResetTokenService;

import java.util.List;

@Service
public class PasswordResetTokenService implements IPasswordResetTokenService {

    private final PasswordResetTokenRepository tokenRepository;

    @Autowired
    public PasswordResetTokenService(PasswordResetTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public PasswordResetToken create(PasswordResetToken token) {
        return tokenRepository.save(token);
    }

    @Override
    public PasswordResetToken read(Long tokenId) {
        return tokenRepository.findById(tokenId).orElse(null);
    }

    @Override
    public PasswordResetToken update(PasswordResetToken token) {
        if (tokenRepository.existsById(token.getTokenId())) {
            return tokenRepository.save(token);
        }
        return null;
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return tokenRepository.findByToken(token).orElse(null);
    }

}
