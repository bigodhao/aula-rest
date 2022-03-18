package br.edu.unicesumar.aula.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.unicesumar.aula.domain.Curso;
import br.edu.unicesumar.aula.domain.Pessoa;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	@Query(nativeQuery = true, value = "select * from curso where lower(nome) like '%' || lower(:parteNome) || '%' ")
	Page<Curso> findByNome(@Param("parteNome") String parteNome, Pageable pageable);

	Page<Curso> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);

	List<Curso> findByNome(String nome);

	List<Curso> findByDescricao(String descricao);

	List<Pessoa> findByNomeAndDescricao(String nome, String descricao);

	Pessoa findOneByNome(String nome);

	Boolean existsByNome(String nome);
	
}
