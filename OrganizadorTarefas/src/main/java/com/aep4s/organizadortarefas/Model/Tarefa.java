/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.aep4s.organizadortarefas.Model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Usuário
 */
@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "criada")
    private LocalDate datacriada;
    @Column(name = "vencimento")
    private String vence;
    @Column(name = "prioridade")
    private int prioridade;

    public Tarefa() {
    }

    public Tarefa(int id, String titulo, String descricao, LocalDate datacriada, String vence, int prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.datacriada = datacriada;
        this.vence = vence;
        this.prioridade = prioridade;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDatacriada() {
        return datacriada;
    }

    public void setDatacriada(LocalDate datacriada) {
        this.datacriada = datacriada;
    }

    public String getVence() {
        return vence;
    }

    public void setVence(String vence) {
        this.vence = vence;
    }

    @Override
    public String toString() {
        return "Tarefa{\n" + "titulo = " + titulo + ", \ndescricao = " + descricao + ", \ndatacriada=" + datacriada + ",\nvence = " + vence + "\n}";
    }
    
    
}
