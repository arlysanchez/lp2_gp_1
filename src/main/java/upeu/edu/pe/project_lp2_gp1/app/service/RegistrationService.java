/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.UserEntity;

/**
 *
 * @author LAB-2
 */
public class RegistrationService {
    private final UserServices userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserServices userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
    }
}
