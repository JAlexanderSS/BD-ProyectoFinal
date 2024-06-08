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
        RenglonInicial renglonInicial = new RenglonInicial();
        renglonInicial.setIdSmip(renglonInicialDto.getIdSmip());
        renglonInicial.setNumRenglonTrabajo(renglonInicialDto.getNumRenglonTrabajo());
        renglonInicial.setRenglonTrabajo(renglonInicialDto.getRenglonTrabajo());
        renglonInicial.setUnidadMedida(renglonInicialDto.getUnidadMedida());
        renglonInicial.setCantidad(renglonInicialDto.getCantidad());
        renglonInicial.setCostoUnitario(renglonInicialDto.getCostoUnitario());
        renglonInicial.setCostoTotal(renglonInicialDto.getCostoTotal());
        return renglonInicialDao.save(renglonInicial);
    }

    @Override
    public RenglonInicial findRenglonInicialById(Integer idRenglonInicial) {
        return renglonInicialDao.findById(idRenglonInicial).orElse(null);
    }

    @Override
    public void deleteRenglonInicial(RenglonInicial renglonInicial) {
        renglonInicialDao.delete(renglonInicial);
    }

    @Override
    public List<RenglonInicial> listAllRenglonInicial() {
        return renglonInicialDao.findAll();
    }

    @Override
    public boolean existsByIdRenglonInicial(Integer idRenglonInicial) {
        return renglonInicialDao.existsById(idRenglonInicial);
    }

    @Override
    public List<RenglonInicial> findByIdSmip(Integer idSmip) {
        return renglonInicialDao.findByIdSmip(idSmip);
    }
}
