package br.com.supernova.anacarolpet.domain.veterinario;

import br.com.supernova.anacarolpet.domain.veterinario.common.Endereco;
import br.com.supernova.anacarolpet.domain.veterinario.common.Especialidade;

public record Veterinario(Long id, String nome, String email, String crmv, Especialidade especialidade, Endereco endereco) {
}
