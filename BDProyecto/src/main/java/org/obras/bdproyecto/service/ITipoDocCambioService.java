package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.TipoDocCambioDto;
import org.obras.bdproyecto.model.entity.TipoDocCambio;

import java.util.List;

public interface ITipoDocCambioService {
    TipoDocCambio saveTipoDocCambio(TipoDocCambioDto tipoDocCambioDto);
    TipoDocCambio findTipoDocCambioById(Integer idTipoDocCambio);
    boolean existsByIdTipoDocCambio(Integer idTipoDocCambio);
    void deleteTipoDocCambio(TipoDocCambio tipoDocCambio);
    List<TipoDocCambio> listAllTipoDocCambio();
}
