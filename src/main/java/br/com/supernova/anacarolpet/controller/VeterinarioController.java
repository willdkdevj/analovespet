package br.com.supernova.anacarolpet.controller;

import br.com.supernova.anacarolpet.Repository.VeterinarioRepository;
import br.com.supernova.anacarolpet.domain.veterinario.dto.DadosAtualizacaoVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.dto.DadosDetalheVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.dto.DadosListagemVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.dto.FormCadastroVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.model.Veterinario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veterinarios")
@SecurityRequirement(name = "bearer-key")
public class VeterinarioController {
    @Autowired
    private VeterinarioRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid FormCadastroVeterinario form, UriComponentsBuilder uriBuilder){
        var veterinario = new Veterinario(form);
        repository.save(veterinario);

        var uri = uriBuilder.path("/veterinarios/{id}").buildAndExpand(veterinario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalheVeterinario(veterinario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemVeterinario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        //return repository.findAll().stream().map(DadosListagemVeterinario::new).toList // Caso fosse necessário o retorno de um objeto List<Veterinario>
        //return repository.findAll(paginacao).map(DadosListagemVeterinario::new); // Retorna todos os registros no Page sem validar o parámetro ativo

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemVeterinario::new); // Retorna todos os registros no Page validando o parámetro ativo
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoVeterinario formAtualizar){
        var atualizarRegistro = repository.getReferenceById(formAtualizar.id());
        atualizarRegistro.validar(formAtualizar);

        return ResponseEntity.ok(new DadosDetalheVeterinario(atualizarRegistro));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        // repository.deleteById(id); // Excluir o registro físico do banco de dados
        var registro = repository.getReferenceById(id);
        registro.inativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        // repository.deleteById(id); // Excluir o registro físico do banco de dados
        var registro = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalheVeterinario(registro));
    }
}
