
/*
Supplier POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/11
 */
package za.ac.cput.domain;

public class Supplier {
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String address;
    private String email;

    public Supplier() {
    }

    public Supplier(Builder builder) {
        this.supplierId = builder.supplierId;
        this.supplierName = builder.supplierName;
        this.contactNumber = builder.contactNumber;
        this.address = builder.address;
        this.email = builder.email;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder {
        private String supplierId;
        private String supplierName;
        private String contactNumber;
        private String address;
        private String email;

        public Builder setSupplierId(String supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public Builder setSupplierName(String supplierName) {
            this.supplierName = supplierName;
            return this;
        }

        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "supplierId='" + supplierId + '\'' +
                    ", supplierName='" + supplierName + '\'' +
                    ", contactNumber='" + contactNumber + '\'' +
                    ", address='" + address + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

        public Builder copy(Supplier supplier) {
            this.supplierId = supplier.getSupplierId();
            this.supplierName = supplier.getSupplierName();
            this.contactNumber = supplier.getContactNumber();
            this.address = supplier.getAddress();
            this.email = supplier.getEmail();
            return this;
        }

        public Supplier build() {
            return new Supplier(this);
        }
    }
}
