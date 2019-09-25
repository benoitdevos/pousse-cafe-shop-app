package poussecafe.shop.adapters.messaging;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.domain.OrderDescription;
import poussecafe.shop.domain.OrderRejected;
import poussecafe.shop.domain.ProductId;

@MessageImplementation(message = OrderRejected.class)
@SuppressWarnings("serial")
public class SerializableOrderRejected implements Serializable, OrderRejected {

    @Override
    public Attribute<ProductId> productId() {
        return AttributeBuilder.single(ProductId.class)
                .storedAs(String.class)
                .adaptOnRead(ProductId::new)
                .read(() -> productId)
                .adaptOnWrite(ProductId::stringValue)
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
