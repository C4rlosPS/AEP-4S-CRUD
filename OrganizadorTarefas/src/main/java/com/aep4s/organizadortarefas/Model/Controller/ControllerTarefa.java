/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aep4s.organizadortarefas.Model.Controller;

import com.aep4s.organizadortarefas.Model.Tarefa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuário
 */
public class ControllerTarefa {

    private static ControllerTarefa instance;
    protected EntityManager entityManager;

    public static ControllerTarefa getInstance() {
        if (instance == null) {
            instance = new ControllerTarefa();
        }
        return instance;
    }

    private ControllerTarefa() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_exemplo");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    //neste metodo criamos um stirng sql para fazer a pesquisa por "nome" no banco de dads
    public List<Tarefa> pesquisarNome(String titulo) {
        entityManager.getTransaction().begin();

        String hql = "select t from tarefa t where t.titulo = :titulo";

        Query query = entityManager.createQuery(hql);

        query.setParameter("nome", titulo); // define que o parametro para pesquisa e o nome

        List<Tarefa> nomeLista = query.getResultList();

        entityManager.getTransaction().commit(); // commit da transacao sempre deve ter

        return nomeLista;
    }

    public List<Tarefa> listarTarefas() {

        entityManager.getTransaction().begin();

        String hql = "select t from Tarefa t"; // Seleciona a entidade completa Tarefa

        Query query = entityManager.createQuery(hql);

        List<Tarefa> tarefaLista = query.getResultList();

        entityManager.getTransaction().commit();

        return tarefaLista;
    }



public void inserirTarefa(Tarefa tarefa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(tarefa);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        JOptionPane.showMessageDialog(null, "Tarefa cadastrada com Sucesso");
    }

    public void updateTarefa(Tarefa tarefa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(tarefa); //  merge para atualizar a entidade
            entityManager.getTransaction().commit();
            
            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso");

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteTarefa(String titulo) {
    try {
        entityManager.getTransaction().begin();

        // Crie uma consulta personalizada para encontrar a entidade com base no título
        Query query = entityManager.createQuery("SELECT t FROM Tarefa t WHERE t.titulo = :titulo");
        query.setParameter("titulo", titulo);
        Tarefa tarefa = (Tarefa) query.getSingleResult();

        if (tarefa != null) {
            entityManager.remove(tarefa);
            JOptionPane.showMessageDialog(null, "Deletado com Sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Tarefa não encontrada");
        }

        entityManager.getTransaction().commit();
    } catch (NoResultException ex) {
        // Trate a exceção caso a entidade não seja encontrada
        JOptionPane.showMessageDialog(null, "Tarefa não encontrada");
        entityManager.getTransaction().rollback();
    } catch (Exception ex) {
        ex.printStackTrace();
        entityManager.getTransaction().rollback();
    }
}

}
