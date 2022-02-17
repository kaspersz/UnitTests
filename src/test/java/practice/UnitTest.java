package practice;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class UnitTest {
    private static Random random = new Random();

    @Test
    void shouldReturnIllegalStateExceptionIfFuelMinusSumofCorrdinatesLowerThanZero() {
        //given
        Unit unit = new Unit(new Coordinates(10, 10), 10, 10);
        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.move(10, 10));
    }

    @Test
    void shouldReturnCorrectCoordinateWhenSumOfCoordinatesIsHigherThanZero() {
        Unit unit = new Unit(new Coordinates(10, 10), 10, 10);
        // when
        Coordinates coordinates = unit.move(3, 3);
        //then
        assertThat(coordinates, equalTo(new Coordinates(13, 13)));
    }

    @Test
    void shouldAssignFuelAsMaxFuel(){
        //given
        Unit unit = new Unit(new Coordinates(10, 10), 20, 10);
        //when
        unit.tankUp();
        //then
        assertThat(unit.getFuel(), equalTo(unit.getMaxFuel()));
    }
    @Test
    void shouldReturnExceptionWhileCargoWeightisHigherThanMaxCargoWeight(){
        //given
        Unit unit = new Unit(new Coordinates(10, 10), 20, 10);
        Cargo cargo = new Cargo("Plane", 100);
        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(cargo));

    }
    @Test
    void shouldAddCargoToTheListIfWeightIsLessThanMaximum(){
        //given
        Unit unit = new Unit(new Coordinates(10, 10), 20, 100);
        Cargo cargo = new Cargo("Plane", 50);
        //when
        unit.loadCargo(cargo);
        //then
        assertThat(unit.getCargo(), hasSize(1));
    }

}