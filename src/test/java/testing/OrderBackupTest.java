package testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OrderBackupTest {

    private static OrderBackup orderBackup;

    @BeforeAll
    static void setup() throws IOException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @AfterAll
    static void TearDown() throws IOException {
        orderBackup.closeFile();
    }
    @Test
    void checkIfFileIsWritten () throws IOException {
        //given
        Meal meal = new Meal(10, "FRIES");
        Order order = new Order();
        order.addMealToOrder(meal);
        //when
        orderBackup.backUpOrder(order);
        //then
        System.out.println("Order" + order.toString() + " backed up");

    }
}