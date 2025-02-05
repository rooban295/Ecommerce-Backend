package com.ecom.E.commerce_backend.service;

import com.ecom.E.commerce_backend.model.Cart;
import com.ecom.E.commerce_backend.model.CartItem;
import com.ecom.E.commerce_backend.model.Product;
import com.ecom.E.commerce_backend.model.User;
import com.ecom.E.commerce_backend.repository.CartItemRepository;
import com.ecom.E.commerce_backend.repository.CartRepository;
import com.ecom.E.commerce_backend.request.AddToCartRequest;
import com.ecom.E.commerce_backend.request.UpdateCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private CartRepository cartRepo;


    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public List<CartItem> cartItems(Long id) {
        return cartItemRepo.findByProductId(id);
    }

    @Override
    public List<CartItem> getProductFromCart(String jwt) {

        User user= userService.getUserByJwtToken(jwt);

        Cart cart=cartRepo.findByUserId(user.getId());

        List<CartItem> allCartItem= cartItemRepo.findByCartId(cart.getId());

        return allCartItem;
    }


    @Override
    public ResponseEntity<CartItem> addToCart(AddToCartRequest req, String jwt) throws Exception {

        User user = userService.getUserByJwtToken(jwt);


        Product product = productService.getProductByID(req.getProductId()).getBody();

        Cart cart=cartRepo.findByUserId(user.getId());

        CartItem cartItem= new CartItem();

        cartItem.setProduct(product);

        cartItem.setCartItemTotal((cartItem.getCartItemTotal() + (product.getProductPrice()  * req.getQuantity())));

        cart.setCartTotal(cart.getCartTotal()+cartItem.getCartItemTotal());

        cartItem.setQuantity(req.getQuantity());

        cartItem.setCart(cart);

        return new ResponseEntity<>(cartItemRepo.save(cartItem),HttpStatus.ACCEPTED);
    }


    @Override
    public ResponseEntity<Cart> getCart(String jwt) {
        User user=userService.getUserByJwtToken(jwt);
        Cart cart = cartRepo.findByUserId(user.getId());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> deleteCartItem(Long id, String jwt) throws Exception {

        Optional<CartItem> cartItem = cartItemRepo.findById(id);

        if(cartItem.isEmpty()){
            throw new Exception("Cart item not found");
        }
        User user=userService.getUserByJwtToken(jwt);

        Cart cart=cartRepo.findByUserId(user.getId());

        cart.setCartTotal(cart.getCartTotal()  - cartItem.get().getCartItemTotal());

        cartRepo.save(cart);

        cartItemRepo.deleteById(cartItem.get().getId());

        return new ResponseEntity<>("Item deleted Successfully  ", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartItem> updateCartItem(UpdateCartItemRequest req, String jwt) throws Exception {

        User user= userService.getUserByJwtToken(jwt);

        Cart cart=cartRepo.findByUserId(user.getId());

        Optional<CartItem> updatedCartItem =cartItemRepo.findById(req.getId());

        if(updatedCartItem.isEmpty()){
            throw new Exception("CartItem Not found");
        }

        Product product= productService.getProductByID(updatedCartItem.get().getProduct().getId()).getBody();

        updatedCartItem.get().setQuantity(req.getQuantity());
        updatedCartItem.get().setCartItemTotal((product.getProductPrice()  *  updatedCartItem.get().getQuantity()));


        List<CartItem>item = getProductFromCart(jwt);
        double cartTotal=0.0;
        for(CartItem item1:item){
            cartTotal+=item1.getCartItemTotal();
        }
        cart.setCartTotal(cartTotal);

        return new ResponseEntity<>(cartItemRepo.save(updatedCartItem.get()),HttpStatus.OK);
    }


}
