package br.edu.unicesumar.aula.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.aula.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Page<Pessoa> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);

}
