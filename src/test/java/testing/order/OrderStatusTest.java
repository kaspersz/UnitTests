package testing.order;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import testing.order.OrderStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OrderStatusTest {

    @ParameterizedTest
    @EnumSource(OrderStatus.class)
    void allOrderStatusShouldBeShorterThan15Characters(OrderStatus orderStatus){
        assertThat(orderStatus.toString().length(), lessThan(50));
    }
    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }
    private static Stream<Arguments>  createMealsWithNameAndPrice(){
        return Stream.of(Arguments.of("Hamburger", 10), Arguments.of("Cheeseburger", 15));
    }
    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNameShouldEndWithName(String name){
        assertThat(name, endsWith("cake"));
    }
    private static Stream<String> createCakeNames(){
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fuitcake");
        return cakeNames.stream();
    }

}