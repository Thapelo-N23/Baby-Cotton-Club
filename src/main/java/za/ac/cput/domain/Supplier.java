
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    private String supplierName;

    private String contactDetails;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    protected Inventory inventory;

    protected Supplier() {
    }

    public Supplier(Builder builder) {
        this.supplierId = builder.supplierId;
        this.supplierName = builder.supplierName;
        this.contactDetails = builder.contactDetails;
        this.inventory = builder.inventory;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getContactDetails() {
        return contactDetails;
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
                ", inventory=" + (inventory != null ? inventory.getInventoryId() : null) +
                '}';
    }

    public static class Builder {
        private int supplierId;
        private String supplierName;
        private String contactDetails;
        private Inventory inventory;

        public Builder setSupplierId(int supplierId) {
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
        public Builder setInventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        public Builder copy(Supplier supplier) {
            this.supplierId = supplier.supplierId;
            this.supplierName = supplier.supplierName;
            this.contactDetails = supplier.contactDetails;
            this.inventory = supplier.inventory;
            return this;
        }
        public Supplier build(){
            return new Supplier(this);
        }
    }
}
