package testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import testing.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(35);
        //when
        int afterDiscount = meal.getDiscountedPrice(10);
        //then
        //assertEquals(25, afterDiscount);
        assertThat(afterDiscount, equalTo(25)); //
    }

    @Test
    void shouldReturnEqualObjects() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;
        //then
        assertSame(meal1, meal2);
        assertThat(meal1, sameInstance(meal2));
        //assertThat(meal1, sameInstance(meal2)); //this is Hamcrest
    }

    @Test
    void twoMealsShouldNotBeEqualWhenPriceAndNameAreNotTheSame() {
        Meal meal1 = new Meal(10, "PIZZA");
        Meal meal2 = new Meal(10, "PZZA");

        //assertThat(meal1, not(sameInstance(meal2)));
    }

    @Test
    void exceptionShouldBeThrownIfDiscountHigherThanPrice() {
        //given

        Meal meal = new Meal(50, "PIZZA");
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(60));

    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 20})
    void mealPricesShouldBeLowerThan60(int price) {

        assertThat(price, lessThan(60));
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollection() {

        return Arrays.asList(dynamicTest("Dynamic test1", () ->
                        assertThat(5, lessThan(6))),
                dynamicTest("Dynamic test2", () -> assertEquals(4, 2 * 2)));
    }

    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {
        Order order = new Order();

        order.addMealToOrder(new Meal(22, "Pizza", 7));
        order.addMealToOrder(new Meal(10, "Fries", 7));
        order.addMealToOrder(new Meal(19, "bURGER", 7));
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> assertThat(calculatePrice(price, quantity), lessThan(1000));

            String name = "Test name" + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);


        }
        return dynamicTests;
    }

    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }
}