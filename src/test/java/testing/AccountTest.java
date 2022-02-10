package testing;


import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

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
       // assertTrue(account.isActive());
      //  assertThat(account.isActive(), equalTo(true));
       // assertThat(account.isActive(), is(true));
    }
    @Test
    void checkIfAddressIsNotSetRightAfterAccountCreation(){
        //given
        Account account = new Account(true);
        //when
        Address address  =account.getDefaultDeliveryAddress();
        //then
        assertNull(address);

    }
    @Test
    void checkIfAddressIsNotNullAfterSettingUp(){
        //given
        Address address = new Address("Wodzislawsk", "67c");
        Account account1 = new Account(address);
        //when
        Address address1 = account1.getDefaultDeliveryAddress();
        //then
        assertNotNull(address1);
       // assertThat(address1, is(notNullValue()));
    }
   // @RepeatedTest(5) podajemy ile razy tets ma byc powtorzony
    @Test
    void newAccountWithNotNullAddressShouldBeActive(){

        Address address = new Address("Wodzislawksa" ,"79/15");

        Account account = new Account(address);

        assumingThat(address != null, () -> assertTrue(account.isActive())); // w pierwszym argumencie podajemy warunek
        // ktory musi byc spelniony
    }
}