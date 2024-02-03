package br.com.ChallengePicpay.ApiPay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ChallengePicpay.ApiPay.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String>{

}
