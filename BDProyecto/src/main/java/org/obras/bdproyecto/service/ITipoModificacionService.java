package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.TipoModificacionDto;
import org.obras.bdproyecto.model.entity.TipoModificacion;

import java.util.List;

public interface ITipoModificacionService {
    TipoModificacion saveTipoModificacion(TipoModificacionDto tipoModificacionDto);
    TipoModificacion findTipoModificacionById(Integer idTipoModificacion);
    boolean existsByIdTipoModificacion(Integer idTipoModificacion);
    void deleteTipoModificacion(TipoModificacion tipoModificacion);
    List<TipoModificacion> listAllTipoModificacion();
}
