package org.example;

import com.gmail.theslavahero.Bank.entity.Account;
import com.gmail.theslavahero.Bank.entity.Clients;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountTest extends BaseTest {

    private TwoIDs saveTestClientWithAccount(String name, Double UAH, Double USD, Double EUR) {
        final Clients client = new Clients(name);
        final Account account = new Account(UAH, USD, EUR);

        client.setAccount(account);
        account.setClient(client);

        return performTransaction(new Callable<TwoIDs>() {
            public TwoIDs call() throws Exception {
                em.persist(client);
                return new TwoIDs(client.getId(), account.getId());
            }
        });
    }

    @Test
    public void testPersist() {
        TwoIDs ids = saveTestClientWithAccount("Nick", 10.0, 20.0, 30.0);
        em.clear();

        Clients otherClient = em.find(Clients.class, ids.getClientId());
        assertNotNull(otherClient);

        Account otherAccount = otherClient.getAccount();
        assertEquals((Double) 10.0, otherAccount.getUAH());

        em.clear();

        otherAccount = em.find(Account.class, ids.getAddressId());
        assertNotNull(otherAccount);

        otherClient = otherAccount.getClient();
        assertNotNull(otherClient);
        assertEquals("Nick", otherClient.getName());
    }
    @Test
    public void totalAmount() {
        final Clients client1 = new Clients("Segrei");
        final Account account1 = new Account(984.21, 651.22, 132.55);
        client1.setAccount(account1);
        account1.setClient(client1);
        final Clients client2 = new Clients("Andrei");
        final Account account2 = new Account(51321.25, 0.0, 0.0);
        client2.setAccount(account2);
        account2.setClient(client2);
        assertEquals((Double) 23556.3865, account1.getTotal());
        assertEquals((Double) 51321.25, account2.getTotal());
    }
    @Test
    public void changeCurrency() {
        final Clients client1 = new Clients("Segrei");
        final Account account1 = new Account(984.21, 651.22, 132.55);
        client1.setAccount(account1);
        account1.setClient(client1);

        account1.changeCurrencyAmount("UAH", 100.0);
        account1.changeCurrencyAmount("EUR", -132.55);
        assertEquals((Double) 1084.21, account1.getUAH());
        assertEquals((Double) 0.0, account1.getEUR());

    }
}

