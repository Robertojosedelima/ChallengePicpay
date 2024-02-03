package br.com.ChallengePicpay.ApiPay.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.ChallengePicpay.ApiPay.dto.ExceptionDTO;
import br.com.ChallengePicpay.ApiPay.model.UserModel;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Email já utilizado","400");
		return ResponseEntity.badRequest().body(exceptionDTO);
	}

}
