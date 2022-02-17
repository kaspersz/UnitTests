package testing.account;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    @Test
    void shouldReturnAllActiveUsers(){
        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        List<Account> accounts = prepareAccountData();
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(accounts);
        //when
        List<Account> list = accountService.getAllActivesAccounts();
        //then
        assertThat(list, hasSize(2));
    }
    @Test
    void shouldNotReturnAnyActiveUser(){
        //given
        AccountRepository accountRepository =  mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(new ArrayList<>());
        //when
        List<Account> accounts = accountService.getAllActivesAccounts();
        //then
        assertThat(accounts, hasSize(0));
    }

    private List<Account> prepareAccountData(){
        Account account = new Account();
        Address address = new Address("Wodzislawska", "55");
        Address address1 = new Address("Radlinska", "57");
        Account account1 = new Account(address);
        Account account2 = new Account(address1);

        return new ArrayList<>(Arrays.asList(account, account1, account2));
    }
}
