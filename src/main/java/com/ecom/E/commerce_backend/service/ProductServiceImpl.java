package com.ecom.E.commerce_backend.service;

import com.ecom.E.commerce_backend.model.Cart;
import com.ecom.E.commerce_backend.model.CartItem;
import com.ecom.E.commerce_backend.model.Product;
import com.ecom.E.commerce_backend.model.User;
import com.ecom.E.commerce_backend.repository.CartItemRepository;
import com.ecom.E.commerce_backend.repository.CartRepository;
import com.ecom.E.commerce_backend.repository.ProductRepository;
import com.ecom.E.commerce_backend.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private CartRepository cartRepo;

    @Override
    public ResponseEntity<Product> addProduct(Product product) {
        return new ResponseEntity<>(productRepo.save(product),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProduct() {
        return new ResponseEntity<>(productRepo.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductByID(Long id) throws Exception {

        Optional<Product> product =productRepo.findById(id);
        if(product.isEmpty()){
            throw  new Exception("product not found");
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProductByCategory(Long id) {
        return new ResponseEntity<>(productRepo.findProductByCategoryId(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Long id,ProductRequest request) throws Exception {


        Product updateProduct= getProductByID(id).getBody();


        updateProduct.setProductName(request.getProductName());
        updateProduct.setProductImg(request.getProductImg());
        updateProduct.setProductPrice(request.getProductPrice());
        updateProduct.setDescription(request.getDescription());
        updateProduct.setCategory(request.getCategory());

        return new ResponseEntity<>(productRepo.save(updateProduct), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) throws Exception {

        Product product = getProductByID(id).getBody(); //find the product

        List<CartItem> cartItems=cartItemRepo.findByProductId(id); //find the cartItem which have the product

        for(CartItem item:cartItems){
            Optional<Cart> cart = cartRepo.findById(item.getCart().getId()); //find the cart which contain the  cart item
            cart.get().setCartTotal(cart.get().getCartTotal()-item.getCartItemTotal()); // minus the  cart total with deleted product
        }

        productRepo.deleteById(id);
        String DeleteMsg="Deleted Successfully";
        return new ResponseEntity(DeleteMsg,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> searchProduct(String keyword) {
        return new ResponseEntity<>(productRepo.searchProduct(keyword),HttpStatus.OK);
    }
}
