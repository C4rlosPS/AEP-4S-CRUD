/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aep4s.organizadortarefas.Model.Controller;

import com.aep4s.organizadortarefas.Model.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
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

    public boolean login(String email, char[] password) {
        try {
            entityManager.getTransaction().begin();

            // Crie a consulta HQL para buscar a Pessoa com o email fornecido
            String hql = "FROM Pessoa p WHERE p.email = :email";
            TypedQuery<Pessoa> query = entityManager.createQuery(hql, Pessoa.class);
            query.setParameter("email", email);

            // Obtenha o resultado da consulta
            Pessoa pessoa = query.getSingleResult();

            // Verifique se a Pessoa foi encontrada e se a senha corresponde
            if (pessoa != null && pessoa.getSenha().equals(password)) {
                JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Falha no login. Verifique as credenciais.");
                return false;
            }
        } catch (NoResultException ex) {
            // Trate a exceção caso a entidade não seja encontrada
            JOptionPane.showMessageDialog(null, "Email não encontrado. Verifique as credenciais.");
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

}
