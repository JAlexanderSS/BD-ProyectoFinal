package org.obras.bdproyecto.controller;

import org.obras.bdproyecto.model.dto.SnipDto;
import org.obras.bdproyecto.model.entity.Snip;
import org.obras.bdproyecto.model.playload.MensajeResponse;
import org.obras.bdproyecto.service.ISnipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/snip")
public class SnipController {
    @Autowired
    private ISnipService snipService;
    @PostMapping("/saveSnip")
    public ResponseEntity<?> saveSnip(@RequestBody SnipDto snipDto){
        Snip snip_Save = null;
        try {
            snip_Save = snipService.saveSnip(snipDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Proyecto guardado correctamente")
                    .object(SnipDto.builder()
                                    .idSnip(snip_Save.getIdSnip())
                                    .noSnip(snip_Save.getNoSnip())
                                    .nombreProyecto(snip_Save.getNombreProyecto())
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
    @GetMapping ("/findSnipById/{idSnip}")
    public ResponseEntity<?> findSnipById(@PathVariable Integer idSnip){
        Snip snip = snipService.findSnipById(idSnip);
        if (snip == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Proyecto no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Proyecto encontrado")
                .object(SnipDto.builder()
                        .idSnip(snip.getIdSnip())
                        .noSnip(snip.getNoSnip())
                        .nombreProyecto(snip.getNombreProyecto())
                        .build()
                )
                .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/deleteSnip/{idSnip}")
    public ResponseEntity<?> deleteSnip(@PathVariable Integer idSnip){
        try {
            Snip snipDelete = snipService.findSnipById(idSnip);
            snipService.deleteSnip(snipDelete);
            return new ResponseEntity<>(snipDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta eliminar no existe")
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listAllSnip")
    public ResponseEntity<?> showAll(){
        List<Snip> getListSnip = snipService.listAllSnip();
        if (getListSnip == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros!!")
                            .object(null)
                            .build(),HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getListSnip)
                        .build(),HttpStatus.OK);
    }
    @PutMapping("/updateSnip/{id}")
    public ResponseEntity<?> update(@RequestBody SnipDto snipDto, @PathVariable Integer id) {
        try {
            if (snipService.existsByIdSnip(id)) {
                snipDto.setIdSnip(id);
                Snip snipUpdate = snipService.saveSnip(snipDto);
                SnipDto updatedDto = SnipDto.builder()
                        .idSnip(snipUpdate.getIdSnip())
                        .noSnip(snipUpdate.getNoSnip())
                        .nombreProyecto(snipUpdate.getNombreProyecto())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Actualizado Correctamente")
                        .object(updatedDto)
                        .build(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Error al intentar actualizar el registro")
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

}
