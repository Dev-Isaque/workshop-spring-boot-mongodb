package com.isaquesoares.workshopmongo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaquesoares.workshopmongo.model.User;
import com.isaquesoares.workshopmongo.model.dto.UserDTO;
import com.isaquesoares.workshopmongo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean register(UserDTO userDTO) {
        if (userDTO == null || userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            return false;
        }
        String emailLower = userDTO.getEmail().trim().toLowerCase();
        // Verifica se o usuário já existe pelo e-mail
        Optional<User> existingUser = userRepository.findByEmail(emailLower);
        if (existingUser.isPresent()) {
            return false;
        }

        User newUser = new User(
                null,
                userDTO.getName(),
                userDTO.getCpf(),
                userDTO.getDataNasc(),
                userDTO.getSexo(),
                userDTO.getTelefone(),
                userDTO.getCep(),
                userDTO.getEndereco(),
                userDTO.getBairro(),
                userDTO.getCidade(),
                emailLower,
                userDTO.getPassword());

        userRepository.save(newUser);
        return true;
    }

    public Map<String, String> login(String email, String password) {
        Map<String, String> response = new HashMap<>();

        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            response.put("message", "E-mail e senha são obrigatórios!");
            return response;
        }

        String emailLower = email.trim().toLowerCase();
        Optional<User> userOptional = userRepository.findByEmail(emailLower);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            response.put("message", "Login realizado com sucesso!");
            response.put("userId", userOptional.get().getId());
        } else {
            response.put("message", "E-mail ou senha inválidos!");
        }

        return response;
    }
}
