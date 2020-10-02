package com.example.UserService.Repository;

import com.example.UserService.Model.EWalletUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<EWalletUser,Integer> {



}
