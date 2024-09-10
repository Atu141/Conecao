package br.com.fiap.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.model.Usuario;
import br.com.fiap.repository.Database;


@WebServlet("/NovoUsuarioServlet")
public class NovoUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario(nome, email, tel, senha);
		
		Database.adiciona(usuario);
		
		request.setAttribute("usuario", usuario);
		response.sendRedirect("listaUsuarios");
		
	}
}

