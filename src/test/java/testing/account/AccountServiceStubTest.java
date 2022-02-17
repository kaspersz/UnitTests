package testing.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class AccountServiceStubTest {

    @Test
    void shouldReturnAllActiveUsers(){
        //given
        AccountRepository accountRepository = new AccountRepositoryStub();

        AccountService accountService = new AccountService(accountRepository);
        //when
        List<Account> list = accountService.getAllActivesAccounts();
        //then
        assertThat(list, hasSize(2));
    }

}