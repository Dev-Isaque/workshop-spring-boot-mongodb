package com.isaquesoares.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaquesoares.workshopmongo.model.User;
import com.isaquesoares.workshopmongo.model.dto.UserDTO;
import com.isaquesoares.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin("*")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        String message = service.register(
                userDTO.getName(), userDTO.getCpf(), userDTO.getDataNasc(),
                userDTO.getSexo(), userDTO.getTelefone(), userDTO.getCep(),
                userDTO.getEndereco(), userDTO.getBairro(), userDTO.getCidade(),
                userDTO.getEmail(), userDTO.getPassword());
        return ResponseEntity.ok(message);
    }
}
