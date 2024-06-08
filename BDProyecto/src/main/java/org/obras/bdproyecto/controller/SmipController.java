package org.obras.bdproyecto.controller;

import org.obras.bdproyecto.model.dto.SmipDto;
import org.obras.bdproyecto.model.entity.Smip;
import org.obras.bdproyecto.model.entity.Snip;
import org.obras.bdproyecto.model.playload.MensajeResponse;
import org.obras.bdproyecto.service.ISmipService;
import org.obras.bdproyecto.service.ISnipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smip")
@CrossOrigin(origins = "http://localhost:3000")
public class SmipController {
    @Autowired
    private ISmipService smipService;

    @Autowired
    private ISnipService snipService;

    @PostMapping("/saveSmip")
    public ResponseEntity<?> saveSmip(@RequestBody SmipDto smipDto){
        Smip smip_Save = null;
        try {
            smip_Save = smipService.saveSmip(smipDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("SMIP asociado correctamente")
                    .object(SmipDto.builder()
                            .idSmip(smip_Save.getIdSmip())
                            .numeroSmip(smip_Save.getNumeroSmip())
                            .idSnip(smip_Save.getIdSnip())
                            .monto(smip_Save.getMonto())
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

    @GetMapping("/findSmipById/{idSmip}")
    public ResponseEntity<?> findSmipById(@PathVariable Integer idSmip){
        Smip smip = smipService.findSmipById(idSmip);
        if (smip == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("SMIP no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("SMIP encontrado")
                .object(SmipDto.builder()
                        .idSmip(smip.getIdSmip())
                        .numeroSmip(smip.getNumeroSmip())
                        .idSnip(smip.getIdSnip())
                        .monto(smip.getMonto())
                        .build()
                )
                .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/deleteSmip/{idSmip}")
    public ResponseEntity<?> deleteSmip(@PathVariable Integer idSmip){
        Smip smip = smipService.findSmipById(idSmip);
        if (smip == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("SMIP no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        smipService.deleteSmip(smip);
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("SMIP eliminado")
                .object(null)
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("/listAllSmip")
    public ResponseEntity<?> listAllSmip(){
        List<Smip> listSmip = smipService.listAllSmip();
        if (listSmip == null || listSmip.isEmpty()){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No hay SMIPs registrados")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("SMIPs encontrados")
                    .object(listSmip)
                    .build()
                    , HttpStatus.OK);
        }
    }

    @PutMapping("/updateSmip/{idSmip}")
    public ResponseEntity<?> updateSmip(@PathVariable Integer idSmip, @RequestBody SmipDto smipDto) {
        try {
            if (smipService.existsByIdSmip(idSmip)) {
                smipDto.setIdSmip(idSmip);
                Smip smipUpdate = smipService.saveSmip(smipDto);
                SmipDto updatedDto = SmipDto.builder()
                        .idSmip(smipUpdate.getIdSmip())
                        .numeroSmip(smipUpdate.getNumeroSmip())
                        .idSnip(smipUpdate.getIdSnip())
                        .monto(smipUpdate.getMonto())
                        .build();

                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("SMIP actualizado")
                        .object(updatedDto)
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("SMIP no encontrado")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @GetMapping("/projectBySmip/{idSmip}")
    public ResponseEntity<?> getProjectBySmip(@PathVariable Integer idSmip) {
        Smip smip = smipService.findSmipById(idSmip);
        if (smip == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("SMIP no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }

        Snip snip = snipService.findSnipById(smip.getIdSnip());
        if (snip == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Proyecto no encontrado")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Proyecto encontrado")
                .object(snip)
                .build()
                , HttpStatus.OK);
    }

}
