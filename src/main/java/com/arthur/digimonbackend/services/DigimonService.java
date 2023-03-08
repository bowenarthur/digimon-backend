package com.arthur.digimonbackend.services;

import com.arthur.digimonbackend.models.DigimonModel;
import com.arthur.digimonbackend.repositories.DigimonRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigimonService {
    final DigimonRepository digimonRepository;

    public DigimonService(DigimonRepository digimonRepository) {
        this.digimonRepository = digimonRepository;
    }

    public String save(List<DigimonModel> digimons) {
        for (DigimonModel digimon : digimons) {
            digimonRepository.save(digimon);
        }
        return "Digimons salvos com sucesso!";
    }

    public Page<DigimonModel> findAll(Pageable pageable) {
        return digimonRepository.findAll(pageable);
    }

    public Page<DigimonModel> findByLevel(String level, Pageable pageable) {
        return digimonRepository.findByLevel(pageable, level);
    }

    public Page<DigimonModel> search(DigimonModel digimon, Pageable pageable) {
        String name = digimon.getName();
        String level = digimon.getLevel();
        if (name != null && level != null) {
            return digimonRepository.findByNameContainingIgnoreCaseAndLevel(pageable, digimon.getName(),
                    digimon.getLevel());
        }
        return digimonRepository.findByNameContainingIgnoreCase(pageable, name);
    }
}
