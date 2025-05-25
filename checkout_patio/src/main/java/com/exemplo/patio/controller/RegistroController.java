package com.exemplo.patio.controller;

import com.exemplo.patio.dto.RegistroDTO;
import com.exemplo.patio.model.Registro;
import com.exemplo.patio.repository.RegistroRepository;
import com.exemplo.patio.service.RegistroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    private final RegistroService service;
    private final RegistroRepository repository;

    public RegistroController(RegistroService service, RegistroRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/checkin")
    public Registro checkIn(@RequestBody @Valid RegistroDTO dto) {
        return service.checkIn(dto);
    }

    @PostMapping("/checkout")
    public Registro checkOut(@RequestBody @Valid RegistroDTO dto) {
        return service.checkOut(dto);
    }

    @GetMapping
    public Page<Registro> listar(@RequestParam String placa, Pageable pageable) {
        return service.listar(placa, pageable);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}