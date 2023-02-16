package br.com.supernova.anacarolpet.domain.usuario.service;

import br.com.supernova.anacarolpet.domain.usuario.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
@Service
public class JWTToken {

    @Value("${api.security.token.secret}")
    private String secret;
   public String getToken(Usuario usuario){
       try {
           var algorithm = Algorithm.HMAC256(secret);
           return JWT.create()
                   .withIssuer("API Ana Loves Pets")
                   .withSubject(usuario.getLogin())
                   .withExpiresAt(dataDeExpiracao())
                   .sign(algorithm);
       } catch (JWTCreationException ex){
            throw new RuntimeException("Erro ao gerar o Token JWT");
       }
   }

   public  String getSubject(String jwtToken){
       try {
           var algorithm = Algorithm.HMAC256(secret);
           return JWT.require(algorithm)
                   .withIssuer("API Ana Loves Pets")
                   .build()
                   .verify(jwtToken)
                   .getSubject();
       } catch (JWTCreationException ex){
           throw new RuntimeException("Token JWT inválido!");
       }
   }

    private Instant dataDeExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
