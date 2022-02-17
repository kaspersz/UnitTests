package testing.cart;


import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testing.cart.Cart;
import testing.order.Order;

import java.time.Duration;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;
@DisplayName("Test for Cart class")
class CartTest {
    //@Disabled ignoruje test, mozna tez nad klasa
    @DisplayName("Card is able to process 1000 order ")
    @Test
    void simulateLargeOrder() {
        //given
        Cart cart = new Cart();
        //when
        //then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }

@Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        //when
        cart.addOrderToCart(order);
        //then
        assertThat(cart.getOrders(), anyOf(nullValue(), hasSize(1), is(empty()))); //jakikolwiek warunek musi byc spelnion

        assertThat(cart.getOrders(), allOf(notNullValue()));//wszystkie warunki musza byc spelnione
        assertAll(()-> assertThat(cart.getOrders(), is(notNullValue())),
                () -> assertThat(cart.getOrders(), hasSize(1))//tutaj mozemy rozbudowac lamde
                );//tutaj mozemy mieszac mozliwosci
    }
}