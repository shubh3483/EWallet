package com.example.UserService.Validator;

import com.example.UserService.Model.EWalletUser;

public class UserValidator {
    public static boolean userValidator(EWalletUser EWalletUser){
        if(EWalletUser.getEmail()==null || EWalletUser.getEmail() == " " || EWalletUser.getMobile_no()==null || EWalletUser.getMobile_no()==" " || EWalletUser.getUsername()==null || EWalletUser.getUsername()==" ")
            return false;
        return true;
    }

}
