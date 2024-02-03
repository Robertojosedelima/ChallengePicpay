package br.com.ChallengePicpay.ApiPay.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "TB_ACCOUNT_TRANSACTION", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@Data
public class AccountTransactionModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // geração automática do ID
	@ManyToOne
    @JoinColumn(name = "sender_cpf_cnpj")
	private UserModel senderUserModel;
	@ManyToOne
	@JoinColumn(name = "receiver_cpf_cnpj")
	private UserModel receiverUserModel;
	private String    typeAccount;
	private String    inf;
	private String    typeTransf;
	private float     transferValue;
	
	
	
}
