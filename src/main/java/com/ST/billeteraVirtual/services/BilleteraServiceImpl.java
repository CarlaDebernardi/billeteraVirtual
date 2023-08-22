package com.ST.billeteraVirtual.services;

import com.ST.billeteraVirtual.entities.Billetera;
import com.ST.billeteraVirtual.entities.Transaccion;
import com.ST.billeteraVirtual.repositories.BilleteraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BilleteraServiceImpl {

    @Autowired
    private BilleteraRepository billeteraRepository;
    @Autowired
    public TransaccionServiceImpl transaccionService;

    /*public Double calcularSaldo(Long id) {
        Optional<Billetera> respuesta = billeteraRepository.findById(id);
        if (respuesta.isPresent()) {
            Billetera b = respuesta.get();
            Double saldoEnARS = b.getARS();
            Double saldoEnBTC = b.getBTC();
            Double saldoEnETH = b.getETH();

            Double saldo = (saldoEnARS + saldoEnBTC + saldoEnETH);
            b.setSaldo(saldo);
            billeteraRepository.save(b);
            return b.getSaldo();
        }
        return 0.0;
    }*/
    public Double calcularSaldo(Double saldoARS, Double saldoBTC, Double saldoETH) {
        Double saldoTotal = (saldoARS + (saldoBTC * 100) + (saldoETH * 20));
        return saldoTotal;
    }

    @Transactional
    public List<Billetera> findAll() throws Exception {
        try {
            List<Billetera> entities = billeteraRepository.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Billetera findById(Long id) throws Exception {
        try {
            Optional<Billetera> entityOptional = billeteraRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Billetera save(Billetera entity) throws Exception {
        try {

            Billetera nueva = new Billetera();
            Double saldoTotal= nueva.getSaldo();
            nueva.setARS(entity.getARS());
            nueva.setBTC(entity.getBTC());
            nueva.setETH(entity.getETH());
            nueva.setSaldo(calcularSaldo(entity.getARS(), entity.getBTC(), entity.getETH()));
            if (entity.getTransaccion().isEmpty()) {
                nueva.setTransaccion(entity.getTransaccion());
            }else {
                List<Transaccion> nuevas = entity.getTransaccion();
                for (Transaccion transaccion : nuevas) {
                    Double saldoSumar = transaccionService.depositar(entity.getId(), transaccion.getMontoMonedaDestino(), transaccion.getMonedaDestino());

                    saldoTotal = saldoTotal + saldoSumar;
                    billeteraRepository.save(nueva);
                }
            }
            nueva.setSaldo(saldoTotal);

            billeteraRepository.save(nueva);
            return nueva;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public Billetera update(Long id, Billetera entity) throws Exception {
        try {
            Optional<Billetera> entityOptional = billeteraRepository.findById(id);
            Billetera entityUpdate = entityOptional.get();
            entityUpdate.setBTC(entity.getBTC());
            entityUpdate.setETH(entity.getETH());
            entityUpdate.setARS(entity.getARS());
            entityUpdate.setSaldo(calcularSaldo(entity.getARS(), entity.getBTC(), entity.getETH()));
            entityUpdate.setTransaccion(entity.getTransaccion());
            billeteraRepository.save(entityUpdate);
            return entityUpdate;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            if (billeteraRepository.existsById(id)) {
                billeteraRepository.deleteById(id);
                return true;
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }

}
