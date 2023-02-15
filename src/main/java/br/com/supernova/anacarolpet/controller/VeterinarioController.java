package br.com.supernova.anacarolpet.controller;

import br.com.supernova.anacarolpet.Repository.VeterinarioRepository;
import br.com.supernova.anacarolpet.domain.veterinario.dto.DadosListagemVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.dto.FormCadastroVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.model.Veterinario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {
    @Autowired
    private VeterinarioRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid FormCadastroVeterinario form){
        repository.save(new Veterinario(form));
    }

    @GetMapping
    public Page<DadosListagemVeterinario> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        //return repository.findAll().stream().map(DadosListagemVeterinario::new).toList // Caso fosse necessário o retorno de um objeto List<Veterinario>
        return repository.findAll(paginacao).map(DadosListagemVeterinario::new);
    }

}
