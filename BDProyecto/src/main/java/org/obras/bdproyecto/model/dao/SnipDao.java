package org.obras.bdproyecto.model.dao;

import org.obras.bdproyecto.model.entity.Snip;
import org.springframework.data.repository.CrudRepository;

public interface SnipDao extends CrudRepository<Snip, Integer>{
    Snip existsSnipById(Integer idSnip);
}
