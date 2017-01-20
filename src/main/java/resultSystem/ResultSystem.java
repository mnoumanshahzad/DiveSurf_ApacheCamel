package resultSystem;

import bean.Order;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;
import java.io.IOException;

/**
 * Result System
 *
 * Prints result from the valid orders and invalid order queues
 */
public class ResultSystem {

    public static void main(String[] args) {

        ResultSystem resultSystem = new ResultSystem();

        try{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();

            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue validQueue = session.createQueue("VALID");
            Queue invalidQueue = session.createQueue("INVALID");

            MessageConsumer validOrderConsumer = session.createConsumer(validQueue);
            MessageConsumer invalidOrderConsumer = session.createConsumer(invalidQueue);

            validOrderConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    Order order = null;
                    try {
                        order = (Order)((ActiveMQObjectMessage)message).getObject();
                        System.out.println("VALID -- " + order.toString());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            invalidOrderConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    Order order = null;
                    try {
                        order = (Order)((ActiveMQObjectMessage)message).getObject();
                        System.out.println("INVALID -- " + order.toString());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
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
