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

import java.util.Arrays;
import java.util.List;

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
        if (productRepository.count() > 0) return;

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

        String[] names = {
                "All-In-One Set",
                "Boy Brown 2 Piece",
                "Boy Duvet Set",
                "Boy Formal Suit",
                "Boy Sandals",
                "Boy Summer Pyjamas",
                "Boys Two Piece Set",
                "Burgundy Ballroom Dress",
                "Comfortable Lace-Up Sneakers",
                "Girl Ribbon Pumps",
                "Girls 2 Piece Dress",
                "Girls Sandals",
                "Girls Summer Dress",
                "Newborn Dress",
                "Newborn Knitted Set",
                "Newborn Romper",
                "Pink Princess Dress"
        };
        double[] prices = {
                220, // All-In-One_Set
                180, // Boy_Brown_2_Piece
                350, // Boy_Duvet_Set
                299, // Boy_Formal_Suit
                120, // Boy_Sandals
                160, // Boy_Summer_Pyjamas
                210, // Boys_Two_Piece_Set
                280, // Burgundy_Ballroom_Dress
                140, // Comfortable_Lace-Up_Sneakers
                130, // Girl_Ribbon_Pumps
                190, // Girls_2_Piece_Dress
                120, // Girls_Sandals
                170, // Girls_Summer_Dress
                150, // Newborn_Dress
                240, // Newborn_Knitted_Set
                160,
                230
        };
        String[] imageUrls = {
                "/images/All-In-One_Set.png",
                "/images/Boy_Brown_2_Piece.png",
                "/images/Boy_Duvet_Set.png",
                "/images/Boy_Formal_Suit.png",
                "/images/Boy_Sandals.png",
                "/images/Boy_Summer_Pyjamas.png",
                "/images/Boys_Two_Piece_Set.png",
                "/images/Burgundy_Ballroom_Dress.png",
                "/images/Comfortable_Lace-Up_Sneakers.png",
                "/images/Girl_Ribbon_Pumps.png",
                "/images/Girls_2_Piece_Dress.jpg",
                "/images/Girls_Sandals.png",
                "/images/Girls_Summer_Dress.png",
                "/images/Newborn_Dress.jpg",
                "/images/Newborn_Knitted_Set.png",
                "/images/Newborn_Romper.png",
                "/images/Pink_Princess_Dress.jpg"
        };

        // Define size categories
        List<String> babySizes = Arrays.asList("0-3M", "3-6M", "6-9M", "9-12M", "12-18M", "18-24M");
        List<String> toddlerSizes = Arrays.asList("2-3 years", "3-4 years", "4-5 years", "5-6 years");
        List<String> newbornSizes = Arrays.asList("Newborn");
        List<String> kidsShoeSizes = Arrays.asList("4", "5", "6", "7", "8", "9", "10");
        List<String> duvetSizes = Arrays.asList("Cot Duvet", "Toddler Duvet", "Single Duvet", "Double Duvet");

        for (int i = 0; i < names.length; i++) {
            Product p = ProductFactory.createProduct(
                    names[i],
                    "Color" + (i+1),
                    (short) prices[i],
                    "available",
                    supplier
            );
            if (p == null) {
                System.err.println("Failed to create Product: invalid data for " + names[i]);
                continue;
            }
            List<String> sizesForProduct = babySizes;
            String lower = names[i].toLowerCase();
            if (lower.contains("newborn")) {
                sizesForProduct = newbornSizes;
            } else if (lower.contains("duvet")) {
                sizesForProduct = duvetSizes;
            } else if (lower.contains("sandal") || lower.contains("sneaker") || lower.contains("pumps") || lower.contains("shoe")) {
                sizesForProduct = kidsShoeSizes;
            } else if (lower.contains("toddler") || lower.contains("two piece") || lower.contains("2 piece") || lower.contains("pyjama") || lower.contains("pyjamas")) {
                sizesForProduct = toddlerSizes;
            }

            p = new Product.Builder().copy(p).setCategory(category).setImageUrl(imageUrls[i]).setSizes(sizesForProduct).build();
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