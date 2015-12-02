package br.com.jortec.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Usuario;
import br.com.jortec.servico.AutenticaLogin;
import br.com.jortec.servico.ImagemValidator;
import br.com.jortec.util.Alerta;

@Controller
@Scope("request")
public class LoginBean {

	private String Login;
	private String Senha;

	@Autowired
	UsuarioLogado usuarioLogado;

	@Autowired
	AutenticaLogin autenticaLogin;

	@Autowired
	Alerta alerta;

	public String logar() {
		Usuario usuario = autenticaLogin.autenticaLogUsuario(Login, Senha);

		if (usuario != null) {
			usuarioLogado.logarUsuario(usuario);
			usuarioLogado.setNomeLogado(usuario.getNome());
			
			alerta.info("Seja Bem vindo "+usuario.getNome(),true);
			return "sistema?faces-redirect=true";
		}

		alerta.error("Usuario ou Senha incorreto");
		return null;
	}
	

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

}
