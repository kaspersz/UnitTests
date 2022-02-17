package testing.cart;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import testing.order.Order;
import testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Test
    void processCartShouldSendToPrepare(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        given(cartHandler.cartHandleCart(cart)).willReturn(true);
        //given(cartHandler.cartHandleCart(cart)).willReturn(true, false, false );  -- MOZEMY DAC KILKA WARTOSCI,
        //BEDA SIE ONE WYKONYWAC PO KOLEI

        //when
        Cart resultCart =  cartService.processCart(cart);
        //then
        //resultCart.getOrders().forEach(p-> assertThat(p.getOrderStatus(), equalTo(OrderStatus.PREPARING)));
        verify(cartHandler).sendToPrepare(cart) ;
        verify(cartHandler, times(1)).sendToPrepare(cart);
        verify(cartHandler, atLeastOnce()).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart); //analogiczny zapis do powyzszego
        InOrder inOrder = inOrder(cartHandler);//sprawdzamy czy w dobrym orderze metody sie procesuja wewnatrz metody
        inOrder.verify(cartHandler).cartHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

    }

    @Test
    void shouldSetOrderToRejected(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        // when(cartHandler.cartHandleCart(cart)).thenReturn(false);
        when(cartHandler.cartHandleCart(any(Cart.class))).thenReturn(false); // ANY EXAMPLE
        //when(cartHandler.cartHandleCart(any(Cart.class), "TEST" )).thenReturn(false);  NIE MOZNA MIESZAC ANY Z NORMALNYMI OBIEKTAMI
        //when
        Cart resultCart =  cartService.processCart(cart);
        //then
        //resultCart.getOrders().forEach(p-> assertThat(p.getOrderStatus(), equalTo(OrderStatus.REJECTED)));
        verify(cartHandler, never()).sendToPrepare(cart) ;
        then(cartHandler).should(never()).sendToPrepare(cart);
    }

    @Test
    void shouldReturnMultipleValues(){

            //given
            Order order = new Order();
            Cart cart = new Cart();
            cart.addOrderToCart(order);
            CartHandler cartHandler = mock(CartHandler.class);
            CartService cartService = new CartService(cartHandler);
            given(cartHandler.cartHandleCart(cart)).willReturn(true, false, false, true);
            // MOZEMY DAC KILKA WARTOSCI,
            //BEDA SIE ONE WYKONYWAC PO KOLEI
            assertThat(cartHandler.cartHandleCart(cart), equalTo(true));
            assertThat(cartHandler.cartHandleCart(cart), equalTo(false));
            assertThat(cartHandler.cartHandleCart(cart), equalTo(false));
            assertThat(cartHandler.cartHandleCart(cart), equalTo(true));
    }

    @Test
    void processCartShouldSendToPrepareWithLamdas(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        given(cartHandler.cartHandleCart(argThat(c -> c.getOrders().size() > 0))).willReturn(true);
        // argThat metoda intefejsu funkcyjnegi

        //when
        Cart resultCart =  cartService.processCart(cart);
        //then
        //resultCart.getOrders().forEach(p-> assertThat(p.getOrderStatus(), equalTo(OrderStatus.PREPARING)));
        verify(cartHandler).sendToPrepare(cart) ;
    }

    @Test
    void canHandleCartWillThrowException(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        given(cartHandler.cartHandleCart(cart)).willThrow(IllegalArgumentException.class);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () ->cartService.processCart(cart));

    }

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        ArgumentCaptor<Cart> argumentCaptor =  ArgumentCaptor.forClass(Cart.class);

        given(cartHandler.cartHandleCart(cart)).willReturn(true);
        //given(cartHandler.cartHandleCart(cart)).willReturn(true, false, false );  -- MOZEMY DAC KILKA WARTOSCI,
        //BEDA SIE ONE WYKONYWAC PO KOLEI

        //when
        Cart resultCart =  cartService.processCart(cart);
        //then
        //resultCart.getOrders().forEach(p-> assertThat(p.getOrderStatus(), equalTo(OrderStatus.PREPARING)));

        then(cartHandler).should().sendToPrepare(argumentCaptor.capture()); //analogiczny zapis do powyzszego
        assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));

    }
    @Test
    void shouldDONothingWhenProcessCart(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        given(cartHandler.cartHandleCart(cart)).willReturn(true);
        doNothing().when(cartHandler).sendToPrepare(cart);

        willDoNothing().willThrow(IllegalStateException.class).given(cartHandler).sendToPrepare(cart);
        //mozemy te willDo etc. dodawac jesli mamy wiecej metod
        //when
        Cart resultCart =  cartService.processCart(cart);
        //then
        //resultCart.getOrders().forEach(p-> assertThat(p.getOrderStatus(), equalTo(OrderStatus.PREPARING)));
        verify(cartHandler).sendToPrepare(cart) ;


    }

    @Test
    void shouldAnswerWhenProcessCart(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        doAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            return true;

        }).when(cartHandler).cartHandleCart(cart);
        when(cartHandler.cartHandleCart(cart)).then(i-> {
            Cart argumentCart = i.getArgument(0);
            argumentCart.clearCart();
            return true;
        });
        //when
        Cart resultCart =  cartService.processCart(cart);
        //then
        assertThat(resultCart.getOrders().size(), equalTo(0));


    }
}