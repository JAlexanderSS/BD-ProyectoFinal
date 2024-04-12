package org.obras.bdproyecto.controller;

import org.obras.bdproyecto.model.dto.ProrrogaDto;
import org.obras.bdproyecto.model.entity.Prorroga;
import org.obras.bdproyecto.model.playload.MensajeResponse;
import org.obras.bdproyecto.service.IProrrogaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prorroga")
public class ProrrogaController {
    @Autowired
    private IProrrogaService prorrogaService;

    @PostMapping("/saveProrroga")
    public ResponseEntity<?> saveProrroga(@RequestBody ProrrogaDto prorrogaDto){
        Prorroga prorroga_Save = null;
        try {
            prorroga_Save = prorrogaService.saveProrroga(prorrogaDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Proyecto guardado correctamente")
                    .object(ProrrogaDto.builder()
                                    .idProrroga(prorroga_Save.getIdProrroga())
                                    .idModificacion(prorroga_Save.getIdModificacion())
                                    .justificacion(prorroga_Save.getJustificacion())
                                    .fechaFinalModificada(prorroga_Save.getFechaFinalModificada())
                                    .numeroActa(prorroga_Save.getNumeroActa())
                                    .fechaActa(prorroga_Save.getFechaActa())
                                    .urlActa(prorroga_Save.getUrlActa())
                            .build()
                    )
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build()
                    ,HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @GetMapping("/findProrrogaById/{idProrroga}")
    public ResponseEntity<?> findProrrogaById(@PathVariable Integer idProrroga){
        Prorroga prorroga = prorrogaService.findProrrogaById(idProrroga);
        if (prorroga == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Proyecto no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Proyecto encontrado")
                .object(ProrrogaDto.builder()
                        .idProrroga(prorroga.getIdProrroga())
                        .idModificacion(prorroga.getIdModificacion())
                        .justificacion(prorroga.getJustificacion())
                        .fechaFinalModificada(prorroga.getFechaFinalModificada())
                        .numeroActa(prorroga.getNumeroActa())
                        .fechaActa(prorroga.getFechaActa())
                        .urlActa(prorroga.getUrlActa())
                        .build()
                )
                .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/deleteProrroga/{idProrroga}")
    public ResponseEntity<?> deleteProrroga(@PathVariable Integer idProrroga){
        try {
            Prorroga prorrogaDelete = prorrogaService.findProrrogaById(idProrroga);
            prorrogaService.deleteProrroga(prorrogaDelete);
            return new ResponseEntity<>(prorrogaDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @GetMapping("/listAllProrroga")
    public ResponseEntity<?> listAllProrroga(){
        List<Prorroga> getListProrroga = prorrogaService.listAllProrroga();
        if (getListProrroga == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getListProrroga)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("/updateProrroga")
    public ResponseEntity<?> updateProrroga(@RequestBody ProrrogaDto prorrogaDto){
        try {
            if (prorrogaService.existsByIdProrroga(prorrogaDto.getIdProrroga())){
                Prorroga prorroga = Prorroga.builder()
                        .idProrroga(prorrogaDto.getIdProrroga())
                        .idModificacion(prorrogaDto.getIdModificacion())
                        .justificacion(prorrogaDto.getJustificacion())
                        .fechaFinalModificada(prorrogaDto.getFechaFinalModificada())
                        .numeroActa(prorrogaDto.getNumeroActa())
                        .fechaActa(prorrogaDto.getFechaActa())
                        .urlActa(prorrogaDto.getUrlActa())
                        .build();
                prorrogaService.saveProrroga(prorrogaDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Proyecto actualizado correctamente")
                        .object(ProrrogaDto.builder()
                                .idProrroga(prorroga.getIdProrroga())
                                .idModificacion(prorroga.getIdModificacion())
                                .justificacion(prorroga.getJustificacion())
                                .fechaFinalModificada(prorroga.getFechaFinalModificada())
                                .numeroActa(prorroga.getNumeroActa())
                                .fechaActa(prorroga.getFechaActa())
                                .urlActa(prorroga.getUrlActa())
                                .build()
                        )
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El proyecto que intenta actualizar no existe")
                        .object(null)
                        .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Error al intentar actualizar el registro")
                    .object(null)
                    .build()
                    ,HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }
}
