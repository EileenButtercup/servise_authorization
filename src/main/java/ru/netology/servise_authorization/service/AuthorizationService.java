package ru.netology.servise_authorization.service;

import org.springframework.stereotype.Service;
import ru.netology.servise_authorization.exception.InvalidCredentials;
import ru.netology.servise_authorization.exception.UnauthorizedUser;
import ru.netology.servise_authorization.model.Authorities;
import ru.netology.servise_authorization.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("USER NAME OR PASSWORD IS EMPTY");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("UNKNOWN USER " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
