package callCenterOrderSystem;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by thomas on 1/15/17.
 *
 * Creates new files containing randomly
 * generated orders
 */
public class CallCenterOrderSystem {

    static String DIRECTORY = "src/data";
    static String BASE_NAME = "orders_";
    static int TWO_MINUTES = (120 * 100);

    public static void main(String[] args) {

        while(true) {
            try{
                // gather all filenames
                ArrayList<String> results = new ArrayList<String>();

                File[] files = new File(DIRECTORY).listFiles();

                int maxFileNum = 0;

                for (File file : files) {
                    if (file.isFile()) {
                        int num = Integer.parseInt(file.getName().split("_")[1].split("\\.")[0]);
                        if(num > maxFileNum){
                            maxFileNum = num;
                        }
                    }
                }

                String fileName = BASE_NAME + (maxFileNum + 1) + ".csv";
                PrintWriter writer = new PrintWriter(DIRECTORY + "/" + fileName, "UTF-8");
                String[] orderStrings = generateRandomOrderString();
                for(String orderString : orderStrings){
                    writer.write(orderString + "\n");
                }
                writer.close();
                // sleep for two minutes
                Thread.sleep(TWO_MINUTES);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static String[] generateRandomOrderString() {
        Random rand = new Random();
        int orders = rand.nextInt(9) + 1; // up to ten orders per file

        String[] names = {"Paul", "Mike", "Andrew", "John", "Simon", "Philip", "Anderson" ,"Chris", "Matt", "Tom"};

        String[] myStringArray = new String[orders];

        for(int i = 0; i < orders; i++) {
            int customerId = rand.nextInt(1000000); // customer id is some random 7 digit integer
            String firstName = names[rand.nextInt(names.length)];
            String lastName = names[rand.nextInt(names.length)];
            int surfs = rand.nextInt(10); // up to 10 surf boards per order
            int dives = rand.nextInt(10); // up to 10 diving suits per order
            int totalItems = surfs + dives;
            int orderId = rand.nextInt(1000); // order id is a random 4 digit number
            myStringArray[i] = customerId + "," + firstName + " " + lastName + "," + totalItems + "," + dives + "," + surfs + "," + orderId;
        }
        return myStringArray;
    }
}
