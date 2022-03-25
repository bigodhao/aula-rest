package br.edu.unicesumar.aula.controller;

<<<<<<< HEAD
import java.util.List;

import javax.websocket.server.PathParam;

=======
>>>>>>> dbda0ef0f48312d7101da7a5b0b34d8b2b82d6c8
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.aula.domain.Curso;
import br.edu.unicesumar.aula.domain.Pessoa;
import br.edu.unicesumar.aula.service.CursoService;
import br.edu.unicesumar.aula.service.PessoaService;
=======
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.aula.domain.Curso;
import br.edu.unicesumar.aula.repository.CursoRepository;
>>>>>>> dbda0ef0f48312d7101da7a5b0b34d8b2b82d6c8

@RestController
@RequestMapping("/curso")
public class CursoController {
    
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/{id}")
    public Curso buscarPorId(@PathVariable(name = "id") Long id) {
        return this.cursoRepository.findById(id).orElse(null);
    }

    @GetMapping
    public Page<Curso> buscarTodosOsCursos(Pageable pageable) {
        return this.cursoRepository.findAll(pageable);
    }

    @PostMapping
    public Curso criarNovoCurso(@RequestBody Curso curso) {
        return this.cursoRepository.save(curso);
    }

    @DeleteMapping("/{id}")
    public void deletarCurso(@PathVariable(name = "id") Long id) {
        this.cursoRepository.deleteById(id);
    }
 
    @PutMapping("/{id}")
    public Curso alterarCurso(@PathVariable(name = "id") Long id, @RequestBody Curso curso) {
        Curso cursoBancoDeDados = this.cursoRepository.findById(id).orElse(null);

        cursoBancoDeDados.setCargaHoraria(curso.getCargaHoraria());
        cursoBancoDeDados.setDataFim(curso.getDataFim());
        cursoBancoDeDados.setDescricao(curso.getDescricao());
        cursoBancoDeDados.setNome(curso.getNome());
        cursoBancoDeDados.setDataInicio(curso.getDataInicio());

        return this.cursoRepository.save(cursoBancoDeDados);
    }

}
