package org.ejemplo.validations;

import org.ejemplo.exceptions.UserRegistrationException;
import org.ejemplo.repositories.users.UserRepository;
import org.springframework.http.HttpStatus;

public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateExistingUser(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserRegistrationException(HttpStatus.PRECONDITION_FAILED, "El usuario ya existe", "UserRegistrationException");
        }
    }

    public void validateRole(String role) {
        if (!role.equalsIgnoreCase("administrador") && !role.equalsIgnoreCase("vendedor")) {
            throw new UserRegistrationException(HttpStatus.PRECONDITION_FAILED, "El rol no es válido", "UserRegistrationException");
        }
    }
}


