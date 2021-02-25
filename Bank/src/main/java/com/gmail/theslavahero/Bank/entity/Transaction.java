package com.gmail.theslavahero.Bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_from")
    private Account from;

    @ManyToOne
    @JoinColumn(name = "account_to")
    private Account to;

    private String currency;
    private Double amount;

    public Transaction(String currency, Double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
    public static void doTransaction(Transaction transaction, EntityManager em) {
        Account from = transaction.getFrom();
        Account to = transaction.getTo();
        String currency = transaction.getCurrency();
        Double amount = transaction.getAmount();
        Double currencyFrom = from.getCurrency(currency);
        Double currencyTo = to.getCurrency(currency);
        currencyFrom = currencyFrom - amount;
        currencyTo = currencyTo + amount;
        from.setCurrency(currency, currencyFrom);
        to.setCurrency(currency, currencyTo);

    }
}
