package br.com.supernova.anacarolpet.domain.veterinario.model;

import br.com.supernova.anacarolpet.domain.veterinario.dto.FormCadastroEndereco;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Endereco {

    public Endereco(FormCadastroEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.complemento = dados.complemento();
        this.numero = dados.numero();
    }

    private String logradouro;
    private String bairro;
    private String cep;

    private String numero;
    private String complemento;

    private String cidade;
    private String uf;
}
