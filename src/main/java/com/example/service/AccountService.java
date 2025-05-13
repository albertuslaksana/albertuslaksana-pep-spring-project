package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
        private AccountRepository accountRepository;

        @Autowired
        public AccountService(AccountRepository accountRepository){
            this.accountRepository = accountRepository;
        }

        public Account makeAccount(Account account){
            if(account.getUsername().isBlank() || account.getPassword().length() < 4){
                throw  new IllegalStateException();
            }
            if(!accountRepository.findByUsername(account.getUsername()).isEmpty()){
                throw new IllegalArgumentException();
            }
            return accountRepository.save(account);
        }

        public Account login(Account account){
            if(accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()).isEmpty()){
                return null;
            }
            return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()).get();

        }

}
