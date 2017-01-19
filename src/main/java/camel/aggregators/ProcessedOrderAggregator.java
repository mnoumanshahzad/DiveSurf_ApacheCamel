package camel.aggregators;

import bean.Order;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * Created by nouman on 1/19/17.
 */
public class ProcessedOrderAggregator implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange exchange, Exchange exchange1) {

        if (exchange == null) {
            return exchange1;
        }
        Order order1 = (Order)exchange.getIn().getBody();
        Order order2 = (Order)exchange1.getIn().getBody();

        if (order1.isValid() && order2.isValid()) {
            return exchange;
        }

        else {
            order1.setValidationResult(order1.getValidationResult() + " -- " + order2.getValidationResult());
            exchange.getIn().setBody(order1);
            return exchange;
        }
    }
}
