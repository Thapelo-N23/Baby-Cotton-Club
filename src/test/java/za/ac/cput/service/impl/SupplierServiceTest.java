//package za.ac.cput.service.impl;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.ac.cput.domain.*;
//import za.ac.cput.factory.*;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class SupplierServiceTest {
//
//    @Autowired
//    private SupplierService service;
//
//    private static Supplier supplier;
//    private static Inventory inventory;
//
//    @Test
//    @Order(1)
//    void create() {
//
//        Product product = ProductFactory.createProduct(
//                "Baby Blanket", "Pink", 199,"true", null
//        );
//
//        inventory = InventoryFactory.createInventory(
//                "2025-08-03",
//                "100 units",
//                Collections.emptyList(),
//                product
//        );
//
//        assertNotNull(inventory);
//
//        supplier = SupplierFactory.createSupplier(
//                "SnuggleBabies Clothing Co.",
//                "0211234567",
//                inventory
//        );
//
//        assertNotNull(supplier);
//
//        Supplier created = service.create(supplier);
//        assertNotNull(created);
//        supplier = created;
//        System.out.println("Created Supplier: " + supplier);
//    }
//
//    @Test
//    @Order(2)
//    void read() {
//        Supplier read = service.read(String.valueOf(supplier.getSupplierId()));
//        assertNotNull(read);
//        System.out.println("Read Supplier: " + read);
//    }
//
//    @Test
//    @Order(3)
//    void update() {
//        Supplier updated = new Supplier.Builder()
//                .copy(supplier)
//                .setContactDetails("0217654321")
//                .build();
//
//        Supplier result = service.update(updated);
//        assertNotNull(result);
//        supplier = result;
//        System.out.println("Updated Supplier: " + result);
//    }
//
//    @Test
//    @Order(4)
//    void getAll() {
//        List<Supplier> all = service.getAll();
//        assertNotNull(all);
//        assertFalse(all.isEmpty());
//        System.out.println("All Suppliers: " + all);
//    }
//}

