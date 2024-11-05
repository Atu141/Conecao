package br.com.fiap.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.fiap.dao.CategoriaDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Categoria;

public class CategoriaDAOTeste {

	private CategoriaDAO dao;
	
	@Before
	public void setUP() {
		dao = DAOFactory.getCategoriaDAO();
	}
	
	@Test
	public void testeListarTodas() {
		List<Categoria> lista = dao.listarTodas();
		assertNotNull("A lista de categoria não deve ser nula", lista);
		assertTrue("A lista de categoria deve conter itens", lista.size() > 0);
	}
}
