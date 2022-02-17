package practice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {
    @ParameterizedTest
    @CsvSource({"101, 500", "-101, -200"})
        void shouldReturnExceptionsWhenParametersAreNotCorrect(int x, int y){
            assertThrows(IllegalArgumentException.class, () -> new Coordinates(x, y));
        }

        @Test
        void returnCorrectCoordinateIfGivenXandYAreNotOutOfRange(){
            //given
            Coordinates coordinates = new Coordinates(50,50);
            //when
            Coordinates copiedCoordinates = Coordinates.copy(coordinates, 20, 20);
            //then
            assertThat(copiedCoordinates, equalTo(new Coordinates(70,70)));
        }

        @Test
        void returnExceptionIfAfterAddingXandYTheCoordinateAreOutOfRange(){
            //given
            Coordinates coordinates = new Coordinates(50,50);
            //when
            //then

            assertThrows(IllegalArgumentException.class, () -> Coordinates.copy(coordinates, 80, 90));
        }


}