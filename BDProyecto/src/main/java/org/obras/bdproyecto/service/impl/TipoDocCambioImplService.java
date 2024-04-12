package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.TipoDocCambioDao;
import org.obras.bdproyecto.model.dto.TipoDocCambioDto;
import org.obras.bdproyecto.model.entity.TipoDocCambio;
import org.obras.bdproyecto.service.ITipoDocCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocCambioImplService implements ITipoDocCambioService {
    @Autowired
    private TipoDocCambioDao tipoDocCambioDao;

    @Override
    public TipoDocCambio saveTipoDocCambio(TipoDocCambioDto tipoDocCambioDto) {
        TipoDocCambio tipoDocCambio = TipoDocCambio.builder()
            .idTipoDocCambio(tipoDocCambioDto.getIdTipoDocCambio())
            .tipoDocumentoCambio(tipoDocCambioDto.getTipoDocumentoCambio())
            .build();
        return tipoDocCambioDao.save(tipoDocCambio);
    }

    @Override
    public TipoDocCambio findTipoDocCambioById(Integer idTipoDocCambio) {
        return null;
    }

    @Override
    public boolean existsByIdTipoDocCambio(Integer idTipoDocCambio) {
        return false;
    }

    @Override
    public void deleteTipoDocCambio(TipoDocCambio tipoDocCambio) {

    }

    @Override
    public List<TipoDocCambio> listAllTipoDocCambio() {
        return null;
    }
}
