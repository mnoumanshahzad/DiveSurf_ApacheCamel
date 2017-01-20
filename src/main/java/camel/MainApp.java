package camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * Apache Camel main
     */
    public static void main(String... args) throws Exception {

        DefaultCamelContext camelContext = new DefaultCamelContext();

        //Adding the ActiveMQ component to the current Camel Context
        ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent();
        activeMQComponent.setTrustAllPackages(true);
        camelContext.addComponent("activemq", activeMQComponent);

        //Adding the routes to the current Camel Context
        camelContext.addRoutes(new MyRouteBuilder());

        //Starting and stopping current Camel Context
        camelContext.start();
        System.in.read();
        camelContext.stop();
    }

}

