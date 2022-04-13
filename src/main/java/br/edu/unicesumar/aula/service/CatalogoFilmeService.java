package br.edu.unicesumar.aula.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.aula.domain.CatalogoFilme;
import br.edu.unicesumar.aula.repository.CatalogoFilmeRepository;
import lombok.Setter;

@Service
@Setter
public class CatalogoFilmeService {
    
    @Autowired
    private CatalogoFilmeRepository catalogoFilmeRepository;

    public Page<CatalogoFilme> buscarTodos(Pageable pageable) {
        return this.catalogoFilmeRepository.findAll(pageable);
    }

    public Optional<CatalogoFilme> buscarPorId(Long id) {
        return this.catalogoFilmeRepository.findById(id);
    }

    public CatalogoFilme salvar(CatalogoFilme novoCatalogoFilme) {
        return this.catalogoFilmeRepository.save(novoCatalogoFilme);
    }

    public Optional<CatalogoFilme> atualizar(CatalogoFilme catalogoFilmeExistente) {
        if(catalogoFilmeRepository.existsById(catalogoFilmeExistente.getId())) {
            CatalogoFilme catalogoFilmeAtualizado = this.catalogoFilmeRepository.save(catalogoFilmeExistente);
            return Optional.of(catalogoFilmeAtualizado);
        }
        return Optional.empty();
    }

    public void excluirPorId(Long id) {
        this.catalogoFilmeRepository.deleteById(id);
    }

    public void excluirTodos() {
        this.catalogoFilmeRepository.deleteAll();
    }

    public boolean existePorId(Long id) {
        return this.catalogoFilmeRepository.existsById(id);
    }

}
