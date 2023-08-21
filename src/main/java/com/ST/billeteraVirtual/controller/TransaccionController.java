package com.ST.billeteraVirtual.controller;

import com.ST.billeteraVirtual.entities.Billetera;
import com.ST.billeteraVirtual.entities.Transaccion;
import com.ST.billeteraVirtual.services.TransaccionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
    @RestController
    @RequestMapping("/api/v1/transacciones")
    public class TransaccionController {

        @Autowired
        public TransaccionServiceImpl servicio;

        @GetMapping("")
        public ResponseEntity<?> getAll(){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
            }
        }
        @GetMapping("/{id}")
        public ResponseEntity<?> getOne(@PathVariable Long id){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
            }
        }
        @PostMapping("")
        public ResponseEntity<?> save(@RequestBody Transaccion entity){
            try{
           /*    Transaccion nuevo =new Transaccion();
                Long idBilleteraDestino = entity.getBilleteraDestino().getId();
                Double montoDestino= servicio.depositar(idBilleteraDestino, entity.getMontoMonedaDestino(), entity.getMonedaDestino());
                nuevo.setMontoMonedaDestino(montoDestino);*/
                return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity));
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, la acción no ha sido posible.\"}");
            }
        }
        @PutMapping("/{id}")
        public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Transaccion entity){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, entity));
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
            }
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete (@PathVariable Long id) {
            try {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id));
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
            }

        }


    }
