package camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        //Main main = new Main();

        DefaultCamelContext camelContext = new DefaultCamelContext();
        //CamelContext camelContext = main.getOrCreateCamelContext();
        ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent();
        activeMQComponent.setTrustAllPackages(true);
        camelContext.addComponent("activemq", activeMQComponent);

        //Thread.sleep(10000);

        camelContext.addRoutes(new MyRouteBuilder());

        camelContext.start();
        System.in.read();
        camelContext.stop();

        //main.addRouteBuilder(new MyRouteBuilder());
        //main.run(args);
    }

}

