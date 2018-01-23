package br.com.fiap.multitenant.controller;

import br.com.fiap.multitenant.model.Estoque;
import br.com.fiap.multitenant.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@RestController("/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping
    public Iterator<Estoque> getAll() {
        return estoqueRepository.findAll().iterator();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Estoque estoque, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(bindingResult.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        estoqueRepository.save(estoque);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
