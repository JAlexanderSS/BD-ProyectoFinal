package org.obras.bdproyecto.controller;

import org.obras.bdproyecto.model.dto.TipoModificacionDto;
import org.obras.bdproyecto.model.entity.TipoModificacion;
import org.obras.bdproyecto.model.playload.MensajeResponse;
import org.obras.bdproyecto.service.ITipoModificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoModificacion")
public class TipoModificacionController {
    @Autowired
    private ITipoModificacionService tipoModificacionService;
    @PostMapping("/saveTipoModificacion")
    public ResponseEntity<?> saveTipoModificacion(@RequestBody TipoModificacionDto tipoModificacionDto){
        TipoModificacion tipoModificacion_Save = null;
        try {
            tipoModificacion_Save = tipoModificacionService.saveTipoModificacion(tipoModificacionDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Tipo de modificación guardado correctamente")
                    .object(TipoModificacionDto.builder()
                                    .idTipoModificacion(tipoModificacion_Save.getIdTipoModificacion())
                                    .modificacion(tipoModificacion_Save.getModificacion())
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

    @GetMapping ("/findTipoModificacionById/{idTipoModificacion}")
    public ResponseEntity<?> findTipoModificacionById(@PathVariable Integer idTipoModificacion){
        TipoModificacion tipoModificacion = tipoModificacionService.findTipoModificacionById(idTipoModificacion);
        if (tipoModificacion == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Tipo de modificación no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Tipo de modificación encontrado")
                .object(TipoModificacionDto.builder()
                        .idTipoModificacion(tipoModificacion.getIdTipoModificacion())
                        .modificacion(tipoModificacion.getModificacion())
                        .build()
                )
                .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/deleteTipoModificacion/{idTipoModificacion}")
    public ResponseEntity<?> deleteTipoModificacion(@PathVariable Integer idTipoModificacion){
        try {
            TipoModificacion tipoModificacion = tipoModificacionService.findTipoModificacionById(idTipoModificacion);
            tipoModificacionService.deleteTipoModificacion(tipoModificacion);
            return new ResponseEntity<>(tipoModificacion, HttpStatus.OK);
        } catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build()
                    ,HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @GetMapping("/listAllTipoModificacion")
    public ResponseEntity<?> listAllTipoModificacion(){
        List<TipoModificacion> getListTipoModificacion = tipoModificacionService.listAllTipoModificacion();
        if (getListTipoModificacion.isEmpty()){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No hay tipos de modificación")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .object(getListTipoModificacion)
                .build()
                , HttpStatus.OK);
    }

    @PutMapping("/updateTipoModificacion/{idTipoModificacion}")
    public ResponseEntity<?> updateTipoModificacion(@PathVariable Integer idTipoModificacion, @RequestBody TipoModificacionDto tipoModificacionDto){
        try {
            if (tipoModificacionService.existsByIdTipoModificacion(idTipoModificacion)){
                tipoModificacionDto.setIdTipoModificacion(idTipoModificacion);
                TipoModificacion tipoModificacionUpdate = tipoModificacionService.saveTipoModificacion(tipoModificacionDto);
                TipoModificacionDto updateDto = TipoModificacionDto.builder()
                        .idTipoModificacion(tipoModificacionUpdate.getIdTipoModificacion())
                        .modificacion(tipoModificacionUpdate.getModificacion())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Tipo de modificación actualizado correctamente")
                        .object(updateDto)
                        .build()
                        , HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
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
