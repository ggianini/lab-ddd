package br.com.lab.impacta.account.domain.service;

import br.com.lab.impacta.account.domain.model.Account;

public interface AccountService {

    Account find(Long AccountId);
    void debit(Long accountId, Double valueOfDebit);

}
