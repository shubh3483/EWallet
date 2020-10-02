package com.example.WalletService1.ValidatorChecker;

import com.example.WalletService1.Model.Wallet;

public class WalletValidator {

    public boolean isValid(Wallet wallet){

        String id = String.valueOf(wallet.getUser_id());

        if(id==null || id==" "){
            return false;
        }
        return true;
    }
}
