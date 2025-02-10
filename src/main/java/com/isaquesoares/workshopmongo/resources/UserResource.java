package com.isaquesoares.workshopmongo.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaquesoares.workshopmongo.model.User;
import com.isaquesoares.workshopmongo.model.dto.UserDTO;
import com.isaquesoares.workshopmongo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService service;

    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDTO userDTO) {
        Map<String, String> response = service.login(userDTO.getEmail(), userDTO.getPassword());

        if ("Login realizado com sucesso!".equals(response.get("message"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response); // Retorna código HTTP 401 (Não autorizado)
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDTO userDTO) {
        Map<String, String> response = new HashMap<>();

        if (userDTO == null || userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            response.put("message", "Erro: O corpo da requisição está vazio ou o e-mail não foi informado!");
            return ResponseEntity.badRequest().body(response);
        }

        boolean success = service.register(userDTO);

        if (success) {
            response.put("message", "Usuário cadastrado com sucesso!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Erro: Usuário já cadastrado!");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
