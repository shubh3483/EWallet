package com.example.WalletService1.Controller;

import com.example.WalletService1.Exception.TransactionBadRequest;
import com.example.WalletService1.Model.AddBalanceDetails;
import com.example.WalletService1.Model.User;
import com.example.WalletService1.Model.Wallet;
import com.example.WalletService1.Model.Transaction;
import com.example.WalletService1.Repository.TransactionRepo;
import com.example.WalletService1.Repository.WalletRepo;
import com.example.WalletService1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TransactionResource {

    @Autowired
    private WalletRepo walletRepository;
    @Autowired
    private TransactionRepo trepository;

    @Autowired
    private UserService userService;


    @PostMapping("/sendMoney")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Transaction sendMoney(@RequestBody Transaction transactionResource) throws Exception {


        transactionResource.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        User sender = userService.getAUser(String.valueOf(transactionResource.getSid()));
        User receiver = userService.getAUser(String.valueOf(transactionResource.getRid()));
        if(sender==null || receiver==null){

            throw new TransactionBadRequest();
        }

        Wallet senderWallet = walletRepository.findWalletByUserId(sender.getId());
        Wallet receiverWallet = walletRepository.findWalletByUserId(receiver.getId());

        int amt = transactionResource.getAmount();

        if (senderWallet.getBalance() < amt) {
            throw new Exception("Not Sufficient Balance");
        }
        senderWallet.setBalance(senderWallet.getBalance()-amt);

        receiverWallet.setBalance(receiverWallet.getBalance()+amt);

        transactionResource.setStatus("SUCCESS");

        walletRepository.save(receiverWallet);
        walletRepository.save(senderWallet);
        return trepository.save(transactionResource);
    }


    @GetMapping("/getBal/{id}")
    int getBal(@PathVariable int id) throws Exception {

        Wallet wallet = walletRepository.findWalletByUserId(id);

        if(wallet==null) throw new Exception("Wallet Not Found");
        else {
            return wallet.getBalance();
        }
    }

    @PostMapping("/addBalance")
    AddBalanceDetails addBalance(@RequestBody AddBalanceDetails request){
        Wallet wallet = walletRepository.findWalletByUserId(request.getUid());

        wallet.setBalance(request.getAmount()+wallet.getBalance());
        walletRepository.save(wallet);
        return request;
    }


}
