package webOrderSystem;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by nouman on 1/15/17.
 */
public class WebOrderSystem {

    public static void main(String[] args) {

        try{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();

            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue inQueue = session.createQueue("WEB_NEW_ORDER");

            MessageConsumer messageConsumer = session.createConsumer(inQueue);

            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    System.out.println(message.toString());
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
