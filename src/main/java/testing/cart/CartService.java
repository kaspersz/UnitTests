package testing.cart;

import testing.order.OrderStatus;

public class CartService {
    private CartHandler cartHandler;

    public CartService(CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }
    Cart processCart(Cart cart){
        if (cartHandler.cartHandleCart(cart)){
            cartHandler.sendToPrepare(cart);

            cart.getOrders().forEach(p-> p.changeOrderStatus(OrderStatus.PREPARING));
            return cart;
        }
        else {
            cart.getOrders().forEach(p-> p.changeOrderStatus(OrderStatus.REJECTED));
            return cart;
        }
    }
}
