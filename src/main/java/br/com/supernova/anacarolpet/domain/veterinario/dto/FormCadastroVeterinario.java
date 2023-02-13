package br.com.supernova.anacarolpet.domain.veterinario.dto;

import br.com.supernova.anacarolpet.domain.veterinario.enums.Especialidade;

public record FormCadastroVeterinario(Long id, String nome, String email, String crmv, Especialidade especialidade, FormCadastroEndereco formCadastroEndereco) {

}
