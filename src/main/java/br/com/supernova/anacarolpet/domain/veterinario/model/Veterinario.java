package br.com.supernova.anacarolpet.domain.veterinario.model;


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

    private String crmv;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Veterinario(FormCadastroVeterinario form) {
        this.name = form.nome();
        this.email = form.email();
        this.crmv = form.crmv();
        this.endereco = new Endereco(form.formCadastroEndereco());
    }


}
