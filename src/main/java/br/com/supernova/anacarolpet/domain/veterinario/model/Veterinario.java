package br.com.supernova.anacarolpet.domain.veterinario.model;


import br.com.supernova.anacarolpet.domain.veterinario.dto.DadosAtualizacaoVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.dto.FormCadastroVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.enums.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "veterinarios")
@Entity(name = "Veterinario")
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String crmv;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Veterinario(FormCadastroVeterinario form) {
        this.name = form.nome();
        this.email = form.email();
        this.phone = form.telefone();
        this.crmv = form.crmv();
        this.endereco = new Endereco(form.formCadastroEndereco());
        this.ativo = Boolean.TRUE;
    }

    public void validar(DadosAtualizacaoVeterinario formAtualizar){
        if(formAtualizar.nome() != null) this.name = formAtualizar.nome();
        if(formAtualizar.telefone() != null) this.phone = formAtualizar.telefone();
        if(formAtualizar.endereco() != null) this.endereco.validarEndereco(formAtualizar.endereco());
    }

    public void inativar() {
        this.ativo = false;
    }
}
