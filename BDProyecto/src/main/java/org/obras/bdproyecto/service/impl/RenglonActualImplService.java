package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.RenglonActualDao;
import org.obras.bdproyecto.model.dto.RenglonActualDto;
import org.obras.bdproyecto.model.entity.RenglonActual;
import org.obras.bdproyecto.service.IRenglonActualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenglonActualImplService implements IRenglonActualService {
    @Autowired
    private RenglonActualDao renglonActualDao;

    @Override
    public RenglonActual saveRenglonActual(RenglonActualDto renglonActualDto) {
        RenglonActual renglonActual = RenglonActual.builder()
                .idRenglonEjecutado(renglonActualDto.getIdRenglonActual())
                .idSmip(renglonActualDto.getIdSmip())
                .numRenglonTrabajo(renglonActualDto.getNumRenglonTrabajo())
                .renglonTrabajo(renglonActualDto.getRenglonTrabajo())
                .unidadMedida(renglonActualDto.getUnidadMedida())
                .cantidad(renglonActualDto.getCantidad())
                .costoUnitario(renglonActualDto.getCostoUnitario())
                .costoTotal(renglonActualDto.getCostoTotal())
            .build();
        return renglonActualDao.save(renglonActual);
    }

    @Override
    public RenglonActual findRenglonActualById(Integer idRenglonActual) {
        return renglonActualDao.findById(idRenglonActual).orElse(null);
    }

    @Override
    public boolean existsByIdRenglonActual(Integer idRenglonActual) {
        return renglonActualDao.existsById(idRenglonActual);
    }

    @Override
    public void deleteRenglonActual(RenglonActual renglonActual) {
        renglonActualDao.delete(renglonActual);
    }

    @Override
    public List<RenglonActual> listAllRenglonActual() {
        return (List) renglonActualDao.findAll();
    }
}
