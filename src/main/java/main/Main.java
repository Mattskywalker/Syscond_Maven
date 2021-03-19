package main;

import dao.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import exceptions.fornecedor.FornecedorNaoEncontrado;
import pojos.Fornecedor;
import repositorios.FornecedorDAO;
import repositorios.FornecedorDaoInterface;

import java.util.List;

public class Main {

    public static void main(String[] args) throws FornecedorNaoEncontrado {

        Fornecedor fornecedor = new Fornecedor("1234","Breno","indefinido");
        Fornecedor fornecedor1 = new Fornecedor("12345","Luana","indefinido");

        FornecedorDaoInterface fornecedorDao = new FornecedorDAO();

        //fornecedorDao.adicionar(fornecedor);
        try {
            System.out.println("Fornecedor procurado: " + fornecedorDao.procurar("1235").getNome());
        }catch (NullPointerException e){
            System.err.println("ERRO: EITA, PORRA DO CARALHO, AGORA FUDEU, FILHA DA PUTA");
        }



        System.out.println("Lista de fornecedores: ");

        for (Fornecedor a: fornecedorDao.listar()) {

            System.out.println("Fornecedor: " + a.getNome());
        }

        System.out.println("Fornecedor encontrado: " + fornecedorDao.procurar("123").getNome());

        fornecedorDao.remover(fornecedor);

        fornecedorDao.alterar(new Fornecedor("12345","Luana Martins de Andrade Lima","indefinido"));

        System.out.println("Lista de fornecedores: ");
        for (Fornecedor a: fornecedorDao.listar()) {

            System.out.println("Fornecedor: " + a.getNome());
        }


    }


}
