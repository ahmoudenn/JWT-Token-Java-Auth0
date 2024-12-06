package com.jwtproject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;


public class CreateJWT {
    public static void main(String[] args) {
        // Secret key for signing the JWT
        String secret = "1234"; // Should be stored securely in a real application

        String CLAIM_KEY_USER_ID = "userId";
        String CLAIM_KEY_EMAIL = "email";
        String CLAIM_KEY_ROLE = "role";
        
        Integer userId  = 6;
        String email    = "pico@ctf.org";
        String role     = "Admin";

        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.DATE, 7); //expires after 7 days

        // Create JWT
        String token = JWT.create()
                .withIssuer("bookshelf")
                .withIssuedAt(new Date())
                .withExpiresAt(expiration.getTime())
                .withClaim(CLAIM_KEY_USER_ID, userId)
                .withClaim(CLAIM_KEY_EMAIL, email)
                .withClaim(CLAIM_KEY_ROLE, role)
                .sign(Algorithm.HMAC256(secret));

        System.out.println("Generated JWT: " + token);

        // Verify JWT
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("bookshelf")
                .build()
                .verify(token);

        System.out.println("Verified JWT: " + decodedJWT.getToken());
        System.out.println("Role: " + decodedJWT.getClaim("role").asString());
    }
}
