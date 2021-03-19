package repositorios;

import dao.JPAUtil;
import exceptions.fornecedor.FornecedorNaoEncontrado;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import pojos.Fornecedor;

import javax.persistence.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;

public class FornecedorDAO implements FornecedorDaoInterface {

    @Override
    public Fornecedor procurar(String cnpj){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Fornecedor retorno = null;
        try {
            tx.begin();
            retorno = em.find(Fornecedor.class,cnpj);
            tx.commit();
            retorno.getNome();

        }catch(NullPointerException e){

            System.err.println("ERRO: " + e.getMessage());

        }finally {
            em.close();
        }


        return retorno;
    }

    @Override
    public void adicionar(Fornecedor fornecedor) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(fornecedor);
            tx.commit();
        }catch (Exception a){
            //a.printStackTrace();
            System.err.println("Id já existente: " + a.getMessage());
        }finally {
            em.close();
        }

    }

    @Override
    public void remover(Fornecedor fornecedor) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        fornecedor = em.merge(fornecedor);
        em.remove(fornecedor);
        tx.commit();
        em.close();

    }

    @Override
    public List<Fornecedor> listar() {

        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("select a from Fornecedor a", Fornecedor.class);

        List<Fornecedor> lista = query.getResultList();
        return lista;
    }

    @Override
    public void alterar(Fornecedor fornecedor) throws FornecedorNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            Fornecedor exemplo = this.procurar(fornecedor.getCnpj());
            exemplo.setCnpj(fornecedor.getCnpj());
            exemplo.setNome(fornecedor.getNome());
            exemplo.setTelefone(fornecedor.getTelefone());

            tx.begin();
            em.merge(exemplo);
            tx.commit();
        }
        catch(Exception e){

            System.err.println("ERRO: " + e.getMessage());
        }finally {
            em.close();

        }
    }
}
