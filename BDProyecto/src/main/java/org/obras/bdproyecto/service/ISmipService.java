package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.SmipDto;
import org.obras.bdproyecto.model.entity.Smip;

import java.util.List;

public interface ISmipService {
    Smip saveSmip(SmipDto smipDto);
    Smip findSmipById(Integer idSmip);
    boolean existsByIdSmip(Integer idSmip);
    void deleteSmip(Smip smip);
    List<Smip> listAllSmip();
}
