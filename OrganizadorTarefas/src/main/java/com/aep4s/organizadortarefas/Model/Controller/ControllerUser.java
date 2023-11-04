/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aep4s.organizadortarefas.Model.Controller;

import com.aep4s.organizadortarefas.Model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

    public void inserirUsuario(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com Sucesso!");

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        
    }

    public void updatePessoa(User user) {
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
            User pessoa = entityManager.find(User.class, id);
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
}
