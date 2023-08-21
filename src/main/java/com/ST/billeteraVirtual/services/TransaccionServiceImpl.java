package com.ST.billeteraVirtual.services;

import com.ST.billeteraVirtual.entities.Billetera;
import com.ST.billeteraVirtual.entities.Transaccion;
import com.ST.billeteraVirtual.repositories.BilleteraRepository;
import com.ST.billeteraVirtual.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class TransaccionServiceImpl{

    @Autowired
    private BilleteraRepository billeteraRepository;
    @Autowired
    private TransaccionRepository transaccionRepository;
    @Transactional
    public List<Transaccion> findAll () throws Exception{
        try {
            List<Transaccion> entities = transaccionRepository.findAll();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Transaccion findById (Long id) throws Exception{
        try {
            Optional<Transaccion> entityOptional = transaccionRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Transaccion save(Transaccion entity) throws Exception{
        try {
            entity = transaccionRepository.save(entity);

            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Transaccion update (Long id, Transaccion entity) throws Exception{
        try {
            Optional<Transaccion> entityOptional = transaccionRepository.findById(id);
            Transaccion entityUpdate = entityOptional.get();
            entityUpdate.setTipoDeOperacion(entity.getTipoDeOperacion());
            entityUpdate.setBilleteraOrigen(entity.getBilleteraOrigen());
            entityUpdate.setBilleteraDestino(entity.getBilleteraDestino());
            entityUpdate.setMonedaOrigen(entity.getMonedaOrigen());
            entityUpdate.setMonedaDestino(entity.getMonedaDestino());
            entityUpdate = transaccionRepository.save(entityUpdate);

            return entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public boolean delete(Long id) throws Exception{
        try{
            if (transaccionRepository.existsById(id)){
                transaccionRepository.deleteById(id);
                return true;
            }

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return false;
    }

    public Transaccion registrarTransaccion(){

        return null;
    }
    public Double depositar(Long idBilleteraDeDestino, Double montoADepositar, String monedaADepositar) {
        Billetera billeteraDestino = null;
        Optional<Billetera> respuesta = billeteraRepository.findById(idBilleteraDeDestino);
        if (respuesta.isPresent()) {
            billeteraDestino = respuesta.get();
            if (monedaADepositar.equalsIgnoreCase("BTC")) {
                Double montoAnterior = billeteraDestino.getBTC();
                Double montoActual = montoADepositar + montoAnterior;
                billeteraDestino.setBTC(montoActual);
                billeteraRepository.save(billeteraDestino);
                return billeteraDestino.getBTC();
            } else if (monedaADepositar.equalsIgnoreCase("ETH")) {
                Double montoAnterior = billeteraDestino.getETH();
                Double montoActual = montoADepositar + montoAnterior;
                billeteraDestino.setETH(montoActual);
                billeteraRepository.save(billeteraDestino);
                return billeteraDestino.getETH();
            } else if (monedaADepositar.equalsIgnoreCase("ARS")) {
                Double montoAnterior = billeteraDestino.getARS();
                Double montoActual = montoADepositar + montoAnterior;
                billeteraDestino.setARS(montoActual);
                billeteraRepository.save(billeteraDestino);
                return billeteraDestino.getARS();
            }
        }
         return null;
    }

    public void transferirBTCaETH(Long id1, Long id2, String m1, String m2, Double m) {
        Double monedaOrigen = null;
        Double monedaDestino;
        Billetera billeteraOrigen = null;
        Billetera billeteraDestino = null;

        Optional<Billetera> respuesta1 = billeteraRepository.findById(id1);
        if (respuesta1.isPresent()) {
            billeteraOrigen = respuesta1.get();
            if (m1 == "BTC") {      /*Envolverlo en una cláusula try catch*/
                monedaOrigen = billeteraOrigen.getBTC();

            }
        }

        Optional<Billetera> respuesta2 = billeteraRepository.findById(id2);
        if (respuesta2.isPresent()) {
            billeteraDestino = respuesta2.get();
            if (m2 == "ETH") {
                monedaDestino = billeteraDestino.getETH();
            }
        }

        Double nuevoValorMonedaOrigen;
        Double nuevoValorMonedaDestino;
        if (m <= monedaOrigen) {
            nuevoValorMonedaDestino = m * 5; /*1BTC = 100 y ETH = 20*/
            nuevoValorMonedaOrigen = monedaOrigen - m;

            billeteraOrigen.setBTC(nuevoValorMonedaOrigen);
            billeteraDestino.setETH(nuevoValorMonedaDestino);

            billeteraRepository.save(billeteraOrigen);
            billeteraRepository.save(billeteraDestino);
            /*Si este método funciona, replicarlo a todas las conversiones*/
        }
    }
}


