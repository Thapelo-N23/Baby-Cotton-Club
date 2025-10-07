package za.ac.cput.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.*;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.factory.SupplierFactory;
import za.ac.cput.repository.CategoryRepository;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.repository.ReviewRepository;
import za.ac.cput.repository.SupplierRepository;

@Component
public class ProductDataInitializer {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @PostConstruct
    public void initProducts() {
        if (productRepository.count() > 0) return; // Prevent duplicate seeding

        Category category = new Category.Builder()
                .setCategoryName("Baby Clothes")
                .build();
        category = categoryRepository.save(category);
        if (category == null) {
            System.err.println("Failed to create and save Category");
            return;
        }

        Supplier supplier = SupplierFactory.createSupplier("SnuggleBabies Clothing Co.", "0211234567", null);
        if (supplier == null) {
            System.err.println("Failed to create Supplier: invalid data");
            return;
        }
        supplier = supplierRepository.save(supplier);
        if (supplier == null) {
            System.err.println("Failed to save Supplier");
            return;
        }

        String[] names = {"Baby Cotton Onesie", "Soft Cotton Blanket", "Baby Boots", "Baby Dress", "Baby Princess Dress", "fleece", "Duvet", "Loafers", "Bedding", "Wool Onesie"};
        double[] prices = {120, 250, 60, 45, 180, 300, 90, 70, 150, 110};
        String[] imageUrls = {"/images/onesie.jpg", "/images/soft-cotton-blanket.jpg", "/images/boots.jpg", "/images/dress.jpg", "/images/princess_dress.jpg", "/images/fleece.jpg", "/images/duvet.jpg", "/images/loafers.jpg", "/images/bedding.jpg", "/images/wool_onesy.jpg"};
        for (int i = 0; i < names.length; i++) {
            Product p = ProductFactory.createProduct(
                    names[i],
                    "Color" + (i+1),
                    (short) prices[i], // Cast price to short
                    "available",
                    supplier
            );
            if (p == null) {
                System.err.println("Failed to create Product: invalid data for " + names[i]);
                continue;
            }
            p = new Product.Builder().copy(p).setCategory(category).setImageUrl(imageUrls[i]).build();
            Product saved = productRepository.save(p);
            if (saved == null) {
                System.err.println("Failed to save Product: " + names[i]);
                continue;
            }

            Review review = ReviewFactory.createReview((short) 4, "Great service!", "20250503", null, saved);
            if (review == null) {
                System.err.println("Failed to create Review for product: " + names[i]);
                continue;
            }
            reviewRepository.save(review);
        }
    }
}
