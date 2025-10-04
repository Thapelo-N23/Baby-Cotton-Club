//package za.ac.cput.controller;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//import za.ac.cput.domain.*;
//import za.ac.cput.factory.*;
//import za.ac.cput.repository.*;
//
//import java.util.List;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class OrderLineControllerTest {
//
//    private OrderLine orderLine;
//    private Product product;
//    private CustomerOrder customerOrder;
//    private Customer customer;
//    private Shipment shipment;
//    private Supplier supplier;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CustomerOrderRepository customerOrderRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private ShipmentRepository shipmentRepository;
//
//    @Autowired
//    private SupplierRepository supplierRepository;
//
//    private String getBaseUrl() {
//        return "http://localhost:" + port + "/api/orderline";
//    }
//
//    @BeforeAll
//    void setUp() {
//        // 1. Persist Supplier
//        supplier = SupplierFactory.createSupplier("SnuggleBabies Clothing Co.", "0211234567", null);
//        supplier = supplierRepository.save(supplier);
//
//        // 2. Persist Product with persisted Supplier
//        product = ProductFactory.createProduct("Lancewood", "Yellow", (short) 588, "OUT OF STOCK", null, supplier);
//        product = productRepository.save(product);
//
//        // 3. Persist Customer
//        customer = new Customer();
//        customer = customerRepository.save(customer);
//
//        // 4. Persist Shipment
//        shipment = ShipmentFactory.createShipment("DHL", "OUT OF STOCK", 23, null);
//        shipment = shipmentRepository.save(shipment);
//
//        // 5. Persist CustomerOrder with persisted Customer and Shipment
//        customerOrder = CustomerOrderFactory.createCustomerOrder("20250729", 200.0, List.of(), customer, shipment);
//        customerOrder = customerOrderRepository.save(customerOrder);
//
//        // 6. Create OrderLine linking persisted entities
//        orderLine = OrderLineFactory.createOrderLine(5, 200.0, customerOrder, product);
//
//        // 7. POST OrderLine to controller
//        ResponseEntity<OrderLine> response = restTemplate.postForEntity(getBaseUrl() + "/create", orderLine, OrderLine.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create OrderLine");
//        orderLine = response.getBody();
//        assertNotNull(orderLine, "OrderLine response body is null");
//        assertNotNull(orderLine.getOrderLineId(), "OrderLine ID not generated");
//
//        System.out.println("Created OrderLine (setup): " + orderLine);
//    }
//
//    @Test
//    void a_create() {
//        assertNotNull(orderLine);
//        System.out.println("Created OrderLine: " + orderLine);
//    }
//
//    @Test
//    void b_read() {
//        ResponseEntity<OrderLine> response = restTemplate.getForEntity(
//                getBaseUrl() + "/read/" + orderLine.getOrderLineId(),
//                OrderLine.class
//        );
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(orderLine.getOrderLineId(), response.getBody().getOrderLineId());
//        assertNotNull(response.getBody().getOrder(), "Order should not be null");
//        assertNotNull(response.getBody().getProduct(), "Product should not be null");
//
//        System.out.println("Read OrderLine with relationships: " + response.getBody());
//    }
//
//    @Test
//    void c_update() {
//        OrderLine updatedOrderLine = new OrderLine.Builder()
//                .copy(orderLine)
//                .setQuantity(10)
//                .setUnitPrice(150.0)
//                .build();
//
//        HttpEntity<OrderLine> request = new HttpEntity<>(updatedOrderLine);
//
//        ResponseEntity<OrderLine> response = restTemplate.exchange(
//                getBaseUrl() + "/update",
//                HttpMethod.PUT,
//                request,
//                OrderLine.class
//        );
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(10, response.getBody().getQuantity());
//        assertEquals(150.0, response.getBody().getUnitPrice());
//
//        orderLine = response.getBody();
//        System.out.println("Updated OrderLine: " + orderLine);
//    }
//
//    @Test
//    void d_getAll() {
//        ResponseEntity<OrderLine[]> response = restTemplate.getForEntity(getBaseUrl() + "/getall", OrderLine[].class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertTrue(response.getBody().length > 0, "No OrderLines found");
//
//        System.out.println("All OrderLines: " + Arrays.toString(response.getBody()));
//    }
//}
