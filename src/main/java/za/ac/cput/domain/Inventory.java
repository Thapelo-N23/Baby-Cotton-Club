/*
 * Inventory.java
 * Inventory POJO class
 * Author: O Ntsaluba (230741754)
 * Date: 11 May 2025
 */

package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int inventoryId;
    private int productId;
    private LocalDate receivedDate;
    private String stockAdded;
    private int supplierId;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<Supplier> supplier;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Inventory() {
    }

    private Inventory(Builder builder) {
        this.inventoryId = builder.inventoryId;
        this.productId = builder.productId;
        this.receivedDate = builder.receivedDate;
        this.stockAdded = builder.stockAdded;
        this.supplierId = builder.supplierId;
        this.supplier = builder.supplier;
        this.product = builder.product;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public String getStockAdded() {
        return stockAdded;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public List<Supplier> getSupplier() {
        return supplier;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", productId=" + productId +
                ", receivedDate=" + receivedDate +
                ", stockAdded='" + stockAdded + '\'' +
                ", supplierId=" + supplierId +
                ", supplier=" + supplier +
                ", product=" + product +
                '}';
    }

    public static class Builder {
        private int inventoryId;
        private int productId;
        private LocalDate receivedDate;
        private String stockAdded;
        private int supplierId;
        private List<Supplier> supplier;
        private Product product;

        public Builder setInventoryId(int inventoryId) {
            this.inventoryId = inventoryId;
            return this;
        }

        public Builder setProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder setReceivedDate(LocalDate receivedDate) {
            this.receivedDate = receivedDate;
            return this;
        }

        public Builder setStockAdded(String stockAdded) {
            this.stockAdded = stockAdded;
            return this;
        }

        public Builder setSupplierId(int supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public Builder setSupplier(List<Supplier> supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(Inventory inventory) {
            this.inventoryId = inventory.inventoryId;
            this.productId = inventory.productId;
            this.receivedDate = inventory.receivedDate;
            this.stockAdded = inventory.stockAdded;
            this.supplierId = inventory.supplierId;
            this.supplier = inventory.supplier;
            this.product = inventory.product;
            return this;
        }

        public Inventory build() {return new Inventory(this);
        }
    }
}