package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.RenglonPendienteDto;
import org.obras.bdproyecto.model.entity.RenglonPendiente;

import java.util.List;

public interface IRenglonPendienteImplService {
    RenglonPendiente saveRenglonPendiente(RenglonPendienteDto renglonPendienteDto);
    RenglonPendiente findRenglonPendienteById(Integer idRenglonPendiente);
    boolean existsByIdRenglonPendiente(Integer idRenglonPendiente);
    void deleteRenglonPendiente(RenglonPendiente renglonPendiente);
    List<RenglonPendiente> listAllRenglonPendiente();
}
