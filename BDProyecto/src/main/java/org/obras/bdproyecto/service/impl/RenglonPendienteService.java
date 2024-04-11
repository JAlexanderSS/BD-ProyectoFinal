package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.RenglonPendieteDao;
import org.obras.bdproyecto.model.dto.RenglonPendienteDto;
import org.obras.bdproyecto.model.entity.RenglonPendiente;
import org.obras.bdproyecto.service.IRenglonPendienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenglonPendienteService implements IRenglonPendienteService {
    @Autowired
    private RenglonPendieteDao renglonPendieteDao;

    @Override
    public RenglonPendiente saveRenglonPendiente(RenglonPendienteDto renglonPendienteDto) {
        RenglonPendiente renglonPendiente = RenglonPendiente.builder()
                .idRenglonPendiente(renglonPendienteDto.getIdRenglonPendiente())
                .idSmip(renglonPendienteDto.getIdSmip())
                .numRenglonTrabajo(renglonPendienteDto.getNumRenglonTrabajo())
                .renglonTrabajo(renglonPendienteDto.getRenglonTrabajo())
                .unidadMedida(renglonPendienteDto.getUnidadMedida())
                .cantidad(renglonPendienteDto.getCantidad())
                .costoUnitario(renglonPendienteDto.getCostoUnitario())
                .costoTotal(renglonPendienteDto.getCostoTotal())
                .build();
        return renglonPendieteDao.save(renglonPendiente);
    }

    @Override
    public RenglonPendiente findRenglonPendienteById(Integer idRenglonPendiente) {
        return renglonPendieteDao.findById(idRenglonPendiente).orElse(null);
    }

    @Override
    public boolean existsByIdRenglonPendiente(Integer idRenglonPendiente) {
        return renglonPendieteDao.existsById(idRenglonPendiente);
    }

    @Override
    public void deleteRenglonPendiente(RenglonPendiente renglonPendiente) {
        renglonPendieteDao.delete(renglonPendiente);
    }

    @Override
    public List<RenglonPendiente> listAllRenglonPendiente() {
        return (List) renglonPendieteDao.findAll();
    }
}
