package com.ecom.E.commerce_backend.authenticationController;

import com.ecom.E.commerce_backend.configuration.JwtProvider;
import com.ecom.E.commerce_backend.model.Cart;
import com.ecom.E.commerce_backend.model.User;
import com.ecom.E.commerce_backend.model.User_Role;
import com.ecom.E.commerce_backend.repository.CartRepository;
import com.ecom.E.commerce_backend.repository.UserRepository;
import com.ecom.E.commerce_backend.request.SignInRequest;
import com.ecom.E.commerce_backend.response.AuthResponse;
import com.ecom.E.commerce_backend.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class authController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private JwtProvider jwtProvider;


    @Autowired
    private CustomUserDetails customUserDetails;

    @PostMapping("/signup") // create an account
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) throws Exception {

        System.out.println(user.getPassword());
        User isEmailExist =userRepo.findByEmail(user.getEmail());

        if(isEmailExist!=null){
            throw new Exception("This email is already exits try with email");
        }
        User createUser= new User();
        createUser.setFullName(user.getFullName());
        createUser.setEmail(user.getEmail());
        createUser.setRole(user.getRole());
        createUser.setPassword(passwordEncoder.encode(user.getPassword())); //convert plain text into hidden password

        User savedUser = userRepo.save(createUser);

        //create cart for the user
        Cart cart = new Cart(); //when user create an account create a cart for the Particular user
        cart.setUser(savedUser);
        cartRepo.save(cart);


        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        //Generating the token
        String jwt=jwtProvider.generateToken(authentication);



        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Successfully");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }



    @PostMapping("/signin")  //Login account
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest req) {

        String userName=req.getUserName();
        String password= req.getPassword();

        Authentication auth = authentication(userName,password);

        //for taking role in Authorities
        Collection<? extends GrantedAuthority> authorities=auth.getAuthorities();
        String role=authorities.isEmpty() ? null :authorities.iterator().next().getAuthority();

        String jwt=jwtProvider.generateToken(auth);

        AuthResponse res= new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("Login successfully");
        res.setRole(User_Role.valueOf(role));

        return new ResponseEntity<>(res,HttpStatus.OK);
    }


    public Authentication authentication (String userName, String password){

        UserDetails userDetails= customUserDetails.loadUserByUsername(userName);

        if(userDetails==null){
            throw new BadCredentialsException("invalid Username");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}
