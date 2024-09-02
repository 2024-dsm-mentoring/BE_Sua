package org.example.be_sua.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.user.domain.repository.UserRepository;
import org.example.be_sua.domain.user.exception.AlreadyUserException;
import org.example.be_sua.domain.user.presentation.dto.request.SignUpRequest;
import org.example.be_sua.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignupService {

    private final UserRepository userRepository;

    public void signup(SignUpRequest request) {

        if(userRepository.existsByAccountId(request.getAccountId())) throw AlreadyUserException.EXCEPTION;

        User user = userRepository.save(User.builder()
                .email(request.getEmail())
                .accountId(request.getAccountId())
                .password((request.getPassword()))
                .build());
    }
}
