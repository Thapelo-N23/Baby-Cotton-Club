package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineFactoryTest {



    @BeforeAll
    public static void setup() {

    }

    @Test
    public void testCreateOrderLineWithMinimalAttributes() {
        OrderLine orderLine = OrderLineFactory.createOrderLine(123, 123.0);

        assertNotNull(orderLine);
        assertEquals(123, orderLine.getQuantity());
        assertEquals(123.0, orderLine.getUnitPrice());
        assertEquals(123 * 123.0, orderLine.getSubTotal());

        assertNull(orderLine.getProduct());
        assertNull(orderLine.getOrder());

        System.out.println(orderLine);
    }
    @Test
    public void testCreateOrderLineWithAllAttributes() {

        Customer customer = new Customer();
        List<OrderLine> orderLines = new ArrayList<>();
        Shipment shipment = ShipmentFactory.createShipment("DHL", "OUT OF STOCK", 23,null);
        CustomerOrder customerOrder = CustomerOrderFactory.createCustomerOrder("20250729", 200.0, orderLines, customer, shipment);
         Supplier supplier = SupplierFactory.createSupplier(
                "SnuggleBabies Clothing Co.",
                "0211234567",
                null);


        Product product = ProductFactory.createProduct("Lancewood", "Yellow", (short) 588, "OUT OF STOCK", null,supplier);

        OrderLine orderLine = OrderLineFactory.createOrderLine(
                5,
                200.0,
                customerOrder,
                product

        );

        assertNotNull(orderLine);
        assertEquals(5, orderLine.getQuantity());
        assertEquals(200.0, orderLine.getUnitPrice());
        assertEquals(1000.0, orderLine.getSubTotal());
        assertEquals(product, orderLine.getProduct());

        assertEquals(customerOrder, orderLine.getOrder());

        System.out.println(orderLine);
    }


    @Test
    public void testCreateOrderLineWithNullDiscount() {
        OrderLine orderLine = OrderLineFactory.createOrderLine(2, 50.00);

        assertNotNull(orderLine);
        assertEquals(2, orderLine.getQuantity());
        assertEquals(50.00, orderLine.getUnitPrice());
        assertEquals(100.00, orderLine.getSubTotal());


        System.out.println(orderLine);
    }

    // New test to ensure size is stored when using the size-aware factory overload
    @Test
    public void testCreateOrderLineWithSize() {
        String size = "3-6M";
        OrderLine orderLine = OrderLineFactory.createOrderLine(2, 100.0, size);

        assertNotNull(orderLine);
        assertEquals(2, orderLine.getQuantity());
        assertEquals(100.0, orderLine.getUnitPrice());
        assertEquals(200.0, orderLine.getSubTotal());
        assertEquals(size, orderLine.getSize());

        System.out.println(orderLine);
    }
}
