package br.com.supernova.anacarolpet.domain.veterinario.dto;

import br.com.supernova.anacarolpet.domain.veterinario.enums.Especialidade;
import br.com.supernova.anacarolpet.domain.veterinario.model.Veterinario;

public record DadosListagemVeterinario(String nome, String email, String crmv, Especialidade especialidade) {

    public DadosListagemVeterinario(Veterinario veterinario){
        this(veterinario.getName(), veterinario.getEmail(), veterinario.getCrmv(), veterinario.getEspecialidade());
    }
}
