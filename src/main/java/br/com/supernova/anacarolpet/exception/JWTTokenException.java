package br.com.supernova.anacarolpet.exception;


public class JWTTokenException extends RuntimeException{

    public JWTTokenException(String message){
        super("Erro no processo de autenticação: " + message);
    }

}
