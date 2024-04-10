package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.RenglnEjecutadoDao;
import org.obras.bdproyecto.model.dto.RenglonEjecutadoDto;
import org.obras.bdproyecto.model.entity.RenglonEjecutado;
import org.obras.bdproyecto.service.IRenglonEjecutadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenglonEjecutadoImplService implements IRenglonEjecutadoService {
    @Autowired
    private RenglnEjecutadoDao renglnejecutadoDao;

    @Override
    public RenglonEjecutado saveRenglonEjecutado(RenglonEjecutadoDto renglonEjecutadoDto) {
        RenglonEjecutado renglonEjecutado = RenglonEjecutado.builder()
                .idRenglonEjecutado(renglonEjecutadoDto.getIdRenglonEjecutado())
                .idSmip(renglonEjecutadoDto.getIdSmip())
                .numRenglonTrabajo(renglonEjecutadoDto.getNumRenglonTrabajo())
                .renglonTrabajo(renglonEjecutadoDto.getRenglonTrabajo())
                .unidadMedida(renglonEjecutadoDto.getUnidadMedida())
                .cantidad(renglonEjecutadoDto.getCantidad())
                .costoUnitario(renglonEjecutadoDto.getCostoUnitario())
                .costoTotal(renglonEjecutadoDto.getCostoTotal())
            .build();
        return renglnejecutadoDao.save(renglonEjecutado);
    }

    @Override
    public RenglonEjecutado findRenglonEjecutadoById(Integer idRenglonEjecutado) {
        return renglnejecutadoDao.findById(idRenglonEjecutado).orElse(null);
    }

    @Override
    public boolean existsByIdRenglonEjecutado(Integer idRenglonEjecutado) {
        return renglnejecutadoDao.existsById(idRenglonEjecutado);
    }

    @Override
    public void deleteRenglonEjecutado(RenglonEjecutado renglonEjecutado) {
        renglnejecutadoDao.delete(renglonEjecutado);
    }

    @Override
    public List<RenglonEjecutado> listAllRenglonEjecutado() {
        return (List) renglnejecutadoDao.findAll();
    }
}