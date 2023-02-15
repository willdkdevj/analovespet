package br.com.supernova.anacarolpet.domain.veterinario.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoVeterinario(
        @NotNull
        Long id,
        String nome,
        String telefone,
        FormCadastroEndereco endereco) {
}
