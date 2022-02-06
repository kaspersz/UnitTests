package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void myTest(){
        //given tworzymy obiekty, ustawiamy stan poczatkowy albo suatwiamy zalenosci
        Account account = new Account(false);

        //when operacje ktorych dzialanie chcemy przetestowac
        assertFalse(account.isActive(), "Check if account is not active");
    }

    @Test
    void myTest2(){
        //given
        Account account = new Account();
        //when
        account.activate();
        //then
        assertTrue(account.isActive());
    }
}