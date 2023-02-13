package br.com.supernova.anacarolpet.controller;

import br.com.supernova.anacarolpet.domain.veterinario.Veterinario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Veterinario form){

        return null;
    }

}
