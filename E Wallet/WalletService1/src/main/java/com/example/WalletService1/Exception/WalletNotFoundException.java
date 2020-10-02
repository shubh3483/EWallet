package com.example.WalletService1.Exception;

public class WalletNotFoundException extends RuntimeException{

    public WalletNotFoundException() {
        super("Wallet not found");
    }
}
