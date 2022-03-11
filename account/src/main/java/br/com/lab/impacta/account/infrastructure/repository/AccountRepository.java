package br.com.lab.impacta.account.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lab.impacta.account.domain.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
