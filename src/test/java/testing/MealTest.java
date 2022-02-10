package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
    void shouldReturnEqualObjects(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;
        //then
        assertSame(meal1, meal2);
        assertThat(meal1,sameInstance(meal2));
        //assertThat(meal1, sameInstance(meal2)); //this is Hamcrest
    }
    @Test
    void twoMealsShouldNotBeEqualWhenPriceAndNameAreNotTheSame(){
        Meal meal1 = new Meal(10,"PIZZA");
        Meal meal2 = new Meal(10,"PZZA");

       //assertThat(meal1, not(sameInstance(meal2)));
    }
    @Test
    void exceptionShouldBeThrownIfDiscountHigherThanPrice(){
        //given

        Meal meal = new Meal(50, "PIZZA");
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(60));

    }

    @ParameterizedTest
    @ValueSource(ints = {5,10,15,20})
    void mealPricesShouldBeLowerThan60(int price){

        assertThat(price, lessThan(60));
    }
}