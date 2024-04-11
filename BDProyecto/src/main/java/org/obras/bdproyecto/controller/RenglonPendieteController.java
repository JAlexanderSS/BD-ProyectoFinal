package org.obras.bdproyecto.controller;

import org.obras.bdproyecto.model.dto.RenglonEjecutadoDto;
import org.obras.bdproyecto.model.dto.RenglonPendienteDto;
import org.obras.bdproyecto.model.entity.RenglonPendiente;
import org.obras.bdproyecto.model.playload.MensajeResponse;
import org.obras.bdproyecto.service.IRenglonPendienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/renglonPendiente")
public class RenglonPendieteController {
    @Autowired
    private IRenglonPendienteService renglonPendienteService;

    @PostMapping("/saveRenglonPendiente")
    public ResponseEntity<?> saveRenglonPendiente(@RequestBody RenglonPendienteDto renglonPendienteDto){
        RenglonPendiente renglonPendienteSave = null;
        try {
            renglonPendienteSave = renglonPendienteService.saveRenglonPendiente(renglonPendienteDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Renglon guardado correctamente")
                    .object(renglonPendienteDto.builder()
                            .idRenglonPendiente(renglonPendienteSave.getIdRenglonPendiente())
                            .idSmip(renglonPendienteSave.getIdSmip())

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
}
