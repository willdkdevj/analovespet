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

    public void validarEndereco(FormCadastroEndereco formEndereco) {
        if(formEndereco.logradouro() != null) this.logradouro = formEndereco.logradouro();
        if(formEndereco.bairro() != null) this.bairro = formEndereco.bairro();
        if(formEndereco.cep() != null) this.cep = formEndereco.cep();
        if(formEndereco.numero() != null) this.numero = formEndereco.numero();
        if(formEndereco.complemento() != null) this.complemento = formEndereco.complemento();
        if(formEndereco.cidade() != null) this.cidade = formEndereco.cidade();
        if(formEndereco.uf() != null) this.uf = formEndereco.uf();


    }
}
