package za.ac.cput.exception;

/**
 * @deprecated The CartNotFoundException is no longer used by the codebase
 * (CartService now throws RuntimeException for missing carts). This class
 * is retained temporarily for backwards-compatibility and can be safely
 * deleted once the repository no longer references it.
 */
@Deprecated
public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message);
    }
}
