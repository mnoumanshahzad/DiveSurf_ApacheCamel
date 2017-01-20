package camel.messageTranslators;

import bean.Order;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Call Center Message Translator
 *
 * Implements the Processor interface
 *
 * Reads data from the CC_NEW_ORDER queue and
 * extract order information to create new order
 * messages.
 */
public class CallCenterMessageTranslator implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Order order = new Order();
        String[] orderTokens = exchange.getIn().getBody(String.class).split(",");

        order.setCutomerId(Integer.parseInt(orderTokens[0]));
        order.setFirstName(orderTokens[1].split(" ")[0]);
        order.setLastName(orderTokens[1].split(" ")[1]);
        order.setOverallItems(Integer.parseInt(orderTokens[2]));
        order.setNumberOfDivingSuits(Integer.parseInt(orderTokens[3]));
        order.setNumberOfSurfBoards(Integer.parseInt(orderTokens[4]));
        order.setOrderId(Integer.parseInt(orderTokens[5]));

        exchange.getIn().setBody(order);

    }
}
