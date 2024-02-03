package br.com.ChallengePicpay.ApiPay.service;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ChallengePicpay.ApiPay.model.AccountModel;
import br.com.ChallengePicpay.ApiPay.model.AccountTransactionModel;
import br.com.ChallengePicpay.ApiPay.repository.AccountRepository;
import br.com.ChallengePicpay.ApiPay.repository.AccountTransactionRepository;
import jakarta.transaction.Transactional;

@Service
public class AccountTransactionService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountTransactionRepository accountTransactionRepository;

	@Transactional
	public String startTransaction(AccountTransactionModel accountTransactionModel) {
		try {
			// transaction account origin
			AccountModel accountModelOrigin = new AccountModel();

			accountModelOrigin = accountRepository.findByUserModel(accountTransactionModel.getSenderUserModel());
			validTypeClient(accountTransactionModel);
			if (accountModelOrigin.getCurrentBalance() < accountTransactionModel.getTransferValue()) {
				throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência");
			}
			accountModelOrigin.setCurrentBalance(
					(accountModelOrigin.getCurrentBalance()) - (accountTransactionModel.getTransferValue()));
			// registration transaction creation
			accountTransactionRepository.save(accountTransactionModel);
			// debit value transaction
			accountRepository.save(accountModelOrigin);

			// transaction account destiny
			AccountModel accountModelDestiny = new AccountModel();
			AccountTransactionModel accountTransactionModelDestiny = new AccountTransactionModel();
			accountModelDestiny = accountRepository.findByUserModel(accountTransactionModel.getReceiverUserModel());
			accountModelDestiny.setCurrentBalance(
					(accountModelDestiny.getCurrentBalance()) + (accountTransactionModel.getTransferValue()));
			// registration transaction creation
			accountTransactionModelDestiny.setSenderUserModel(accountTransactionModel.getSenderUserModel());
			accountTransactionModelDestiny.setReceiverUserModel(accountTransactionModel.getReceiverUserModel());
			accountTransactionModelDestiny.setTypeAccount(accountTransactionModel.getTypeAccount());
			accountTransactionModelDestiny.setTransferValue(accountTransactionModel.getTransferValue());
			accountTransactionModelDestiny.setTypeTransf("ENTRY");
			accountTransactionModelDestiny.setInf("ENTRADA TESTE");
			accountTransactionRepository.save(accountTransactionModelDestiny);

			// debit value transaction
			accountRepository.save(accountModelDestiny);
			String serviceUrl = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";
			String sendProffUrl = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";
			int responseCode = authorizationService(serviceUrl);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				int responseSendProff = sendProof(sendProffUrl);
				if (responseSendProff == HttpURLConnection.HTTP_OK) {
					System.out.println("Email enviado para :"+accountModelDestiny.getUserModel().getEmail());
				}else {
					System.out.println("falha ao enviar e-mail");
				}
				
				return "Transaction Successful!";
			} else {
				throw new RuntimeException("Transferência não autorizada. Código de resposta: " + responseCode);

			}
		} catch (Exception e) {
			return "Erro na transação: " + e.getMessage();
		}
	}

	private void validTypeClient(AccountTransactionModel accountTransactionModel) throws Exception {
		try {
			// Remover caracteres não numéricos
			String cleanedNumber = accountTransactionModel.getSenderUserModel().getId().replaceAll("[^0-9]", "");

			// Verificar o comprimento para determinar se é um CPF
			int length = cleanedNumber.length();
			if (length != 11) {
				// Se não for um CPF, lançar uma exceção
				throw new IllegalArgumentException("Apenas usuário pessoa física pode realizar transferência!");
			}

			// Lógica adicional se o CPF for válido
		} catch (Exception e) {
			// Lançar uma exceção mais geral (ou tratá-la conforme necessário)
			throw new Exception("Erro na transação: " + e.getMessage());
		}
	}
//poderia ter usado restTamplate do spring
	private static int authorizationService(String url) throws Exception {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("GET");

		// Obtém o código de resposta do serviço autorizador
		return connection.getResponseCode();
	}
	
	private static int sendProof(String url) throws Exception {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("GET");

		// Obtém o código de resposta do serviço autorizador
		return connection.getResponseCode();
	}
	
	

}
