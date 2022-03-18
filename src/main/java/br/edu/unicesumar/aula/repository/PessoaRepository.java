package br.edu.unicesumar.aula.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.unicesumar.aula.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query(nativeQuery = true, value = "select * from pessoa where nome_completo like %:parteNome% ")
	Page<Pessoa> findByNomeQueryNative(@Param("parteNome") String parteNome, Pageable pageable);

	@Query(value = "select p from Pessoa p where p.nome like %:parteNome%" )
	Page<Pessoa> findByNomeHQL(@Param("parteNome") String parteNome, Pageable pageable);

	Page<Pessoa> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);

	List<Pessoa> findByNome(String nome);

	List<Pessoa> findByCpf(String cpf);

	List<Pessoa> findByNomeAndCpf(String nome, String cpf);

	Pessoa findOneByNome(String nome);

	Boolean existsByNome(String nome);




}
