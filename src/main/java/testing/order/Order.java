package testing.order;

import testing.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Meal> meals = new ArrayList<>();
    private OrderStatus orderStatus;

    public void addMealToOrder (Meal meal){
        meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal){
        this.meals.remove(meal);
    }
    public List<Meal> getMeals() {
        return meals;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
