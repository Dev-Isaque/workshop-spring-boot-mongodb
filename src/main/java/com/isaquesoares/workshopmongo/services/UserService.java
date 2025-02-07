package com.isaquesoares.workshopmongo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaquesoares.workshopmongo.model.User;
import com.isaquesoares.workshopmongo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String register(String name, String cpf, String dataNasc, String sexo, String telefone,
            String cep, String endereco, String bairro, String cidade,
            String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            return "Usuário já cadastrado!";
        }

        User newUser = new User(null, name, cpf, dataNasc, sexo, telefone, cep, endereco, bairro, cidade, email,
                password);
        userRepository.save(newUser);
        return "Usuário cadastrado com sucesso!";
    }

    public Map<String, String> login(String email, String password) {
        User user = userRepository.findByEmail(email);
        Map<String, String> response = new HashMap<>();

        if (user != null && user.getPassword().equals(password)) {
            response.put("message", "Login realizado com sucesso!");
            response.put("userId", user.getId());
        } else {
            response.put("message", "E-mail ou senha inválidos!");
        }

        return response;
    }
}
