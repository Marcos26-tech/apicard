package br.com.fiap.resource.dao;

import java.util.List;

import br.com.fiap.resource.to.UltimaCeiaTO;

public interface UltimaCeiaInterface {

	public List<UltimaCeiaTO> listarTodos();
	public UltimaCeiaTO listarPorId(int id);
	public void inserir(UltimaCeiaTO uct);
	public void editar(UltimaCeiaTO uct);
	public void votar(int id);

}
