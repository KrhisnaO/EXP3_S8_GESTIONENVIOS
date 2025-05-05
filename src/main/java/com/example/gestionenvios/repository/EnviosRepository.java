package com.example.gestionenvios.repository;

import com.example.gestionenvios.model.Envios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnviosRepository extends JpaRepository<Envios, Long>{
  
}

 