package org.example;

import com.gmail.theslavahero.Bank.entity.Account;
import com.gmail.theslavahero.Bank.entity.Clients;
import com.gmail.theslavahero.Bank.entity.Transaction;
import org.junit.Test;

import java.util.concurrent.Callable;

import static com.gmail.theslavahero.Bank.entity.Transaction.doTransaction;
import static org.junit.Assert.*;

public class TransactionTest extends BaseTest {




    @Test
    public void testTransaction() {

        final Clients client1 = new Clients("Segrei");
        final Account account1 = new Account(984.21, 651.22, 132.55);
        client1.setAccount(account1);
        account1.setClient(client1);
        final Clients client2 = new Clients("Andrei");
        final Account account2 = new Account(51321.25, 0.0, 0.0);
        client2.setAccount(account2);
        account2.setClient(client2);

        final Transaction transaction = new Transaction("USD",100.0);
        transaction.setFrom(account1);
        transaction.setTo(account2);

        performTransaction(new Callable<Transaction>() {
            public Transaction call() throws Exception {
                doTransaction(transaction, em);
                return new Transaction();
            }
        });
        assertNotEquals((Double) 651.22, account1.getUSD());
        assertEquals((Double) 551.22, account1.getUSD());
        assertEquals((Double) 100.0, account2.getUSD());

    }

}
