package br.com.fiap.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Usuario;

public class Database {

	private static List<Usuario> lista = new ArrayList<>();
	private static int id = 1;

	public static void adiciona(Usuario usuario) {
		usuario.setId(id++);
		lista.add(usuario);
	}

	public static Usuario buscaUsuarios() {
		for (Usuario usuario : lista) {
			if (usuario.getId() == id) {
				return usuario;
			}
		}
		return null;
	}

	public static List<Usuario> getUsuarios() {
		return lista;
	}
}
