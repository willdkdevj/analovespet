package br.com.supernova.anacarolpet.Repository;

import br.com.supernova.anacarolpet.domain.veterinario.dto.DadosListagemVeterinario;
import br.com.supernova.anacarolpet.domain.veterinario.model.Veterinario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}
