package com.example.UserService.Controller;

import com.example.UserService.Exception.UserNotFoundException;
import com.example.UserService.Model.EWalletUser;
import com.example.UserService.Repository.UserRepo;
import com.example.UserService.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/adduser")
    boolean addNewUser(@RequestBody EWalletUser eWalletUser) {
        if (UserValidator.userValidator(eWalletUser)) {
            userRepo.saveAll(Collections.singletonList(eWalletUser));
            return true;

        }
        return false;
    }

    @PostMapping("/allusers")
    List getAllUsers() {
        List<EWalletUser> ewalletusers = new ArrayList<>();
        ewalletusers = userRepo.findAll();
        return ewalletusers;
    }

    @GetMapping("/userbyid/{id}")
    EWalletUser getUserById(@PathVariable int id) {

        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException());

    }

    @PutMapping("/updateuser")
    String updateUser(@RequestBody EWalletUser user) {

        List<EWalletUser> users = new ArrayList<>();
        users = userRepo.findAll();

        for (EWalletUser ew : users) {
            if (ew.getId() == user.getId()) {
                userRepo.saveAndFlush(user);
                return "User Updated";
            }

        }
        return "No Record found!Please register with us";

    }




}
