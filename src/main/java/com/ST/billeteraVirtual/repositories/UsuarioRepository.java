package com.ST.billeteraVirtual.repositories;

import com.ST.billeteraVirtual.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {


}

   /*@Query("SELECT u FROM Usuario u WHERE u.especialidad = :especialidad")
    public List<Profesional> buscarPorEspecialidad(@Param("especialidad") Especialidad especialidad);*/

