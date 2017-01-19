package billingSystem;

import bean.Order;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.MessageId;

import javax.jms.*;
import java.io.IOException;
import java.util.Random;

/**
 * Created by nouman on 1/19/17.
 */
public class BillingSystem {

    public static void main(String[] args) {

        Random random = new Random();

        try{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();

            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic newOrder = session.createTopic("NEW_ORDER_TOPIC");
            MessageConsumer billingConsumer = session.createConsumer(newOrder);

            Queue processedOrder = session.createQueue("PROCESSED_ORDER");
            MessageProducer billingProducer = session.createProducer(processedOrder);

            billingConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    Order order = null;
                    try {
                        order = (Order)((ActiveMQObjectMessage)message).getObject();

                        if (random.nextInt(2) == 1) {
                            order.setValid(true);
                        }
                        else {
                            order.setValid(false);
                            order.setValidationResult("Customer account balance is low");
                        }

                        ActiveMQObjectMessage activeMQObjectMessage = new ActiveMQObjectMessage();
                        activeMQObjectMessage.setObject(order);
                        activeMQObjectMessage.setProperty("order_id", order.getOrderId());
                        billingProducer.send(activeMQObjectMessage);

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
