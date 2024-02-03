package br.com.ChallengePicpay.ApiPay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ChallengePicpay.ApiPay.model.AccountModel;
import br.com.ChallengePicpay.ApiPay.model.UserModel;
import br.com.ChallengePicpay.ApiPay.repository.AccountRepository;
import br.com.ChallengePicpay.ApiPay.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;

	@GetMapping
	public List<UserModel>  GetUser() {
		return userRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<UserModel> addUser(@RequestBody @Valid UserModel userModel) {
		AccountModel accountModel =  new AccountModel();
		accountModel.setUserModel(userModel);
		userRepository.save(userModel);
		accountRepository.save(accountModel);
		return ResponseEntity.status(HttpStatus.CREATED)
				             .body(userModel);
		
		
		
	};

}
