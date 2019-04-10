package com.smecosystem_rest.smecosystem_rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.smecosystem_rest.smecosystem_rest.exception.ResourceNotFoundException;
import com.smecosystem_rest.smecosystem_rest.model.User;
import com.smecosystem_rest.smecosystem_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRestService")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/getUserWalletAddress/{id}")
    public ResponseEntity<String> getUserWalletAddress(
            @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
        return ResponseEntity.ok().body(user.getWalletAddress());
    }


    @GetMapping("/setPrivateKey/{id}/{private_key}")
    public ResponseEntity<String> setPrivateKey(
            @PathVariable(value = "id") Long userId, @PathVariable(value = "private_key") String privateKey) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        user.setPrivateKey(privateKey);
        userRepository.save(user);
        return ResponseEntity.ok().body("Private key updated");
    }

    @GetMapping("/setPublicKey/{id}/{public_key}")
    public ResponseEntity<String> setPublicKey(
            @PathVariable(value = "id") Long userId, @PathVariable(value = "public_key") String publicKey) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
        user.setPublicKey(publicKey);
        userRepository.save(user);
        return ResponseEntity.ok().body("Public key updated");
    }

    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId,
            @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));

        user.setEmailId(userDetails.getEmailId());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setUpdatedAt(new Date());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}