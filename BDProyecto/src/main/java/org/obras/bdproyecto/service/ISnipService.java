package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.SnipDto;
import org.obras.bdproyecto.model.entity.Snip;

import java.util.List;

public interface ISnipService {
    Snip saveSnip(SnipDto snipDto);
    Snip findSnipById(Integer idSnip);
    boolean existsByIdSnip(Integer idSnip);
    void deleteSnip(Snip snip);
    List<Snip> listAllSnip();

}
