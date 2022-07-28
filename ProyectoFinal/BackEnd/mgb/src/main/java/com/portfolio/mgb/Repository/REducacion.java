/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.mgb.Repository;

import com.portfolio.mgb.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tomi
 */
public interface REducacion extends JpaRepository<Educacion, Integer>{
    public Optional <Educacion> findByNombreEd(String nombreEd); 
   public boolean existsByNombreEd(String nombreEd);
}
