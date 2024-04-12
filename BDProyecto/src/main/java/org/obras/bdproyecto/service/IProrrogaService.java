package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.ProrrogaDto;
import org.obras.bdproyecto.model.entity.Prorroga;

import java.util.List;

public interface IProrrogaService {
    Prorroga saveProrroga(ProrrogaDto prorrogaDto);
    Prorroga findProrrogaById(Integer idProrroga);
    boolean existsByIdProrroga(Integer idProrroga);
    void deleteProrroga(Prorroga prorroga);
    List<Prorroga> listAllProrroga();
}
