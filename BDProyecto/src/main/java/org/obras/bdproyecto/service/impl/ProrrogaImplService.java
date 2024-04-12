package org.obras.bdproyecto.service.impl;

import org.obras.bdproyecto.model.dao.ProrrogaDao;
import org.obras.bdproyecto.model.dto.ProrrogaDto;
import org.obras.bdproyecto.model.entity.Prorroga;
import org.obras.bdproyecto.service.IProrrogaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProrrogaImplService implements IProrrogaService {
    @Autowired
    private ProrrogaDao prorrogaDao;

    @Override
    public Prorroga saveProrroga(ProrrogaDto prorrogaDto) {
        Prorroga prorroga = Prorroga.builder()
                .idProrroga(prorrogaDto.getIdProrroga())
                .idModificacion(prorrogaDto.getIdModificacion())
                .justificacion(prorrogaDto.getJustificacion())
                .fechaFinalModificada(prorrogaDto.getFechaFinalModificada())
                .numeroActa(prorrogaDto.getNumeroActa())
                .fechaActa(prorrogaDto.getFechaActa())
                .urlActa(prorrogaDto.getUrlActa())
            .build();
        return prorrogaDao.save(prorroga);
    }

    @Override
    public Prorroga findProrrogaById(Integer idProrroga) {
        return prorrogaDao.findById(idProrroga).orElse(null);
    }

    @Override
    public boolean existsByIdProrroga(Integer idProrroga) {
        return prorrogaDao.existsById(idProrroga);
    }

    @Override
    public void deleteProrroga(Prorroga prorroga) {
        prorrogaDao.delete(prorroga);
    }

    @Override
    public List<Prorroga> listAllProrroga() {
        return (List) prorrogaDao.findAll();
    }
}
