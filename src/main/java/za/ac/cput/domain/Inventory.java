/*
 * Inventory.java
 * Inventory POJO class
 * Author: O Ntsaluba (230741754)
 * Date: 11 May 2025
 */

package za.ac.cput.domain;

import java.util.Date;

public class Inventory {
    private int inventoryId;
    private int productId;
    private Date receivedDate;
    private String stockAdded;
    private int supplierId;

    public Inventory() {
    }

    private Inventory(Builder builder) {
        this.inventoryId = builder.inventoryId;
        this.productId = builder.productId;
        this.receivedDate = builder.receivedDate;
        this.stockAdded = builder.stockAdded;
        this.supplierId = builder.supplierId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public String getStockAdded() {
        return stockAdded;
    }

    public int getSupplierId() {
        return supplierId;
    }



    public static class Builder {
        private int inventoryId;
        private int productId;
        private Date receivedDate;
        private String stockAdded;
        private int supplierId;

        public Builder setInventoryId(int inventoryId) {
            this.inventoryId = inventoryId;
            return this;
        }

        public Builder setProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder setReceivedDate(Date receivedDate) {
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

        @Override
        public String toString() {
            return "Inventory{" +
                    "inventoryId=" + inventoryId +
                    ", productId=" + productId +
                    ", receivedDate=" + receivedDate +
                    ", stockAdded='" + stockAdded + '\'' +
                    ", supplierId=" + supplierId +
                    '}';
        }

        public Builder copy(Inventory inventory) {
            this.inventoryId = inventory.inventoryId;
            this.productId = inventory.productId;
            this.receivedDate = inventory.receivedDate;
            this.stockAdded = inventory.stockAdded;
            this.supplierId = inventory.supplierId;
            return this;
        }

        public Inventory build() {
            return new Inventory(this);
        }
    }
}