package inventorySystem;

import bean.Order;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.MessageId;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by nouman on 1/19/17.
 */
public class InventorySystem {

    int numberOfDivingSuits = 50;
    int numberOfSurfBoards = 50;

    void decrementDivingSuits(int numberOfDivingSuits) {
        this.numberOfDivingSuits -= numberOfDivingSuits;
    }

    void decrementSurfBoards(int numberOfSurfBoards) {
        this.numberOfSurfBoards -= numberOfSurfBoards;
    }

    boolean areDivingSuitsAvailable(int numberOfDivingSuits) {
        if (numberOfDivingSuits > this.numberOfDivingSuits) {
            return false;
        }
        return true;
    }

    boolean areSurfBoardsAvailable(int numberOfSurfBoards) {
        if (numberOfSurfBoards > this.numberOfSurfBoards) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        InventorySystem inventory = new InventorySystem();

        try{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();

            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic newOrder = session.createTopic("NEW_ORDER_TOPIC");
            MessageConsumer inventoryConsumer = session.createConsumer(newOrder);

            Queue processedOrder = session.createQueue("PROCESSED_ORDER");
            MessageProducer inventoryProducer = session.createProducer(processedOrder);

            inventoryConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    Order order = null;
                    try {
                        order = (Order)((ActiveMQObjectMessage)message).getObject();

                        int numberOfDivingSuitsOrdered = order.getNumberOfDivingSuits();
                        int numberOfSurfBoardsOrdered = order.getNumberOfSurfBoards();

                        if (inventory.areDivingSuitsAvailable(numberOfDivingSuitsOrdered) &&
                                inventory.areSurfBoardsAvailable(numberOfSurfBoardsOrdered)) {
                            inventory.decrementDivingSuits(numberOfDivingSuitsOrdered);
                            inventory.decrementSurfBoards(numberOfSurfBoardsOrdered);
                            order.setValid(true);
                        }

                        else {
                            order.setValid(false);
                            order.setValidationResult("Not enough stock available");
                        }

                        ActiveMQObjectMessage activeMQObjectMessage = new ActiveMQObjectMessage();
                        activeMQObjectMessage.setObject(order);
                        activeMQObjectMessage.setProperty("order_id", order.getOrderId());
                        inventoryProducer.send(activeMQObjectMessage);

                    } catch (JMSException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(order.toString());
                }
            });

            connection.start();
            System.in.read();
            connection.stop();

        }
        catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }
}
