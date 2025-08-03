
/*
Supplier POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/11
 */
package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer supplierId;

    private String supplierName;

    private String contactDetails;

    private Integer inventoryId;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    protected Inventory inventory;

    protected Supplier() {
    }

    public Supplier(Builder builder) {
        this.supplierId = builder.supplierId;
        this.supplierName = builder.supplierName;
        this.contactDetails = builder.contactDetails;
        this.inventoryId = builder.inventoryId;
        this.inventory = builder.inventory;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", inventoryId=" + inventoryId +
                ", inventory=" + (inventory != null ? inventory.getInventoryId() : null) +
                '}';
    }

    public static class Builder {
        private Integer supplierId;
        private String supplierName;
        private String contactDetails;
        private Integer inventoryId;
        private Inventory inventory;

        public Builder setSupplierId(Integer supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public Builder setSupplierName(String supplierName) {
            this.supplierName = supplierName;
            return this;
        }

        public Builder setContactDetails(String contactDetails) {
            this.contactDetails = contactDetails;
            return this;
        }

        public Builder setInventoryId(Integer inventoryId) {
            this.inventoryId = inventoryId;
            return this;
        }

        public Builder setInventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        public Builder copy(Supplier supplier) {
            this.supplierId = supplier.supplierId;
            this.supplierName = supplier.supplierName;
            this.contactDetails = supplier.contactDetails;
            this.inventoryId = supplier.inventoryId;
            this.inventory = supplier.inventory;
            return this;
        }
        public Supplier build(){
            return new Supplier(this);
        }
    }
}
