package br.com.ChallengePicpay.ApiPay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ChallengePicpay.ApiPay.model.AccountTransactionModel;
@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransactionModel, Long>{

}
