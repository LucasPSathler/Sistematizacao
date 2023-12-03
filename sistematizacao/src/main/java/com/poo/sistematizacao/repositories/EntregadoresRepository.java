package com.poo.sistematizacao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poo.sistematizacao.models.Entregadores;


@Repository
public interface EntregadoresRepository extends JpaRepository<Entregadores, Integer>{}