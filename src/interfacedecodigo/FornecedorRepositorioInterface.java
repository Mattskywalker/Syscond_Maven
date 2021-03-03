package interfacedecodigo;

import java.util.ArrayList;

import exceptions.FornecedorN�oEncontrado;
import pojos.Fornecedor;

public interface FornecedorRepositorioInterface {
	
	public Fornecedor procurar(String id) throws FornecedorN�oEncontrado;
	public Fornecedor procurar(Fornecedor fornecedor) throws FornecedorN�oEncontrado;
    public void adicionar(Fornecedor fornecedor);
    public void remover(Fornecedor fornecedor);
    public ArrayList<Fornecedor> listar();
    public void alterar(Fornecedor fornecedor) throws FornecedorN�oEncontrado;
	
}
