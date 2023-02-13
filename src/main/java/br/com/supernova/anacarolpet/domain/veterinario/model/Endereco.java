package br.com.supernova.anacarolpet.domain.veterinario.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;

    private String numero;
    private String complemento;

    private String cidade;
    private String uf;
}
