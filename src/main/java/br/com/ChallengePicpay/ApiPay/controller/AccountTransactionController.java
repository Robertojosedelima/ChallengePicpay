package br.com.ChallengePicpay.ApiPay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ChallengePicpay.ApiPay.model.AccountTransactionModel;
import br.com.ChallengePicpay.ApiPay.service.AccountTransactionService;

@RestController
@RequestMapping(value = "/transaction")
public class AccountTransactionController {
    @Autowired
    AccountTransactionService accountTransactionService;


    @PostMapping
    public String addTransaction(@RequestBody AccountTransactionModel accountTransactionModel) {
        return accountTransactionService.startTransaction(accountTransactionModel);
    }
}



