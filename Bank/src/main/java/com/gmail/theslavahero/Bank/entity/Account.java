package com.gmail.theslavahero.Bank.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "account")
    private Clients client;
    private Double UAH;
    private Double USD;
    private Double EUR;

    @OneToMany(mappedBy = "to", cascade = CascadeType.ALL)
    private List<Transaction> transactionsTo = new ArrayList<>();

    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
    private List<Transaction> transactionsFrom = new ArrayList<>();

    public Account(Double UAH, Double USD, Double EUR) {
        this.UAH = UAH;
        this.USD = USD;
        this.EUR = EUR;
    }

    public Account() {
    }

    public Double getCurrency(String currency) {
        if (currency.equals("UAH")) { return this.UAH;}
        if (currency.equals("USD")) { return this.USD;}
        if (currency.equals("EUR")) { return this.EUR;}
        else return null;
    }

    public void setCurrency(String currency, Double amount) {
        if (currency.equals("UAH")) { this.UAH = amount;}
        if (currency.equals("USD")) { this.USD = amount;}
        if (currency.equals("EUR")) { this.EUR = amount;}
    }

    public List<Transaction> getTransactionsTo() {
        return transactionsTo;
    }

    public void setTransactionsTo(List<Transaction> transactionsTo) {
        this.transactionsTo = transactionsTo;
    }

    public List<Transaction> getTransactionsFrom() {
        return transactionsFrom;
    }

    public void setTransactionsFrom(List<Transaction> transactionsFrom) {
        this.transactionsFrom = transactionsFrom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Double getUAH() {
        return UAH;
    }

    public void setUAH(Double UAH) {
        this.UAH = UAH;
    }

    public Double getUSD() {
        return USD;
    }

    public void setUSD(Double USD) {
        this.USD = USD;
    }

    public Double getEUR() {
        return EUR;
    }

    public void setEUR(Double EUR) {
        this.EUR = EUR;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", UAH=" + UAH +
                ", USD=" + USD +
                ", EUR=" + EUR +
                '}';
    }

    public Double getTotal() {
        return (this.UAH + (this.EUR * 33.71) + (this.USD * 27.80));
    }

    public void changeCurrencyAmount(String currency, Double amount) {
        Double currencyDouble = this.getCurrency(currency);
        currencyDouble = currencyDouble + amount;
        this.setCurrency(currency, currencyDouble);
    }
}

