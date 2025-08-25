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
    private int inventoryId; // Use String to match Helper.generateId()

    private LocalDate receivedDate;
    private String stockAdded;

    @OneToMany(mappedBy = "inventory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Supplier> suppliers;

    @ManyToOne(fetch = FetchType.EAGER) // don't re-persist Product
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    protected Inventory() {
    }

    private Inventory(Builder builder) {
        this.inventoryId = builder.inventoryId;
        this.receivedDate = builder.receivedDate;
        this.stockAdded = builder.stockAdded;
        this.suppliers = builder.suppliers;
        this.product = builder.product;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public String getStockAdded() {
        return stockAdded;
    }

    public List<Supplier> getSupplier() {
        return suppliers;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId='" + inventoryId + '\'' +
                ", receivedDate=" + receivedDate +
                ", stockAdded='" + stockAdded + '\'' +
                ", supplier=" + suppliers +
                ", product=" + product +
                '}';
    }

    public static class Builder {
        private int inventoryId;
        private LocalDate receivedDate;
        private String stockAdded;
        private List<Supplier> suppliers;
        private Product product;

        public Builder setInventoryId(int inventoryId) {
            this.inventoryId = inventoryId;
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

        public Builder setSupplier(List<Supplier> suppliers) {
            this.suppliers = suppliers;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(Inventory inventory) {
            this.inventoryId = inventory.inventoryId;
            this.receivedDate = inventory.receivedDate;
            this.stockAdded = inventory.stockAdded;
            this.suppliers = inventory.suppliers;
            this.product = inventory.product;
            return this;
        }

        public Inventory build() {return new Inventory(this);
        }
    }
}
