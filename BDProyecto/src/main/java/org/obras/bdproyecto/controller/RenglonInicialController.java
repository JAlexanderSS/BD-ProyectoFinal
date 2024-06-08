package org.obras.bdproyecto.controller;

import org.obras.bdproyecto.model.dto.RenglonInicialDto;
import org.obras.bdproyecto.model.entity.RenglonInicial;
import org.obras.bdproyecto.model.playload.MensajeResponse;
import org.obras.bdproyecto.service.IRenglonInicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/renglonInicial")
public class RenglonInicialController {
    @Autowired
    private IRenglonInicialService renglonInicialService;

    @PostMapping("/saveRenglonInicial")
    public ResponseEntity<?> saveRenglonInicial(@RequestBody RenglonInicialDto renglonInicialDto){
        RenglonInicial renglonInicial_Save = null;
        try {
            renglonInicial_Save = renglonInicialService.saveRenglonInicial(renglonInicialDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Renglon guardado correctamente")
                    .object(RenglonInicialDto.builder()
                            .idRenglonInicial(renglonInicial_Save.getIdRenglonInicial())
                            .idSmip(renglonInicial_Save.getIdSmip())
                            .numRenglonTrabajo(renglonInicial_Save.getNumRenglonTrabajo())
                            .renglonTrabajo(renglonInicial_Save.getRenglonTrabajo())
                            .unidadMedida(renglonInicial_Save.getUnidadMedida())
                            .cantidad(renglonInicial_Save.getCantidad())
                            .costoUnitario(renglonInicial_Save.getCostoUnitario())
                            .costoTotal(renglonInicial_Save.getCostoTotal())
                            .build()
                    )
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/findRenglonInicialById/{idRenglonInicial}")
    public ResponseEntity<?> findRenglonInicialById(@PathVariable Integer idRenglonInicial){
        RenglonInicial renglonInicial = renglonInicialService.findRenglonInicialById(idRenglonInicial);
        if (renglonInicial == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Renglon no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Renglon encontrado")
                .object(RenglonInicialDto.builder()
                        .idRenglonInicial(renglonInicial.getIdRenglonInicial())
                        .idSmip(renglonInicial.getIdSmip())
                        .numRenglonTrabajo(renglonInicial.getNumRenglonTrabajo())
                        .renglonTrabajo(renglonInicial.getRenglonTrabajo())
                        .unidadMedida(renglonInicial.getUnidadMedida())
                        .cantidad(renglonInicial.getCantidad())
                        .costoUnitario(renglonInicial.getCostoUnitario())
                        .costoTotal(renglonInicial.getCostoTotal())
                        .build()
                )
                .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/deleteRenglonInicial/{idRenglonInicial}")
    public ResponseEntity<?> deleteRenglonInicial(@PathVariable Integer idRenglonInicial){
        try {
            RenglonInicial renglonInicialDelete = renglonInicialService.findRenglonInicialById(idRenglonInicial);
            renglonInicialService.deleteRenglonInicial(renglonInicialDelete);
            return new ResponseEntity<>(renglonInicialDelete, HttpStatus.NO_CONTENT);
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

    @GetMapping("/listBySmip/{idSmip}")
    public ResponseEntity<?> listBySmip(@PathVariable Integer idSmip){
        List<RenglonInicial> getListRenglonInicial = renglonInicialService.findByIdSmip(idSmip);
        if (getListRenglonInicial == null || getListRenglonInicial.isEmpty()){
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
                        .object(getListRenglonInicial)
                        .build(),HttpStatus.OK);
    }

    @PutMapping("/updateRenglonInicial/{idRenglonInicial}")
    public ResponseEntity<?> update(@PathVariable Integer idRenglonInicial, @RequestBody RenglonInicialDto renglonInicialDto){
        try {
            if (renglonInicialService.existsByIdRenglonInicial(idRenglonInicial)){
                renglonInicialDto.setIdRenglonInicial(idRenglonInicial);
                RenglonInicial renglonInicialUpdate = renglonInicialService.saveRenglonInicial(renglonInicialDto);
                RenglonInicialDto updateRenglonInicial = RenglonInicialDto.builder()
                        .idRenglonInicial(renglonInicialUpdate.getIdRenglonInicial())
                        .idSmip(renglonInicialUpdate.getIdSmip())
                        .numRenglonTrabajo(renglonInicialUpdate.getNumRenglonTrabajo())
                        .renglonTrabajo(renglonInicialUpdate.getRenglonTrabajo())
                        .unidadMedida(renglonInicialUpdate.getUnidadMedida())
                        .cantidad(renglonInicialUpdate.getCantidad())
                        .costoUnitario(renglonInicialUpdate.getCostoUnitario())
                        .costoTotal(renglonInicialUpdate.getCostoTotal())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Renglon actualizado correctamente")
                        .object(updateRenglonInicial)
                        .build()
                        , HttpStatus.OK);
            } else{
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Renglon no encontrado")
                        .object(null)
                        .build()
                        , HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Error al intentar actualizar el registro")
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}