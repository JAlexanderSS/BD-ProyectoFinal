package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.SnipDao;
import org.obras.bdproyecto.model.dto.SnipDto;
import org.obras.bdproyecto.model.entity.Snip;
import org.obras.bdproyecto.service.ISnipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;

@Service
public class SnipImplService implements ISnipService {
    @Autowired
    private SnipDao snipDao;
    @Override
    public Snip saveSnip(SnipDto snipDto) {
        Snip snip = Snip.builder()
            .idSnip(snipDto.getIdSnip())
            .noSnip(snipDto.getNoSnip())
            .nombreProyecto(snipDto.getNombreProyecto())
            .build();
        return snipDao.save(snip);
    }

    @Override
    @GetMapping("/findSnipById/{idSnip}")
    public Snip findSnipById(Integer idSnip) {
        return snipDao.existsSnipById(idSnip);
    }

    @Override
    public boolean existsSnipById(Integer idSnip) {
        return snipDao.existsById(idSnip);
    }

    @Override
    public void deleteSnip(Integer idSnip) {
        snipDao.existsById(idSnip);
    }
}
