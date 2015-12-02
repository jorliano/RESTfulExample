package br.com.jortec.servico;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.jortec.controller.ClienteBean;
import br.com.jortec.model.Usuario;

@Service
@Scope("request")
public class AutenticaLogin {
	final Logger logger = Logger.getLogger(ClienteBean.class);
	Gson gson = new Gson();

	public Usuario autenticaLogUsuario(String login, String senha) {
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);		
		try{
			String resposta = new HttpConnection().getPostHttp(
					"http://localhost:8080/RESTfulExample/rest/ClienteWeb/logar",
					"logar", gson.toJson(usuario));
			
			logger.info("autenticaLogUsuario chamado " + resposta);			
			
			JSONObject jo = new JSONObject(resposta);
			usuario.setId(jo.getLong("id"));
			usuario.setNome(jo.getString("nome"));
						
			
		}catch(Exception e){
			logger.info("aerro ao se autenticar " + e.getMessage());
		}
		
		if (usuario.getId() > 0) {
			return usuario;
		}
		else
			return null;
	}
          
}
