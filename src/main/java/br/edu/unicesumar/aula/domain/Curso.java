package br.edu.unicesumar.aula.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private Long cargaHoraria;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @OneToOne
    @JoinColumn(name = "ementa_id")
    private Ementa ementa;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany
    @JoinColumn(name = "curso_id")
    private List<Aula> aulas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "curso_alunos", joinColumns = {
            @JoinColumn(name = "curso_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "aluno_id")
    })
    private List<Aluno> alunos = new ArrayList<>();

}
