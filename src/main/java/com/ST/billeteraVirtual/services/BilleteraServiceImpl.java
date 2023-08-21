package com.ST.billeteraVirtual.services;

import com.ST.billeteraVirtual.entities.Billetera;
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
            nueva.setNombre(entity.getNombre());
            nueva.setARS(entity.getARS());
            nueva.setBTC(entity.getBTC());
            nueva.setETH(entity.getETH());
            nueva.setTransaccion(entity.getTransaccion());
            nueva.setSaldo(calcularSaldo(entity.getARS(), entity.getBTC(), entity.getETH()));

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
            entityUpdate.setNombre(entity.getNombre());
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
