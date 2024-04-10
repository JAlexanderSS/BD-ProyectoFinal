package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.RenglonEjecutadoDto;
import org.obras.bdproyecto.model.entity.RenglonEjecutado;

import java.util.List;

public interface IRenglonEjecutadoService {
    RenglonEjecutado saveRenglonEjecutado(RenglonEjecutadoDto renglonEjecutadoDto);
    RenglonEjecutado findRenglonEjecutadoById(Integer idRenglonEjecutado);
    boolean existsByIdRenglonEjecutado(Integer idRenglonEjecutado);
    void deleteRenglonEjecutado(RenglonEjecutado renglonEjecutado);
    List<RenglonEjecutado> listAllRenglonEjecutado();
}
