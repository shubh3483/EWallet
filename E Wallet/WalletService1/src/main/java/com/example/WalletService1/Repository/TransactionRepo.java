package com.example.WalletService1.Repository;

import com.example.WalletService1.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
}
