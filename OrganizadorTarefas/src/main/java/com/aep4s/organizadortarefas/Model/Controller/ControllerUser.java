/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aep4s.organizadortarefas.Model.Controller;

import com.aep4s.organizadortarefas.Model.Pessoa;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuário
 */
public class ControllerUser {

    private static ControllerUser instance;
    protected EntityManager entityManager;

    public static ControllerUser getInstance() {
        if (instance == null) {
            instance = new ControllerUser();
        }
        return instance;
    }

    private ControllerUser() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_exemplo");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public void inserirPessoa(Pessoa pessoa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pessoa);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updatePessoa(Pessoa user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user); //  merge para atualizar a entidade
            entityManager.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso");

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void deletePessoa(int id) {
        try {
            entityManager.getTransaction().begin();
            Pessoa pessoa = entityManager.find(Pessoa.class, id);
            if (pessoa != null) {
                entityManager.remove(pessoa);
            }
            JOptionPane.showMessageDialog(null, "Deletado com Sucesso!");
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<Pessoa> login(String email, String password) {
        entityManager.getTransaction().begin();

        // Criando a consulta HQL para buscar a Pessoa com o email
        String hql = "FROM Pessoa p WHERE p.email = :email";
        Query query = entityManager.createQuery(hql, Pessoa.class);
        query.setParameter("email", email);

        // Obtendo o resultado da consulta
        List<Pessoa> pessoas = query.getResultList();

        entityManager.getTransaction().commit();

        for (Pessoa pessoa : pessoas) {
            // Verificar a senha (considerando que a senha está em texto claro no exemplo)
            if (pessoa.getSenha().equals(password)) {
               
                return Collections.singletonList(pessoa);
            }
        }

 
        return Collections.emptyList();
    }

}
