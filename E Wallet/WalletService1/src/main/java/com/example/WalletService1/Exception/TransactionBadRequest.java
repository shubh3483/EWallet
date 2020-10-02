package com.example.WalletService1.Exception;

public class TransactionBadRequest extends RuntimeException {

    public TransactionBadRequest(){
        super("TransactionBadRequest");
    }
}
