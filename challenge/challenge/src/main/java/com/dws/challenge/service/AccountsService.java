package com.dws.challenge.service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.FundTransfer;
import com.dws.challenge.exception.InsufficientBalanceException;
import com.dws.challenge.repository.AccountsRepository;
import com.dws.challenge.web.Slf4j;
import com.sun.tools.sjavac.Log;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;

  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }

  @Transactional
  public static synchronized Boolean fundTransfer(FundTransfer fundTransfer) {
	try {
		Account sender = this.accountsRepository.getAccount(fundTransfer.getFromAccount());
		Account reciever = this.accountsRepository.getAccount(fundTransfer.getToAccount());
		if( sender.getBalance() < fundTransfer.getAmount()) {
			throw new InsufficientBalanceException("Payer Does not have sufficient balance to complete this transaction");
		}
		else {
			reciever.setBalance(reciever.getBalance() + fundTransfer.getAmount() );
			sender.setbalance(payer.getbalance() - fundTransfer.getAmount());
			//persist both sender and receiver accounts in db
			Thread.sleep(1000);
		}
	}catch(InterruptedException ex) {
		log.error(String.format("Exception occured while doing transaction between accounts %s and %s", sender.getAccountId(),reciever.getAccountId()));
		throw new RuntimeException("Exception occured while doing transaction");
	}catch(Exception ex) {
		log.error("Something went wrong");
		throw new RuntimeException("Exception occured while doing transaction");
	}
	return false;
  }
}
