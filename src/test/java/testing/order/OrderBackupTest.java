package testing.order;

import org.junit.jupiter.api.*;
import testing.Meal;
import testing.order.Order;
import testing.order.OrderBackup;

import java.io.IOException;

class OrderBackupTest {

    private static OrderBackup orderBackup;

    @BeforeAll
    static void setup() throws IOException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }
    @BeforeEach
    void addStringToOrder() throws IOException {
        orderBackup.getFileWriter().append("New Order");
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
    @AfterEach
    void addStringToTheEndOfOrder() throws IOException {
        orderBackup.getFileWriter().append("added");
    }

    @AfterAll
    static void TearDown() throws IOException {
        orderBackup.closeFile();
    }
}