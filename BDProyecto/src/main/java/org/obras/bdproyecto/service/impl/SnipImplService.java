package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.SnipDao;
import org.obras.bdproyecto.model.dto.SnipDto;
import org.obras.bdproyecto.model.entity.Snip;
import org.obras.bdproyecto.service.ISnipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

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
    public Snip findSnipById(Integer idSnip) {
        return snipDao.findById(idSnip).orElse(null);
    }

    @Override
    public boolean existsByIdSnip(Integer idSnip) {
        return snipDao.existsById(idSnip);
    }

    @Override
    public void deleteSnip(Snip snip) {
        snipDao.delete(snip);
    }

    @Override
    public List<Snip> listAllSnip() {
        return (List) snipDao.findAll();
    }
}
