package br.com.jortec.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Cliente;
import br.com.jortec.model.Usuario;
import br.com.jortec.servico.HttpConnection;
import br.com.jortec.util.Alerta;

@Controller
@Scope("request")
public class ClienteBean implements Serializable {
	// Log4j
	final Logger logger = Logger.getLogger(ClienteBean.class);

	Cliente cliente = new Cliente();
	private List<Cliente> lista = new ArrayList<Cliente>();

	@Autowired
	UsuarioLogado usuarioLogado;

	@Autowired
	Alerta alerta;

	public void busca() {
		// listaDoVendedor = dao.buscaDoVendedorPorNome(cliente.getNome(),
		// usuarioLogado.getVendedor().getId());
	}

	public String deletar() {
		String jsonDados = new HttpConnection()
		.getGetHttp("http://localhost:8080/RESTfulExample/rest/ClienteWeb/deletarCliente/"+cliente.getId());
		
		alerta.info(jsonDados, true);
		return "usuario";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getLista() {
		if (!lista.isEmpty()) {
			return lista;
		} else {
			return listarDados();
		}
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public List listarDados() {

		String jsonDados = new HttpConnection()
				.getGetHttp("http://localhost:8080/RESTfulExample/rest/ClienteWeb/listarClientes");
		logger.info("Dados recebidos " + jsonDados);

		try {
			JSONArray jsonArray = new JSONArray(jsonDados);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = new JSONObject(jsonArray.get(i)
						.toString());
				Cliente c = new Cliente();
				c.setId(jsonObject.getInt("id"));
				c.setNome(jsonObject.getString("nome"));
				c.setLogin(jsonObject.getString("login"));
				c.setEmail(jsonObject.getString("email"));
				c.setTelefone(jsonObject.getString("telefone"));
				c.setCidade(jsonObject.getString("cidade"));
				lista.add(c);

			}
		} catch (JSONException e) {
			logger.info("erro ao carregar dados dos clientes " + e.getMessage());
		}

		return lista;
	}
}
