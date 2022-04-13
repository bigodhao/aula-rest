package br.edu.unicesumar.aula.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.aula.domain.CatalogoFilme;
import br.edu.unicesumar.aula.service.CatalogoFilmeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/catalogo-filme")
public class CatalogoFilmeController {

    @Autowired
    private CatalogoFilmeService catalogoFilmeService;

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoFilme> buscarPorId(@PathVariable(name = "id") Long id) {
        Optional<CatalogoFilme> catalogoFilmeOptional = this.catalogoFilmeService.buscarPorId(id);

        if (catalogoFilmeOptional.isPresent()) {
            return ResponseEntity.ok(catalogoFilmeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<CatalogoFilme>> buscarTodosPaginado(Pageable pageable) {
        return ResponseEntity.ok(this.catalogoFilmeService.buscarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<CatalogoFilme> criarNovoCatalogoFilme(@Valid @RequestBody CatalogoFilme catalogoFilme) throws URISyntaxException {
        CatalogoFilme catalogoFilmeCriado = this.catalogoFilmeService.salvar(catalogoFilme);
        return ResponseEntity.created(new URI("/catalogo-filme/"+catalogoFilmeCriado.getId())).body(catalogoFilmeCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogoFilme> criarNovoCatalogoFilme(@PathVariable(name = "id") Long id,
            @RequestBody CatalogoFilme catalogoFilme) {

        if (!id.equals(catalogoFilme.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<CatalogoFilme> catalogoFilmeAtualizadoOptional = this.catalogoFilmeService.atualizar(catalogoFilme);

        if (catalogoFilmeAtualizadoOptional.isPresent()) {
            return ResponseEntity.ok(catalogoFilmeAtualizadoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<Void> excluirTodos() {
        this.catalogoFilmeService.excluirTodos();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable(name = "id") Long id) {
        if (this.catalogoFilmeService.existePorId(id)) {
            this.catalogoFilmeService.excluirPorId(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
