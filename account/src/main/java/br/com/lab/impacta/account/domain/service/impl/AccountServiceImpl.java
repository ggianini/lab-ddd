package br.com.lab.impacta.account.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.lab.impacta.account.domain.exception.AccountNotFoundException;
import br.com.lab.impacta.account.domain.exception.InsufficientAccountBalanceException;
import br.com.lab.impacta.account.domain.model.Account;
import br.com.lab.impacta.account.domain.service.AccountService;
import br.com.lab.impacta.account.infrastructure.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Value("${lab.account.exceptions.account-not-found-message}")
    private String messageExceptionAccountNotFound;

    @Value("${lab.account.exceptions.account-not-found-description}")
    private String descriptionExceptionAccountNotFound;

    @Value("${lab.account.exceptions.insufficient-account-balance-message}")
    private String messageExceptionInsufficientAccountBalance;

    @Value("${lab.account.exceptions.insufficient-account-balance-description}")
    private String descriptionExceptionInsufficientAccountBalance;

    @Override
    public Account find(Long accountId) {

        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty())
            throw new AccountNotFoundException(
                    messageExceptionAccountNotFound,
                    descriptionExceptionAccountNotFound);

        return account.get();

    }

    @Override
    public void debit(Long accountId, Double valueOfDebit) {
        Account account = this.find(accountId);
        boolean insufficientBalance = !account.debit(valueOfDebit);

        if (insufficientBalance)
            throw new InsufficientAccountBalanceException(
                    messageExceptionInsufficientAccountBalance,
                    descriptionExceptionInsufficientAccountBalance);

        account.debit(valueOfDebit);
        accountRepository.save(account);
    }

}
