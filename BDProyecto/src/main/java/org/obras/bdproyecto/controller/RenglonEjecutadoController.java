package org.obras.bdproyecto.controller;

import org.obras.bdproyecto.model.dto.RenglonEjecutadoDto;
import org.obras.bdproyecto.model.entity.RenglonEjecutado;
import org.obras.bdproyecto.model.playload.MensajeResponse;
import org.obras.bdproyecto.service.IRenglonEjecutadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/renglonEjecutado")
public class RenglonEjecutadoController {
    @Autowired
    private IRenglonEjecutadoService renglonEjecutadoService;

    @PostMapping("/saveRenglonEjecutado")
    public ResponseEntity<?> saveRenglonEjecutado(@RequestBody RenglonEjecutadoDto renglonEjecutadoDto){
        RenglonEjecutado renglonEjecutadoSave = null;
        try {
            renglonEjecutadoSave = renglonEjecutadoService.saveRenglonEjecutado(renglonEjecutadoDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Proyecto guardado correctamente")
                    .object(RenglonEjecutadoDto.builder()
                            .idRenglonEjecutado(renglonEjecutadoSave.getIdRenglonEjecutado())
                            .idSmip(renglonEjecutadoSave.getIdSmip())
                            .numRenglonTrabajo(renglonEjecutadoSave.getNumRenglonTrabajo())
                            .renglonTrabajo(renglonEjecutadoSave.getRenglonTrabajo())
                            .unidadMedida(renglonEjecutadoSave.getUnidadMedida())
                            .cantidad(renglonEjecutadoSave.getCantidad())
                            .costoUnitario(renglonEjecutadoSave.getCostoUnitario())
                            .costoTotal(renglonEjecutadoSave.getCostoTotal())
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

    @GetMapping ("/findRenglonEjecutadoById/{idRenglonEjecutado}")
    public ResponseEntity<?> findRenglonEjecutadoById(@PathVariable Integer idRenglonEjecutado){
        RenglonEjecutado renglonEjecutado = renglonEjecutadoService.findRenglonEjecutadoById(idRenglonEjecutado);
        if (renglonEjecutado == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Proyecto no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Proyecto encontrado")
                .object(RenglonEjecutadoDto.builder()
                        .idRenglonEjecutado(renglonEjecutado.getIdRenglonEjecutado())
                        .idSmip(renglonEjecutado.getIdSmip())
                        .numRenglonTrabajo(renglonEjecutado.getNumRenglonTrabajo())
                        .renglonTrabajo(renglonEjecutado.getRenglonTrabajo())
                        .unidadMedida(renglonEjecutado.getUnidadMedida())
                        .cantidad(renglonEjecutado.getCantidad())
                        .costoUnitario(renglonEjecutado.getCostoUnitario())
                        .costoTotal(renglonEjecutado.getCostoTotal())
                        .build()
                )
                .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/deleteRenglonEjecutado/{idRenglonEjecutado}")
    public ResponseEntity<?> deleteRenglonEjecutado(@PathVariable Integer idRenglonEjecutado){
        try {
            RenglonEjecutado renglonEjecutadoDelete = renglonEjecutadoService.findRenglonEjecutadoById(idRenglonEjecutado);
            renglonEjecutadoService.deleteRenglonEjecutado(renglonEjecutadoDelete);
            return new ResponseEntity<>(renglonEjecutadoDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta eliminar no existe")
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listAllRenglonEjecutado")
    public ResponseEntity<?> showAll(){
        List <RenglonEjecutado> getListRenglonEjecutado = renglonEjecutadoService.listAllRenglonEjecutado();
        if (getListRenglonEjecutado == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getListRenglonEjecutado)
                        .build(),HttpStatus.OK);
    }

    @PostMapping("/updateRenglonEjecutado")
    public ResponseEntity<?> updateRenglonEjecutado(@RequestBody RenglonEjecutadoDto renglonEjecutadoDto){
        RenglonEjecutado renglonEjecutadoUpdate = null;
        try{
            if (renglonEjecutadoService.existsByIdRenglonEjecutado(renglonEjecutadoDto.getIdRenglonEjecutado())){
                renglonEjecutadoUpdate = renglonEjecutadoService.saveRenglonEjecutado(renglonEjecutadoDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Proyecto actualizado correctamente")
                        .object(RenglonEjecutadoDto.builder()
                                .idRenglonEjecutado(renglonEjecutadoUpdate.getIdRenglonEjecutado())
                                .idSmip(renglonEjecutadoUpdate.getIdSmip())
                                .numRenglonTrabajo(renglonEjecutadoUpdate.getNumRenglonTrabajo())
                                .renglonTrabajo(renglonEjecutadoUpdate.getRenglonTrabajo())
                                .unidadMedida(renglonEjecutadoUpdate.getUnidadMedida())
                                .cantidad(renglonEjecutadoUpdate.getCantidad())
                                .costoUnitario(renglonEjecutadoUpdate.getCostoUnitario())
                                .costoTotal(renglonEjecutadoUpdate.getCostoTotal())
                                .build()
                        )
                        .build()
                        ,HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El proyecto que intenta actualizar no existe")
                        .object(null)
                        .build()
                        ,HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Error al intentar actualizar el registro")
                    .object(null)
                    .build()
                    ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
