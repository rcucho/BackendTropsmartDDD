package com.techelper.tropsmart_backend.user.application.controllers;

import com.techelper.tropsmart_backend.user.domain.services.comunications.AuthResponse;
import com.techelper.tropsmart_backend.user.domain.services.inputs.SignIn;
import com.techelper.tropsmart_backend.user.domain.services.inputs.SignUp;
import com.techelper.tropsmart_backend.user.infrastructure.servicesImp.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthService authService;


    @PostMapping(value = "/sign-in")
    public ResponseEntity<AuthResponse> SignIn(@Valid @RequestBody SignIn signIn) throws Exception {
        if(signIn == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        HttpHeaders responseHeaders = new HttpHeaders();
        AuthResponse result = authService.login(signIn.getEmail(), signIn.getPassword());

        responseHeaders.add("Auth-Token", result.getResource().getToken());

        //if(!result.success)
        //return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, responseHeaders,HttpStatus.OK);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<AuthResponse> SignUp(@Valid @RequestBody SignUp signUp) throws Exception {

        if(signUp == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        AuthResponse result = authService.registerComplete(signUp);



        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    /*@PostMapping(value="/refresh",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> refreshUser(@Valid @RequestBody RefreshInput refreshInput) throws Exception {
        if(refreshInput == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        AuthResponse authResponse = authService.refresh(refreshInput);
        if (!authResponse.success)
            return new ResponseEntity<>(authResponse, HttpStatus.NOT_FOUND);

        return getAuthResponseResponseEntity(authResponse);
    }*/

    private ResponseEntity<AuthResponse> getAuthResponseResponseEntity(AuthResponse authResponse) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Auth-Token", authResponse.getResource().getToken());
        //responseHeaders.add("Refresh-Token", authResponse.getResource().getRefreshToken());

        //authResponse.getResource().setRefreshToken(null);
        authResponse.getResource().setToken(null);
        return new ResponseEntity<>(authResponse,responseHeaders, HttpStatus.OK);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}