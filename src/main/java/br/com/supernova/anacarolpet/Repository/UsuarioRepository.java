package br.com.supernova.anacarolpet.Repository;

import br.com.supernova.anacarolpet.domain.veterinario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
