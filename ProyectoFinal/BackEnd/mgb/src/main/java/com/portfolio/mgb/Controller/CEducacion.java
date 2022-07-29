/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mgb.Controller;

import com.portfolio.mgb.Dto.dtoEducacion;
import com.portfolio.mgb.Entity.Educacion;
import com.portfolio.mgb.Security.Controller.Mensaje;
import com.portfolio.mgb.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educa")
@CrossOrigin(origins = "http://localhost:4200")
public class CEducacion {
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/lista")
    public ResponseEntity<List<CEducacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoed){
        if(StringUtils.isBlank(dtoed.getNombreEd()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(sEducacion.existsByNombreEd(dtoed.getNombreEd()))
            return new ResponseEntity(new Mensaje("Esa estudio existe"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = new Educacion(dtoed.getNombreEd(), dtoed.getDescripcionEd());
        sEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("Estudio agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/(id)")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoed){
        //Validamos si existe el ID
       if(sEducacion.existsById(id))
           return new ResponseEntity (new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
     
       if(sEducacion.existsByNombreEd(dtoed.getNombreEd()) && sEducacion.getByNombreEd(dtoed.getNombreEd()).get().getId() != id)
           return new ResponseEntity(new Mensaje("Ese estudio ya existe"), HttpStatus.BAD_REQUEST);
       
       //No puede estar vacio
       if(StringUtils.isBlank(dtoed.getNombreEd()))
           return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
       
       Educacion educacion = sEducacion.getOne(id).get();
       educacion.setNombreEd(dtoed.getNombreEd());
       educacion.setDescripcionEd((dtoed.getDescripcionEd()));
       
       sEducacion.save(educacion);
       return new ResponseEntity(new Mensaje("Estudio actualizado"), HttpStatus.OK);
    }
    
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //Validamos si existe el ID
       if(sEducacion.existsById(id))
           return new ResponseEntity (new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
       
       sEducacion.delete(id);
       
       return new ResponseEntity(new Mensaje("Estudio eliminado"), HttpStatus.OK);
       
    }
}
