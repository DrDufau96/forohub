//package com.alura.ForoHub.repository;
//
//import model.com.aluracursos.forohub.Usuario;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//    Optional<Usuario> findByLogin(String login); // Define el método personalizado
//}
//

package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);

}
