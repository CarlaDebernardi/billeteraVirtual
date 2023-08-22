package com.ST.billeteraVirtual.services;

import com.ST.billeteraVirtual.entities.Billetera;
import com.ST.billeteraVirtual.entities.Usuario;
import com.ST.billeteraVirtual.repositories.BilleteraRepository;
import com.ST.billeteraVirtual.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServiceImpl{
    @Autowired
    private BilleteraRepository billeteraRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Transactional
    public List<Usuario> findAll () throws Exception{
        try {
            List<Usuario> entities = usuarioRepository.findAll();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Usuario findById (Long id) throws Exception{
        try {
            Optional<Usuario> entityOptional = usuarioRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Usuario save (Usuario entity) throws Exception{
        try {
            entity = usuarioRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Usuario update (Long id, Usuario entity) throws Exception {
        try {
            Optional<Usuario> entityOptional = usuarioRepository.findById(id);
            Usuario entityUpdate = entityOptional.get();
            entityUpdate.setNombre(entity.getNombre());
            entityUpdate.setApellido(entity.getApellido());
            entityUpdate.setSexo(entity.getSexo());
            entityUpdate.setDni(entity.getDni());
            entityUpdate.setTelefono(entity.getTelefono());
            entityUpdate.setEmail(entity.getEmail());
            entityUpdate.setSaldoTotal(entity.getSaldoTotal());
            entityUpdate.setBilleteras(entity.getBilleteras());

            /*for (Billetera billetera: nuevas) {
                billeteraRepository.save(billetera);
            }
            entityUpdate.setBilleteras(nuevas);*/
            usuarioRepository.save(entityUpdate);
            return entityUpdate;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
    @Transactional
    public boolean delete(Long id) throws Exception{
        try{
            if (usuarioRepository.existsById(id)){
                usuarioRepository.deleteById(id);
                return true;
            }

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return false;
    }

   /* public Double calcularSaldoTotal (Long id){

   */
}
