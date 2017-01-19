package camel;

import bean.Order;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by nouman on 1/19/17.
 */
public class Experiment {
    public static void main(String[] args) {

        try{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();

            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue inQueue = session.createQueue("final");

            MessageConsumer messageConsumer = session.createConsumer(inQueue);

            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    //System.out.println(message);
                    Order order = null;
                    try {
                        System.out.println(message.getClass().getName());
                        order = (Order)((ActiveMQObjectMessage)message).getObject();
                    } catch (JMSException e) {
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
