package org.example.be_sua.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.user.domain.User;
import org.example.be_sua.domain.user.domain.repository.UserRepository;
import org.example.be_sua.domain.user.exception.UserNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserRepository userRepository;

    public User getUserByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId).orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }

    public User getCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByAccountId(accountId);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
