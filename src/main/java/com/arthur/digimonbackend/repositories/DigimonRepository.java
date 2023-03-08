package com.arthur.digimonbackend.repositories;

import com.arthur.digimonbackend.models.DigimonModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigimonRepository extends JpaRepository<DigimonModel, String> {
    Page<DigimonModel> findByLevel(Pageable pageable, String level);

    Page<DigimonModel> findByNameContainingIgnoreCase(Pageable pageable, String name);

    Page<DigimonModel> findByNameContainingIgnoreCaseAndLevel(Pageable pageable, String name, String level);
}
