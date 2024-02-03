package br.com.ChallengePicpay.ApiPay.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "TB_USER", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "email"}))
@Data
public class UserModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cpf/cnpj", unique = true, nullable = false)
	@NotBlank
	private String id;
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	@NotBlank
	private String password;
	
	

}
