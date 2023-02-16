package br.com.supernova.anacarolpet.domain.veterinario.dto;

import br.com.supernova.anacarolpet.domain.veterinario.enums.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record FormCadastroVeterinario(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // Valida se o valor fornecido está entre 4 a 6 dígitos
        String crmv,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        FormCadastroEndereco endereco) {

}
