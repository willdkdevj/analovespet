package br.com.supernova.anacarolpet.Repository;

import br.com.supernova.anacarolpet.domain.veterinario.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}
