package com.arthur.digimonbackend.controllers;

import com.arthur.digimonbackend.dtos.DigimonDto;
import com.arthur.digimonbackend.models.DigimonModel;
import com.arthur.digimonbackend.services.DigimonService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/digimon")
public class DigimonController {
    final DigimonService digimonService;

    public DigimonController(DigimonService digimonService) {
        this.digimonService = digimonService;
    }

    @GetMapping
    public ResponseEntity<Page<DigimonModel>> getAllDigimons(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(digimonService.findAll(pageable));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<Page<DigimonModel>> getDigimonsByLevel(@PathVariable(value = "level") String level,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(digimonService.findByLevel(level, pageable));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<DigimonModel>> searchDigimons(@RequestBody @Valid DigimonDto digimonDto,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        var digimonModel = new DigimonModel();
        BeanUtils.copyProperties(digimonDto, digimonModel);
        return ResponseEntity.status(HttpStatus.OK).body(digimonService.search(digimonModel, pageable));
    }

    @GetMapping("/populate")
    public ResponseEntity<String> populateDatabase() {
        var digimons = getDigimonsFromAPI();
        saveDigimons(digimons);
        return ResponseEntity.status(HttpStatus.OK).body("Banco de dados populado");
    }

    private List<DigimonModel> getDigimonsFromAPI() {
        String url = "https://digimon-api.vercel.app/api/digimon";
        RestTemplate restTemplate = new RestTemplate();
        DigimonModel[] digimons = restTemplate.getForObject(url, DigimonModel[].class);
        return Arrays.asList(digimons);
    }

    private ResponseEntity<Object> saveDigimons(@RequestBody @Valid List<DigimonModel> digimons) {
        return ResponseEntity.status(HttpStatus.CREATED).body(digimonService.save(digimons));
    }
}
