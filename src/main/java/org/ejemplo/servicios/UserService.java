package org.ejemplo.servicios;

import org.ejemplo.exceptions.UserAuthenticationException;
import org.ejemplo.exceptions.UserException;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.User;
import org.ejemplo.repository.UserRepository;
import org.ejemplo.validations.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public void register(User user) {
        userValidator.validateExistingUser(user.getUsername());
        userValidator.validateRole(user.getRole());

        userRepository.save(user);
    }

    private boolean authenticateUser(Login login) {
        User userDB = userRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new UserAuthenticationException(HttpStatus.PRECONDITION_FAILED,
                        "Usuario no encontrado", "UserAuthenticationException"));

        return userDB.getPassword().equals(login.getPassword());
    }

    public String login(Login login) {
        if (authenticateUser(login)) {
            User userDB = userRepository.findByUsername(login.getUsername())
                    .orElseThrow(() -> new UserAuthenticationException(HttpStatus.PRECONDITION_FAILED,
                            "Usuario no encontrado", "UserAuthenticationException"));

            return userDB.getRole();
        } else {
            throw new UserAuthenticationException(HttpStatus.PRECONDITION_FAILED,
                    "Usuario o contraseña incorrectos", "UserAuthenticationException");
        }
    }

    public List<User> getAllUsers() throws UserException {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserException(HttpStatus.PRECONDITION_FAILED,
                    "No hay usuarios registrados", "UserException");
        }

        return users;
    }

    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException(HttpStatus.PRECONDITION_FAILED,
                        "Usuario no encontrado", "UserException"));
    }
}

