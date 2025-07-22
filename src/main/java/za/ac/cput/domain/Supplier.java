
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
    private List<Inventory> inventoryList;

    protected Supplier() {}

    private Supplier(Builder builder) {
        this.supplierId = builder.supplierId;
        this.supplierName = builder.supplierName;
        this.contactDetails = builder.contactDetails;
        this.inventoryList = builder.inventoryList;
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

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", inventoryList=" + (inventoryList != null ? inventoryList.size() : 0) +
                '}';
    }

    public static class Builder {
        private int supplierId;
        private String supplierName;
        private String contactDetails;
        private List<Inventory> inventoryList;

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

        public Builder setInventoryList(List<Inventory> inventoryList) {
            this.inventoryList = inventoryList;
            return this;
        }

        public Builder copy(Supplier supplier) {
            this.supplierId = supplier.getSupplierId();
            this.supplierName = supplier.getSupplierName();
            this.contactDetails = supplier.getContactDetails();
            this.inventoryList = supplier.getInventoryList();
            return this;
        }

        public Supplier build() {
            return new Supplier(this);
        }
    }
}