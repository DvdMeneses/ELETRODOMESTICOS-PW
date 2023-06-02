package com.eletros.Repository;

import com.eletros.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findUsuarioByLogin(String login);

    Usuario getUsuarioByLogin(String Username);



}
