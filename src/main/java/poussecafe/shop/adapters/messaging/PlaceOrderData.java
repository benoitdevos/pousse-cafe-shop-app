package poussecafe.shop.adapters.messaging;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.command.PlaceOrder;
import poussecafe.shop.domain.OrderDescription;
import poussecafe.shop.domain.ProductId;

@MessageImplementation(message = PlaceOrder.class)
@SuppressWarnings("serial")
public class PlaceOrderData implements Serializable, PlaceOrder {

    @Override
    public Attribute<ProductId> productId() {
        return AttributeBuilder.stringId(ProductId.class)
                .read(() -> productId)
                .write(value -> productId = value)
                .build();
    }

    private String productId;

    @Override
    public Attribute<OrderDescription> description() {
        return AttributeBuilder.single(OrderDescription.class)
                .usingAutoAdapter(OrderDescriptionData.class)
                .read(() -> description)
                .write(value -> description = value)
                .build();
    }

    private OrderDescriptionData description;
}
