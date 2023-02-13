package br.com.supernova.anacarolpet.controller;

import br.com.supernova.anacarolpet.Repository.VeterinarioRepository;
import br.com.supernova.anacarolpet.domain.veterinario.dto.FormCadastroVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.model.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {
    @Autowired
    private VeterinarioRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody FormCadastroVeterinario form){
        repository.save(new Veterinario(form));
    }

}
