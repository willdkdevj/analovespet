package br.com.supernova.anacarolpet.exception;

public class ValidationRegisterJPAException extends RuntimeException {

    public ValidationRegisterJPAException(String message){
        super("Erro ao consultar registro no Banco de Dados! " + message);
    }
}
