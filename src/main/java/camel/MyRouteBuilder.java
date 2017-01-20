package camel;

import camel.aggregators.ProcessedOrderAggregator;
import camel.messageTranslators.CallCenterMessageTranslator;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        //Call Center Orders routed from file to the queue
        from("file:src/data?noop=true")
                .split(body().tokenize("\n"))
                .to("activemq:queue:CC_NEW_ORDER");

        //Messages translated from CC_NEW_ORDER queue
        //and passed to NEW_ORDER queue
        from("activemq:CC_NEW_ORDER")
                .process(new CallCenterMessageTranslator())
                .to("activemq:NEW_ORDER");

        //New Orders passed to pub-sub channel
        from("activemq:NEW_ORDER")
                .to("activemq:topic:NEW_ORDER_TOPIC");

        //Orders passed down an aggregator
        //Result of aggregator then split into
        //two new queues
        from("activemq:PROCESSED_ORDER")
                .aggregate(header("order_id"),new ProcessedOrderAggregator())
                .completionTimeout(500)
                .choice()
                    .when(header("valid"))
                    .to("activemq:VALID")
                .otherwise()
                .to("activemq:INVALID");
    }
}
