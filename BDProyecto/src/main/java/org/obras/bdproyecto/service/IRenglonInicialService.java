package org.obras.bdproyecto.service;

import org.obras.bdproyecto.model.dto.RenglonInicialDto;
import org.obras.bdproyecto.model.entity.RenglonInicial;

import java.util.List;

public interface IRenglonInicialService {
    RenglonInicial saveRenglonInicial(RenglonInicialDto renglonInicialDto);
    RenglonInicial findRenglonInicialById(Integer idRenglonInicial);
    void deleteRenglonInicial(RenglonInicial renglonInicial);
    List<RenglonInicial> listAllRenglonInicial();
    boolean existsByIdRenglonInicial(Integer idRenglonInicial);
    List<RenglonInicial> findByIdSmip(Integer idSmip);
}
