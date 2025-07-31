
/*
Supplier POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/11
 */
package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    private String supplierName;
    private String contactDetails;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Inventory> inventoryId;

    protected Supplier() {}

    private Supplier(Builder builder) {
        this.supplierId = builder.supplierId;
        this.supplierName = builder.supplierName;
        this.contactDetails = builder.contactDetails;
        this.inventoryId = builder.inventoryId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public List<Inventory> getInventoryList() {
        return inventoryId;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryId = inventoryList;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", inventoryList=" + (inventoryId != null ? inventoryId.size() : 0) +
                '}';
    }

    public static class Builder {
        private int supplierId;
        private String supplierName;
        private String contactDetails;
        private List<Inventory> inventoryId;

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

        public Builder setInventoryId(List<Inventory> inventoryId) {
            this.inventoryId = inventoryId;
            return this;
        }

        public Builder copy(Supplier supplier) {
            this.supplierId = supplier.getSupplierId();
            this.supplierName = supplier.getSupplierName();
            this.contactDetails = supplier.getContactDetails();
            this.inventoryId = supplier.getInventoryList();
            return this;
        }

        public Supplier build() {
            return new Supplier(this);
        }
    }
}
