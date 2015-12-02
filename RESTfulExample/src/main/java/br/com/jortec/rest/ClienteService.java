package br.com.jortec.rest;

import java.awt.CardLayout;
import java.util.Date;

import javax.management.StringValueExp;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.jortec.dao.ClienteDao;
import br.com.jortec.dao.EmergenciaDao;
import br.com.jortec.model.Cliente;
import br.com.jortec.model.Emergencia;

@Path("/ClienteService")
public class ClienteService {
	
	ClienteDao dao = new ClienteDao();
	Cliente cliente;
	Emergencia emergencia;
	EmergenciaDao emDao;
	JSONObject js;
	//Log4j
	final  Logger logger = Logger.getLogger(ClienteService.class);
	
	
	@POST			
	@Consumes(value = { "application/json", "application/x-www-form-urlencoded" })  
	@Produces("text/plan")
	//@Path("/logar")
	public String logar(@FormParam("logar") String logar) {		
		logger.info("metdo logar chamado "+logar);			
					
		Gson g = new Gson();				
		cliente = g.fromJson(logar, Cliente.class);	
		
		if(cliente != null){
			 logger.info("Cliente tem dados ");
			//Pega os valores e compara	                
			 Cliente c = dao.buscaPor(cliente.getLogin(), cliente.getSenha());
			 if(c != null){
				  logger.info("cliente logado");			      
			      
			      return String.valueOf( c.getId());					 
			 }			
		      
			 return "Login ou senha incorreto";
		 }
		 else{
			 return "dados inconpativeis";
		 }
					
		
	}	
	
	@POST		
	@Produces("text/plan")
	@Consumes(value = { "application/json", "application/x-www-form-urlencoded" }) 
	@Path("/cadastrar")
    public String cadastrar(@FormParam("cadastrar") String dados)  {
        //TODO return proper representation object
		logger.info("mettodo cadastro chamado"+dados);
		
		 Gson g = new Gson();				
		 cliente = g.fromJson(dados, Cliente.class);        		
		
		 if(cliente != null){
			 logger.info("Cliente tem dados ");
			//Pega os valores e compara	                
			 Cliente c = dao.buscaPorLoginNome(cliente.getLogin(), cliente.getNome());
			 if(c == null){
				 //salva o cliente no banco de dados		        
			      dao.salvar(cliente);
			      logger.info("Dados do cadastro salvo");		
			      
			      cliente = dao.buscaPor(cliente.getLogin(), cliente.getSenha());		      			      
			      return String.valueOf( cliente.getId());					 
			 }			
		      
			 return "Usuario já existe";
		 }
		 else{
			 return "dados inconpativeis";
		 }
    }
	
	
	@POST		
	@Produces("text/plan")
	@Consumes(value = { "application/json", "application/x-www-form-urlencoded" }) 
	@Path("/enviar")
    public String enviar(@FormParam("enviar") String dados) throws JSONException  {
        //TODO return proper representation object
		
		logger.info("mettodo enviar chamdao "+dados);		
		
		emergencia = new Emergencia();
		cliente = new Cliente();
		
		//logger.info("cliente teste tem dados "+cliente.getId());
		
		Gson g = new Gson();				
		emergencia = g.fromJson(dados, Emergencia.class);	
		
		
		JSONObject js = new JSONObject(dados);		
		
		cliente.setId(js.getLong("id"));		
		emergencia.setCliente(cliente);
		emergencia.setData(new Date()); 
		emergencia.setHora(new Date().getHours()+":"+ new Date().getMinutes());
		emergencia.setId(0);
        if(emergencia != null){      	        	        	
        	
    	    //salva e emergencia no banco de dados
            EmergenciaDao emDao = new EmergenciaDao();
            emDao.salvar(emergencia);
            
            return "sucesso";
        }
        
        return "Falhou ao salvar";
    }  	

}