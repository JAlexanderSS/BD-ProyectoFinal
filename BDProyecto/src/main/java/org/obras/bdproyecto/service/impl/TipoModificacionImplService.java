package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.TipoModificacionDao;
import org.obras.bdproyecto.model.dto.TipoModificacionDto;
import org.obras.bdproyecto.model.entity.TipoModificacion;
import org.obras.bdproyecto.service.ITipoModificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoModificacionImplService implements ITipoModificacionService {
     @Autowired
     private TipoModificacionDao tipoModificacionDao;
    @Override
    public TipoModificacion saveTipoModificacion(TipoModificacionDto tipoModificacionDto) {
        TipoModificacion tipoModificacion = TipoModificacion.builder()
            .idTipoModificacion(tipoModificacionDto.getIdTipoModificacion())
            .modificacion(tipoModificacionDto.getModificacion())
            .build();
        return tipoModificacionDao.save(tipoModificacion);
    }

    @Override
    public TipoModificacion findTipoModificacionById(Integer idTipoModificacion) {
        return tipoModificacionDao.findById(idTipoModificacion).orElse(null);
    }

    @Override
    public boolean existsByIdTipoModificacion(Integer idTipoModificacion) {
        return tipoModificacionDao.existsById(idTipoModificacion);
    }

    @Override
    public void deleteTipoModificacion(TipoModificacion tipoModificacion) {
        tipoModificacionDao.delete(tipoModificacion);
    }

    @Override
    public List<TipoModificacion> listAllTipoModificacion() {
        return (List) tipoModificacionDao.findAll();
    }
}
