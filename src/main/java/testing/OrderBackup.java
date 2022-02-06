package testing;

import java.io.*;

public class OrderBackup {

    private FileWriter fileWriter;

    public OrderBackup() {
    }

    void createFile() throws IOException {
        File file = new File("order.txt");
        this.fileWriter = new FileWriter(file);

        /*FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        writer = new BufferedWriter(outputStreamWriter);*/

    }
    void backUpOrder(Order order) throws IOException {
        fileWriter.write(order.toString());
    }
    void closeFile() throws IOException {
        fileWriter.close();
    }
}
