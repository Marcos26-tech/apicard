package br.com.fiap.resource.bo;

import java.util.List;

import br.com.fiap.resource.dao.UltimaCeiaDAO;
import br.com.fiap.resource.dao.UltimaCeiaInterface;
import br.com.fiap.resource.to.UltimaCeiaTO;

public class UltimaCeiaBO implements UltimaCeiaInterface {

	private UltimaCeiaDAO ucdao = new UltimaCeiaDAO();

	@Override
	public List<UltimaCeiaTO> listarTodos() {
		return ucdao.listarTodos();
	}

	@Override
	public UltimaCeiaTO listarPorId(int id) {
		return ucdao.listarPorId(id);
	}

	@Override
	public void inserir(UltimaCeiaTO ucto) {
		ucdao.inserir(ucto);
	}

	@Override
	public void editar(UltimaCeiaTO uct) {
		var receitaById = validaUltimaCeia(uct, ucdao.listarPorId(uct.getId()));
		ucdao.editar(receitaById);
	}

	public void votar(int id) {
		ucdao.votar(id);
	}
	public List<UltimaCeiaTO> buscarOrdemVotacao(){
		return ucdao.buscarOrdemVotacao();
	}

	private UltimaCeiaTO validaUltimaCeia(UltimaCeiaTO request, UltimaCeiaTO receitaDoBanco) {
		var ceia = new UltimaCeiaTO();
		ceia.setId(request.getId());
		if (request.getNomePrato() == null) {
			ceia.setNomePrato(receitaDoBanco.getNomePrato());
		} else {
			ceia.setNomePrato(request.getNomePrato());
		}
		if (request.getReceita() == null) {
			ceia.setReceita(receitaDoBanco.getReceita());
		} else {
			ceia.setReceita(request.getReceita());
		}
		if (request.getKcal() == null) {	
			ceia.setKcal(receitaDoBanco.getKcal());
		} else {
			ceia.setKcal(request.getKcal());
		}
		if( request.getKcal() == 0) {
			ceia.setKcal(receitaDoBanco.getKcal());
		}
		return ceia;
	}

}
