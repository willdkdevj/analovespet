package br.com.supernova.anacarolpet.controller;

import br.com.supernova.anacarolpet.domain.usuario.dto.DadosToken;
import br.com.supernova.anacarolpet.domain.usuario.dto.FormAutenticacao;
import br.com.supernova.anacarolpet.domain.usuario.model.Usuario;
import br.com.supernova.anacarolpet.domain.usuario.service.JWTToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuteinticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JWTToken service;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid FormAutenticacao form){

        var authToken = new UsernamePasswordAuthenticationToken(form.login(), form.senha());
        var authenticate = manager.authenticate(authToken);

        String jwtToken = service.getToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new DadosToken(jwtToken));
    }
}
