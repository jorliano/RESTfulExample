package br.com.jortec.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.jortec.model.Usuario;
import br.com.jortec.servico.HttpConnection;
import br.com.jortec.servico.ImagemValidator;
import br.com.jortec.util.Alerta;

@Controller
@Scope("request")
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 663627621331088052L;
	// Log4j
	final Logger logger = Logger.getLogger(ClienteBean.class);

	Usuario usuario = new Usuario();
	List<Usuario> lista = new ArrayList<Usuario>();
	private String confirmeSenha;
	Gson gson = new Gson();

	@Autowired
	Alerta alerta;
	
	@Autowired
	UsuarioLogado usuarioLogado;

	public String salvar() {
		String resposta;
		if (usuario.getId() == 0) {
			resposta = new HttpConnection()
					.getPostHttp(
							"http://localhost:8080/RESTfulExample/rest/ClienteWeb/cadastrar",
							"cadastrar", gson.toJson(usuario));
            if(!usuarioLogado.isLogado()){
            	return "/index?faces-redirect=true";
            }
		} else {
			resposta = new HttpConnection()
					.getPostHttp(
							"http://localhost:8080/RESTfulExample/rest/ClienteWeb/editar",
							"editar", gson.toJson(usuario));

		}
		alerta.info(resposta, true);
		
		return "usuario?faces-redirect=true";
	}

	public String deletar() {
		String resposta = new HttpConnection().getGetHttp(
				"http://localhost:8080/RESTfulExample/rest/ClienteWeb/deletar/"+ usuario.getId());
                logger.info("deletar url "+"http://localhost:8080/RESTfulExample/rest/ClienteWeb/deletar/"+ usuario.getId());
		alerta.info(resposta, true);

		return "usuario";
	}

	public void busca() {
		// lista = dao.listarPorNome(usuario.getNome());
	}

	public String edita() {
		return "edita";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLista() {
		if (!lista.isEmpty()) {
			return lista;
		} else {
			return listarDados();
		}
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String getConfirmeSenha() {
		return confirmeSenha;
	}

	public void setConfirmeSenha(String confirmeSenha) {
		this.confirmeSenha = confirmeSenha;
	}

	public List listarDados() {
		// Solicitação ao servidor
		String jsonDados = new HttpConnection()
				.getGetHttp("http://localhost:8080/RESTfulExample/rest/ClienteWeb/listarUsuarios");

		logger.info("JsonRecebido " + jsonDados);

		try {
			JSONArray jsonArray = new JSONArray(jsonDados);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = new JSONObject(jsonArray.get(i)
						.toString());
				Usuario u = new Usuario();
				u.setId(jsonObject.getInt("id"));
				u.setNome(jsonObject.getString("nome"));
				u.setLogin(jsonObject.getString("login"));
				u.setEmail(jsonObject.getString("email"));
				u.setSenha(jsonObject.getString("senha"));
				lista.add(u);

			}
		} catch (JSONException e) {
			logger.info("lista tamanho " + e.getMessage());
		}
		return lista;
	}

}
