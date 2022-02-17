package testing.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testing.Meal;
import testing.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;
    @BeforeEach
    void initializeOrder(){
        System.out.println(order);
        order = new Order();
    }
    @AfterEach
    void cancelCretingOrder(){
        order = null;
        System.out.println("I am setting order to null");
    }

    @Test
    void testAssertArraysEquals(){
        int[] ints1 = {1,2,3};
        int[] ints2 = {1,2,3};
        assertArrayEquals(ints1, ints2);
    }
    @Test
    void mealListShouldBeEmptyAfterCreationTheOrder(){
        //given

      //  Order order = new Order();
        // then
        assertThat(order.getMeals()).isEmpty();

    }

    @Test
    void addingMealToOrderShouldIncreaseOrderListSize(){
        //given
        Meal meal = new Meal(20, "PIZZA");
        //eal meal2 = new Meal(20, "Burger");
       // Order order = new Order();
        //when
        order.addMealToOrder(meal);
        //then
        assertThat(order.getMeals().size()).isGreaterThan(0);
        assertThat(order.getMeals()).contains(meal);
    }
    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){
        //given
        Meal meal = new Meal(20, "PIZZA");
      //  Order order = new Order();
        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals()).hasSize(0);
        assertThat(order.getMeals()).doesNotContain(meal);

    }
    @Test
    void mealsShouldBeInTheSameOrderAfterAdding(){

        //given
        Meal meal = new Meal(20, "PIZZA");
        Meal meal2 = new Meal(20, "Burger");
       // Order order = new Order();
        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);
        //then
        assertThat(order.getMeals()).containsExactly(meal, meal2);

    }
    @Test
    void ifTwoListsAreTheSame(){
        Meal meal = new Meal(20, "PIZZA");
        Meal meal2 = new Meal(20, "Burger");
        Meal meal3 = new Meal(230, "VeganMeal");
        List list1 = new ArrayList<>(Arrays.asList(meal, meal2));
        List list2 = new ArrayList(Arrays.asList(meal, meal2));
        assertThat(list1).isEqualTo(list2);


    }
}