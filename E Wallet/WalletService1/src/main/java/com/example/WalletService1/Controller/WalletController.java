package com.example.WalletService1.Controller;

import com.example.WalletService1.Exception.WalletNotFoundException;
import com.example.WalletService1.Model.Wallet;
import com.example.WalletService1.Repository.WalletRepo;
import com.example.WalletService1.ValidatorChecker.WalletValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class WalletController {

    @Autowired
    WalletRepo walletRepo;

    WalletValidator wv = new WalletValidator();

    @PostMapping("/Createwallet")
    boolean newWallet(@RequestBody Wallet wallet){
        if(wv.isValid(wallet)){
            walletRepo.saveAll(Collections.singletonList(wallet));
            return true;
        }
        return false;
    }

    @GetMapping("/walletbyid/{id}")
    Wallet getUserById(@PathVariable int id){
        return walletRepo.findById(id).orElseThrow(()->new WalletNotFoundException());
    }

    @PostMapping("/allwallet")
    List allWallet(){
       List<Wallet> wallets = new ArrayList<>();
       wallets = walletRepo.findAll();
       return wallets;
    }

    @PutMapping("/updatewallet")
    boolean updateWallet(@RequestBody Wallet wallet){
        List<Wallet> wallets = new ArrayList<>();
        wallets = walletRepo.findAll();
        for (Wallet w:wallets) {
            if(w.getUser_id()==wallet.getUser_id()){
                walletRepo.saveAndFlush(wallet);
                return true;
            }
        }
        return false;
    }
}
