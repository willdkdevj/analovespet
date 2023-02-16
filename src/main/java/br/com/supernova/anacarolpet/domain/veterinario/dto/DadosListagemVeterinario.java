package br.com.supernova.anacarolpet.domain.veterinario.dto;

import br.com.supernova.anacarolpet.domain.veterinario.enums.Especialidade;
import br.com.supernova.anacarolpet.domain.veterinario.model.Veterinario;

public record DadosListagemVeterinario(Long id, String nome, String email, String crmv, Especialidade especialidade) {

    public DadosListagemVeterinario(Veterinario veterinario){
        this(veterinario.getId(), veterinario.getNome(), veterinario.getEmail(), veterinario.getCrmv(), veterinario.getEspecialidade());
    }
}
