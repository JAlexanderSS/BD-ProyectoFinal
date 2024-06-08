package org.obras.bdproyecto.model.dao;

import org.obras.bdproyecto.model.entity.RenglonInicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RenglonInicialDao extends JpaRepository<RenglonInicial, Integer> {
    List<RenglonInicial> findByIdSmip(Integer idSmip);
}
