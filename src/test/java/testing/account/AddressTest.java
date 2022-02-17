package testing.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @ParameterizedTest
    @CsvSource({"Wodzislawska, 10", "ArmiiKrakjowej, 57/11", "ArmiiKrakjowej, 57/11"})
     void givenAddressesShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertThat(street.length(), lessThan(30));
        assertThat(number, notNullValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources= "/addreses.csv")
    void addressesFromFileShouldNotBeEmpty(String street, String number){
        assertThat(street.length(), lessThan(30));
        assertThat(number, notNullValue());
    }
}