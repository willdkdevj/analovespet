package br.com.supernova.anacarolpet.domain.veterinario.dto;

import br.com.supernova.anacarolpet.domain.veterinario.model.Veterinario;

public record DadosDetalheVeterinario(Long id, String nome, String email, String telefone, String crmv, String especialidade, FormCadastroEndereco endereco) {

    public DadosDetalheVeterinario(Veterinario veterinario){
        this(veterinario.getId(), veterinario.getNome(), veterinario.getEmail(), veterinario.getTelefone(), veterinario.getCrmv(), veterinario.getEspecialidade().getValue(),
                new FormCadastroEndereco(veterinario.getEndereco().getLogradouro(), veterinario.getEndereco().getBairro(), veterinario.getEndereco().getCep(), veterinario.getEndereco().getCidade(), veterinario.getEndereco().getUf(),veterinario.getEndereco().getComplemento(), veterinario.getEndereco().getNumero()));
    }
}
