package br.com.jortec.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.jortec.dao.ClienteDao;
import br.com.jortec.dao.EmergenciaDao;
import br.com.jortec.dao.UsuarioDao;
import br.com.jortec.model.Cliente;
import br.com.jortec.model.Emergencia;
import br.com.jortec.model.Usuario;

import com.google.gson.Gson;

@Path("/ClienteWeb")
public class ClienteWeb {	
	//Log4j
		final  Logger logger = Logger.getLogger(ClienteService.class);
		Gson gson = new Gson();
		
	Usuario us;
	UsuarioDao dao = new UsuarioDao();
   
	@POST			
	@Consumes(value = { "application/json", "application/x-www-form-urlencoded" })  
	@Produces("application/json")
	@Path("/logar")
	public Usuario logar(@FormParam("logar") String dados) {		
		logger.info("metdo logar chamado "+dados);			
			
		try{	
			JSONObject js = new JSONObject(dados);			
			
			//Pega os valores e compara	                
			 us = dao.buscaPor(js.getString("Login"),js.getString("Senha"));			 
			 if(us != null){
				 logger.info("Retornou o usuario");
				 return us;
			 }
			 else{
				 return new Usuario();				
			 }
		}catch(Exception e){
			logger.info("erro ao logar "+e.getMessage());
		}
		return new Usuario();	
	}	
	
	
	@POST
	@Consumes(value = { "application/json", "application/x-www-form-urlencoded" })
	@Path("/cadastrar")
	public String cadastrar(@FormParam("cadastrar") String dados) {
		// TODO return proper representation object
		logger.info("mettodo cadastro chamado" + dados);

		try {
			JSONObject js = new JSONObject(dados);
			us = new Usuario();
			us.setNome(js.getString("Nome"));
			us.setEmail(js.getString("Email"));
			us.setLogin(js.getString("Login"));
			us.setSenha(js.getString("Senha"));

			if (us != null) {
				logger.info("Cliente tem dados " + us.getNome());
				// Pega os valores e compara				
				Usuario usuario = dao.buscaPorLoginNome(us.getLogin(), us.getNome());
				if (usuario == null) {
					// salva o usuario no banco de dados
					dao.salvar(us);
					logger.info("Dados do cadastro salvo");

					return "Usuario salvo com sucesso";
				}

				return "Usuario já existe";
			} else {
				return "dados inconpativeis";
			}
		} catch (Exception e) {
			logger.info("erro ao carregar json do cadastro " + e.getMessage());
		}

		return "falhou";

	}
	
	@POST
	@Consumes(value = { "application/json", "application/x-www-form-urlencoded" })
	@Path("/editar")
	public String editar(@FormParam("editar") String dados) {
		// TODO return proper representation object
		logger.info("mettodo cadastro chamado" + dados);

		try {
			JSONObject js = new JSONObject(dados);
			us = new Usuario();
			us.setNome(js.getString("Nome"));
			us.setEmail(js.getString("Email"));
			us.setLogin(js.getString("Login"));
			us.setSenha(js.getString("Senha"));

			if (us != null) {
				logger.info("Cliente tem dados " + us.getNome());
				// salva o usuario no banco de dados
				dao.editar(us);
				logger.info("Dados do cadastro salvo");

				return "Usuario editado com sucesso";
				
			} else {
				return "dados inconpativeis";
			}
		} catch (Exception e) {
			logger.info("erro ao carregar json do cadastro " + e.getMessage());
		}

		return "falhou";

	}
	
	
	@GET
	@Path("/deletar/{id}")
	@Produces("text/plain")
	public String removeUsuario(@PathParam("id") long id) {
		logger.info("metodo deletar usuario chamado "+id);
		Usuario us = dao.buscaPorId(id);
		dao.deleta(us);
		return "Deletado com sucesso";
	}
	
	
	@GET
	@Path("/deletarCliente/{id}")
	@Produces("text/plain")
	public String removeCliente(@PathParam("id") long id) {
		Cliente c = new ClienteDao().buscaPorId(id);
		new ClienteDao().deletar(c);
		return "Deletado com sucesso";
	}
	
	@GET
	@Path("/deletarEmergencia/{id}")
	@Produces("text/plain")
	public String removeEmergencia(@PathParam("id") long id) {
		Emergencia em = new EmergenciaDao().buscaPorId(id);
		new EmergenciaDao().deletar(em);
		return "Deletado com sucesso";
	}
	
	
	@GET
	@Produces("application/json")	
	@Path("/listarUsuarios")
	public List<Usuario> listarUsuarios() {
		logger.info("Metodo listar usuarios chamado ");
		return new UsuarioDao().listar();
	}	
	
	@GET	
	@Produces("application/json")	
	@Path("/listarClientes")
	public List<Cliente> listarClientes() {
		logger.info("Metodo listar clientes chamado ");
		return new ClienteDao().listar();
	}
	
	@GET	
	@Produces("application/json")	
	@Path("/listarEmergencias")
	public List<Emergencia> listarEmergencias() {
		logger.info("Metodo listar ocorrencias chamado ");
		return new EmergenciaDao().listar();
	}
	
}
