package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.RenglonActualDto;
import org.obras.bdproyecto.model.entity.RenglonActual;

import java.util.List;

public interface IRenglonActualService {
    RenglonActual saveRenglonActual(RenglonActualDto renglonActualDto);
    RenglonActual findRenglonActualById(Integer idRenglonActual);
    boolean existsByIdRenglonActual(Integer idRenglonActual);
    void deleteRenglonActual(RenglonActual renglonActual);
    List<RenglonActual> listAllRenglonActual();
}
