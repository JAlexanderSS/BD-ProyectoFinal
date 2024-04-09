package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.RenglonInicialDao;
import org.obras.bdproyecto.model.dto.RenglonInicialDto;
import org.obras.bdproyecto.model.entity.RenglonInicial;
import org.obras.bdproyecto.service.IRenglonInicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenlgonInicialImplService implements IRenglonInicialService {
    @Autowired
    private RenglonInicialDao renglonInicialDao;
    @Override
    public RenglonInicial saveRenglonInicial(RenglonInicialDto renglonInicialDto) {
        RenglonInicial renglonInicial = RenglonInicial.builder()
                .idRenglonInicial(renglonInicialDto.getIdRenglonInicial())
                .idSmip(renglonInicialDto.getIdSmip())
                .numRenglonTrabajo(renglonInicialDto.getNumRenglonTrabajo())
                .renglonTrabajo(renglonInicialDto.getRenglonTrabajo())
                .unidadMedida(renglonInicialDto.getUnidadMedida())
                .cantidad(renglonInicialDto.getCantidad())
                .costoUnitario(renglonInicialDto.getCostoUnitario())
                .costoTotal(renglonInicialDto.getCostoTotal())
                .build();
        return renglonInicialDao.save(renglonInicial);
    }

    @Override
    public RenglonInicial findRenglonInicialById(Integer idRenglonInicial) {
        return renglonInicialDao.findById(idRenglonInicial).orElse(null);
    }

    @Override
    public boolean existsByIdRenglonInicial(Integer idRenglonInicial) {
        return renglonInicialDao.existsById(idRenglonInicial);
    }

    @Override
    public void deleteRenglonInicial(RenglonInicial renglonInicial) {
        renglonInicialDao.delete(renglonInicial);
    }

    @Override
    public List<RenglonInicial> listAllRenglonInicial() {
        return (List) renglonInicialDao.findAll();
    }
}
