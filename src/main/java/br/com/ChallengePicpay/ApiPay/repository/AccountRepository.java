package br.com.ChallengePicpay.ApiPay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ChallengePicpay.ApiPay.model.AccountModel;
import br.com.ChallengePicpay.ApiPay.model.UserModel;
@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long>{
	

	AccountModel findByUserModel(UserModel userModel);
}