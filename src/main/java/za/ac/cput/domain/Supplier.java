/*
Supplier POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/11
 */
package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    private String supplierName;

    private String contactDetails;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("supplier-products")
    private java.util.List<Product> products;

    protected Supplier() {
    }

    public Supplier(Builder builder) {
        this.supplierId = builder.supplierId;
        this.supplierName = builder.supplierName;
        this.contactDetails = builder.contactDetails;
        this.products = builder.products;
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

    public java.util.List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                '}';
    }

    public static class Builder {
        private int supplierId;
        private String supplierName;
        private String contactDetails;
        private java.util.List<Product> products;

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

        public Builder setProducts(java.util.List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder copy(Supplier supplier) {
            this.supplierId = supplier.supplierId;
            this.supplierName = supplier.supplierName;
            this.contactDetails = supplier.contactDetails;
            return this;
        }
        public Supplier build(){
            return new Supplier(this);
        }
    }
}
