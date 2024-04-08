package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.SmipDao;
import org.obras.bdproyecto.model.dto.SmipDto;
import org.obras.bdproyecto.model.entity.Smip;
import org.obras.bdproyecto.service.ISmipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SmipImlService implements ISmipService {
    @Autowired
    private SmipDao smipDao;
    @Override
    public Smip saveSmip(SmipDto smipDto) {
        Smip smip = Smip.builder()
                .idSmip(smipDto.getIdSmip())
                .numeroSmip(smipDto.getNumeroSmip())
                .idSnip(smipDto.getIdSnip())
                .monto(smipDto.getMonto())
                .build();
        return smipDao.save(smip);
    }

    @Override
    public Smip findSmipById(Integer idSmip) {
        return smipDao.findById(idSmip).orElse(null);
    }

    @Override
    public boolean existsByIdSmip(Integer idSmip) {
        return smipDao.existsById(idSmip);
    }

    @Override
    public void deleteSmip(Smip smip) {
        smipDao.delete(smip);
    }

    @Override
    public List<Smip> listAllSmip() {
        return (List) smipDao.findAll();
    }
}
