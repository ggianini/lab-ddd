package br.com.lab.impacta.account.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private String customer;

    private Double balance;

    public boolean debit(double valueOfDebit) {

        if (this.getBalance() < valueOfDebit) {
            return false;
        }
 
        double debitedAmount = this.getBalance() - valueOfDebit;
        this.setBalance(debitedAmount);

        return true;
    }

}
