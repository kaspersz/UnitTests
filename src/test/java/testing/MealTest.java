package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(35);
        //when
        int afterDiscount = meal.getDiscountedPrice(10);
        //then
        assertEquals(25, afterDiscount);
    }
    @Test
    void shouldReturnEqualObjects(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;
        //then
        assertSame(meal1, meal2);
    }
    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){
        Meal meal1 = new Meal(10,"PIZZA");
        Meal meal2 = new Meal(10,"PIZZA");

        assertEquals(meal1, meal2);
    }
}