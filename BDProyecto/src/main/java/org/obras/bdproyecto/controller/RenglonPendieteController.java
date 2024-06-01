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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping ("/findRenglonPendienteById/{idRenglonPendiente}")
    public ResponseEntity<?> findRenglonPendienteById(@PathVariable Integer idRenglonPendiente){
        RenglonPendiente renglonPendiente = renglonPendienteService.findRenglonPendienteById(idRenglonPendiente);
        if (renglonPendiente == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Renglon no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Renglon encontrado")
                .object(RenglonPendienteDto.builder()
                        .idRenglonPendiente(renglonPendiente.getIdRenglonPendiente())
                        .idSmip(renglonPendiente.getIdSmip())
                        .numRenglonTrabajo(renglonPendiente.getNumRenglonTrabajo())
                        .renglonTrabajo(renglonPendiente.getRenglonTrabajo())
                        .unidadMedida(renglonPendiente.getUnidadMedida())
                        .cantidad(renglonPendiente.getCantidad())
                        .costoUnitario(renglonPendiente.getCostoUnitario())
                        .costoTotal(renglonPendiente.getCostoTotal())
                        .build()
                )
                .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/deleteRenglonPendiente/{idRenglonPendiente}")
    public ResponseEntity<?> deleteRenglonPendiente(@PathVariable Integer idRenglonPendiente){
        try {
            RenglonPendiente renglonPendienteDelete = renglonPendienteService.findRenglonPendienteById(idRenglonPendiente);
            renglonPendienteService.deleteRenglonPendiente(renglonPendienteDelete);
            return new ResponseEntity<>(renglonPendienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/listAllRenglonPendiente")
    public ResponseEntity<?> showAll(){
        List<RenglonPendiente> getListRenglonPendiente = renglonPendienteService.listAllRenglonPendiente();
        if (getListRenglonPendiente == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Registros encontrados")
                        .object(getListRenglonPendiente)
                        .build(), HttpStatus.OK
        );
    }

    @PutMapping("/updateRenglonPendiente/{idRenglonPendiente}")
    public ResponseEntity<?> updateRenglonPendiente(@PathVariable Integer idRenglonPendiente, @RequestBody RenglonPendienteDto renglonPendienteDto) {
        try {
            if (renglonPendienteService.existsByIdRenglonPendiente(idRenglonPendiente)) {
                renglonPendienteDto.setIdRenglonPendiente(idRenglonPendiente);
                RenglonPendiente renglonPendienteUpdate = renglonPendienteService.saveRenglonPendiente(renglonPendienteDto);
                RenglonPendienteDto renglonPendienteDtoUpdate = RenglonPendienteDto.builder()
                        .idRenglonPendiente(renglonPendienteUpdate.getIdRenglonPendiente())
                        .idSmip(renglonPendienteUpdate.getIdSmip())
                        .numRenglonTrabajo(renglonPendienteUpdate.getNumRenglonTrabajo())
                        .renglonTrabajo(renglonPendienteUpdate.getRenglonTrabajo())
                        .unidadMedida(renglonPendienteUpdate.getUnidadMedida())
                        .cantidad(renglonPendienteUpdate.getCantidad())
                        .costoUnitario(renglonPendienteUpdate.getCostoUnitario())
                        .costoTotal(renglonPendienteUpdate.getCostoTotal())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Renglon actualizado correctamente")
                        .object(renglonPendienteDtoUpdate)
                        .build()
                        , HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Renglon no encontrado")
                        .object(null)
                        .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Error al intentar actualizar el registro")
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
