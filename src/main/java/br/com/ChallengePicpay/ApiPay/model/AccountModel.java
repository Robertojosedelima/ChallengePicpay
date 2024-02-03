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
@Table(name = "TB_ACCOUNT", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@Data
public class AccountModel implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // geração automática do ID

    @ManyToOne
    @JoinColumn(name = "cpf/cnpj")
    private UserModel userModel;

    private float currentBalance;
    private float savingsBalance;
}
