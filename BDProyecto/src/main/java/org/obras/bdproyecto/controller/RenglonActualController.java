package org.obras.bdproyecto.controller;

import org.obras.bdproyecto.model.playload.MensajeResponse;
import org.obras.bdproyecto.service.IRenglonActualService;
import org.obras.bdproyecto.model.dto.RenglonActualDto;
import org.obras.bdproyecto.model.entity.RenglonActual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/renglonActual")
public class RenglonActualController {
    @Autowired
    private IRenglonActualService renglonActualService;

    @PostMapping("/saveRenglonActual")
    public ResponseEntity<?> saveRenglonActual(@RequestBody RenglonActualDto renglonActualDto){
        RenglonActual renglonActual_Save = null;
        try {
            renglonActual_Save = renglonActualService.saveRenglonActual(renglonActualDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Renglon guardado correctamente")
                    .object(RenglonActualDto.builder()
                                    .idRenglonActual(renglonActual_Save.getIdRenglonEjecutado())
                                    .idSmip(renglonActual_Save.getIdSmip())
                                    .numRenglonTrabajo(renglonActual_Save.getNumRenglonTrabajo())
                                    .renglonTrabajo(renglonActual_Save.getRenglonTrabajo())
                                    .unidadMedida(renglonActual_Save.getUnidadMedida())
                                    .cantidad(renglonActual_Save.getCantidad())
                                    .costoUnitario(renglonActual_Save.getCostoUnitario())
                                    .costoTotal(renglonActual_Save.getCostoTotal())
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

    @GetMapping ("/findRenglonActualById/{idRenglonActual}")
    public ResponseEntity<?> findRenglonActualById(@PathVariable Integer idRenglonActual){
        RenglonActual renglonActual = renglonActualService.findRenglonActualById(idRenglonActual);
        if (renglonActual == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Renglon no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Renglon encontrado")
                .object(RenglonActualDto.builder()
                        .idRenglonActual(renglonActual.getIdRenglonEjecutado())
                        .idSmip(renglonActual.getIdSmip())
                        .numRenglonTrabajo(renglonActual.getNumRenglonTrabajo())
                        .renglonTrabajo(renglonActual.getRenglonTrabajo())
                        .unidadMedida(renglonActual.getUnidadMedida())
                        .cantidad(renglonActual.getCantidad())
                        .costoUnitario(renglonActual.getCostoUnitario())
                        .costoTotal(renglonActual.getCostoTotal())
                        .build()
                )
                .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/deleteRenglonActual/{idRenglonActual}")
    public ResponseEntity<?> deleteRenglonActual(@PathVariable Integer idRenglonActual){
        try {
            RenglonActual renglonActualDelete = renglonActualService.findRenglonActualById(idRenglonActual);
            renglonActualService.deleteRenglonActual(renglonActualDelete);
            return new ResponseEntity<>(renglonActualDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta eliminar no existe")
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @GetMapping("/listAllRenglonActual")
    public ResponseEntity<?> showAll(){
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Lista de renglones")
                .object(renglonActualService.listAllRenglonActual())
                .build()
                , HttpStatus.OK);
    }

    @PutMapping("/updateRenglonActual/{id}")
    public ResponseEntity<?> updateRenglonActual(@PathVariable Integer id, @RequestBody RenglonActualDto renglonActualDto){
        try {
            if (renglonActualService.existsByIdRenglonActual(id)){
                renglonActualDto.setIdRenglonActual(id);
                RenglonActual udateRenglonActual = renglonActualService.saveRenglonActual(renglonActualDto);
                RenglonActualDto renglonActualDtoUpdate = RenglonActualDto.builder()
                        .idRenglonActual(udateRenglonActual.getIdRenglonEjecutado())
                        .idSmip(udateRenglonActual.getIdSmip())
                        .numRenglonTrabajo(udateRenglonActual.getNumRenglonTrabajo())
                        .renglonTrabajo(udateRenglonActual.getRenglonTrabajo())
                        .unidadMedida(udateRenglonActual.getUnidadMedida())
                        .cantidad(udateRenglonActual.getCantidad())
                        .costoUnitario(udateRenglonActual.getCostoUnitario())
                        .costoTotal(udateRenglonActual.getCostoTotal())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Renglon actualizado correctamente")
                        .object(renglonActualDtoUpdate)
                        .build()
                        , HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no existe")
                        .object(null)
                        .build()
                        , HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Error al intentar actualizar el registro")
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}