package com.krushna.Java_rnd.jwt;

import java.util.Arrays;
import java.util.List;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;

public class JWTUtil {

	public static void main(String[] args) throws Exception {
		 String token="Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6InBmZWRzdGFnZV9zaWduX01heTIwMjUiLCJwaS5hdG0iOiI1In0.eyJzY29wZSI6Im9wZW5pZCBmdWxsIiwiYXV0aG9yaXphdGlvbl9kZXRhaWxzIjpbXSwiY2xpZW50X2lkIjoiQ29ubmVjdGVkLUFzc29jaWF0ZSIsImd1aWQiOiJUTGtRbzBPeW80NlRRQmVDS0tNMVptWDVkSDVrV2lQUSIsImlzcyI6Imh0dHBzOi8vcGZlZGNlcnQud2FsLW1hcnQuY29tIiwianRpIjoiMUFLVGV5aGwiLCJhdWQiOiJDb25uZWN0ZWQtQXNzb2NpYXRlIiwic3ViIjoiMjMyNTc2NTM5IiwidXBuIjoiczBhMGoxZkBob21lb2ZmaWNlLndhbC1tYXJ0LmNvbSIsIm5iZiI6MTczOTUyMjgwMCwiaWF0IjoxNzM5NTIyOTIwLCJleHAiOjE3Mzk1MjM4MjB9.f3dGCGMzysYMZ9Z_B5OPUAP3UytD_MP5pkWM4r_KmE-w1lvaC3Li8__Smdex4UVvGZ7I0bTD84P2i5Ty_1nWsp_-co9aIk7Ye9r1NkAEJa78F3npKaTi4iHK0YZuS0Mi0OL9A38waodsYDDN4rLVegjTNUri3e50MZ0Mmb8atQCOqHuV7WTEYPLchd7166kei9XjrY1qmVU-2AbcLlr6oi6YdgYNKD89khXT6ZINN3NEK26mLESKKYeeQDyHO8Vk4VmfRNrQvK4JXSwiYictQHudTHNODEtK9r4E1sfjUaifvHFEMJvZEW5ffn-AibtiLOpuHLkVXDHmTFjLzD1Axusr86OSghbh3GURs5TIR26diYq35fu5rPVCkcrzz9f5sSv3CVcR0DfM7y3myK9KRJOwtN6c1jK70H6TvN2URKkVkRVP0Sa9QNPep7tb5Pes58AEMZE3IXWa0tiSe3qaecFM7umvpu1J9TSEYneB8ZFGXGeHE98kKqznq3u1YxKn0OucdWr9MZXVXnIZwarU-sOrO8-qwaRfBffnDC_yZ8TBSSraA1Kv9N3qW22itCMTT4BLJ8QG4-Ev-wzMi6vrt7ySWMakCcAplv1rW6rRQdZCzPGefqbr5BDsfyn5cPSpRQ-ajG1HOiS-0XZOoHV9x-ndAVk1i0W7o6CYcmzDsuc";
		//String token = createToken();
		JwtClaims claims = getJwtClaims(token);
		System.out.println(claims);

	}
	
	private static JwtClaims getJwtClaims(String jwtToken) {
	    try {
	      final JwtConsumer jwtConsumer =
	          new JwtConsumerBuilder()
	              .setSkipAllValidators()
	              .setDisableRequireSignature()
	              .setSkipSignatureVerification()
	              .build();
	      String exactToken = jwtToken.substring(6);
	      final JwtContext jwtContext = jwtConsumer.process(exactToken);

	      JwtClaims claims = jwtContext.getJwtClaims();
	      return claims;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	  }
	  

	public static String createToken() throws Exception {
		  
		   // Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
		    RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);

		    // Give the JWK a Key ID (kid), which is just the polite thing to do
		    rsaJsonWebKey.setKeyId("k1");
		  
		  // Create the Claims, which will be the content of the JWT
		    JwtClaims claims = new JwtClaims();
		    claims.setIssuer("Issuer");  // who creates the token and signs it
		    claims.setAudience("Audience"); // to whom the token is intended to be sent
		    claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
		    claims.setGeneratedJwtId(); // a unique identifier for the token
		    claims.setIssuedAtToNow();  // when the token was issued/created (now)
		    claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
		    claims.setSubject("subject"); // the subject/principal is whom the token is about
		    claims.setClaim("email","mail@example.com"); // additional claims/attributes about the subject can be added
		    
		    
		    List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
		    
		    claims.setStringListClaim("groups", groups); // multi-valued claims work too and will end up as a JSON array

		    // A JWT is a JWS and/or a JWE with JSON claims as the payload.
		    // In this example it is a JWS so we create a JsonWebSignature object.
		    JsonWebSignature jws = new JsonWebSignature();

		    // The payload of the JWS is JSON content of the JWT Claims
		    jws.setPayload(claims.toJson());

		    // The JWT is signed using the private key
		    
		    jws.setKey(rsaJsonWebKey.getPrivateKey());

		    // Set the Key ID (kid) header because it's just the polite thing to do.
		    // We only have one key in this example but a using a Key ID helps
		    // facilitate a smooth key rollover process
		    //jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

		    // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
		    jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

		    // Sign the JWS and produce the compact serialization or the complete JWT/JWS
		    // representation, which is a string consisting of three dot ('.') separated
		    // base64url-encoded parts in the form Header.Payload.Signature
		    // If you wanted to encrypt it, you can simply set this jwt as the payload
		    // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
		    String jwt = jws.getCompactSerialization();


		    // Now you can do something with the JWT. Like send it to some other party
		    // over the clouds and through the interwebs.
		    System.out.println("JWT: " + jwt);
		    
		    return "Bearer "+jwt;

	  }
}
