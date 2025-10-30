package br.com.coletaresiduos.coleta.service;

import br.com.coletaresiduos.coleta.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${secret.key}")
    private String secretWord;

    public String generateToken(User user){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretWord);

            String token = JWT.create()
                    .withIssuer("calorias")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Não foi possível gerar o token!");
        }

    }

    private Instant generateExpirationDate(){
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretWord);

            return JWT.require(algorithm)
                    .withIssuer("calorias")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException e){
            return "";
        }
    }

}
