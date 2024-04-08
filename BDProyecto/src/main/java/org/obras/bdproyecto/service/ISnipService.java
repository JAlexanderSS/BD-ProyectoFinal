package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.SnipDto;
import org.obras.bdproyecto.model.entity.Snip;

public interface ISnipService {
    Snip saveSnip(SnipDto snipDto);
    Snip findSnipById(Integer idSnip);
    boolean existsSnipById(Integer idSnip);
    void deleteSnip(Integer idSnip);
}
