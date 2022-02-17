package testing.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository {
    @Override
    public List<Account> getAllAccounts() {

        Account account = new Account();
        Address address = new Address("Wodzislawska", "55");
        Address address1 = new Address("Radlinska", "57");
        Account account1 = new Account(address);
        Account account2 = new Account(address1);

        return new ArrayList<>(Arrays.asList(account, account1, account2));
    }
}
