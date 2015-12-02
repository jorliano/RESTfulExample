package br.com.jortec.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.jortec.dao.ClienteDao;
import br.com.jortec.dao.UsuarioDao;
import br.com.jortec.model.Cliente;
import br.com.jortec.model.Usuario;

@Path("/teste")
public class Teste {
	
	@GET	
	@Produces("text/plain")
	public String removeUsuario() {
		ClienteDao dao = new ClienteDao();
		
		Cliente cliente = new Cliente();
		cliente.setNome("jorliano");
		cliente.setEmail("jorliano@hotmail.com");
		cliente.setLogin("jorliano");		
		cliente.setSenha("leandro");	
		cliente.setCidade("Cucaia");
		cliente.setTelefone("99999999");
		Cliente c = dao.buscaPorLoginNome(cliente.getLogin(), cliente.getNome());
		
		 if(c == null){
			 //salva o cliente no banco de dados		        
		      dao.salvar(cliente);
		     	
		      
		      return "WebService instalado corretamente \n Para testar o banco foi adicionado um cliente \n"
				+ " login: jorliano senha: leandro \n faça um teste na app do android ";			 
		 }			
	      
		 return "Usuario ja existe";
		
		
	}
	
	
  
}
