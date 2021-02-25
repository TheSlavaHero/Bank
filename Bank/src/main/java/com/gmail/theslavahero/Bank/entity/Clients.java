package com.gmail.theslavahero.Bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class Clients {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    private Account account;
    private String name;

    public Clients(String name) {
        this.name = name;
    }

    public Clients(Long id) {
        this.id = id;
    }

    public Clients() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
