package br.com.jortec.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;





import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.exolab.castor.types.DateTime;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Cliente;
import br.com.jortec.model.Emergencia;
import br.com.jortec.model.Usuario;
import br.com.jortec.servico.HttpConnection;
import br.com.jortec.servico.ImagemValidator;
import br.com.jortec.util.Alerta;

@Controller
@Scope("request")
public class EmergenciaBean {
    
	Emergencia emergencia = new Emergencia();
	private List<Emergencia> lista = new ArrayList<Emergencia>();	    
   
	// Log4j
	final Logger logger = Logger.getLogger(ClienteBean.class);
 
	
	@Autowired
	UsuarioLogado usuarioLogado;

	@Autowired
	Alerta alerta;		
	
	public String chamaFotos(){			
		
		return "fotos";
	}
	
	public String deletar() {
		String jsonDados = new HttpConnection()
		.getGetHttp("http://localhost:8080/RESTfulExample/rest/ClienteWeb/deletarEmergencia/"+emergencia.getId());
		
		logger.info("url para deletar :"+"http://localhost:8080/RESTfulExample/rest/ClienteWeb/deletarEmergencia/"+emergencia.getId());
		alerta.info(jsonDados, true);
		return "emergencia?faces-redirect=true";
	}


	public void busca() {
		// listaDoVendedor = dao.buscaDoVendedorPorNome(cliente.getNome(),
		// usuarioLogado.getVendedor().getId());
	}	
	public Emergencia getEmergencia() {
		return emergencia;
	}

	public void setEmergencia(Emergencia emergencia) {
		this.emergencia = emergencia;
	}

	public List<Emergencia> getLista() {
		if(!lista.isEmpty()){
			return lista;
		}else{
			return listarDados();
		}
		
	}

	public void setLista(List<Emergencia> lista) {
		this.lista = lista;
	}  			
	
	public List listarDados() {

		String jsonDados = new HttpConnection()
				.getGetHttp("http://localhost:8080/RESTfulExample/rest/ClienteWeb/listarEmergencias");
		logger.info("Dados recebidos " + jsonDados);

		try {
			JSONArray jsonArray = new JSONArray(jsonDados);
			logger.info("tamanho do array "+jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());	
				emergencia = new Emergencia();
				emergencia.setId(jsonObject.getLong("id"));
				emergencia.setLongitude(jsonObject.getString("longitude"));
				emergencia.setLatitude(jsonObject.getString("latitude"));
				emergencia.setTipo(jsonObject.getString("tipo"));						
				emergencia.setData(jsonObject.getString("data"));		
				emergencia.setHora(jsonObject.getString("hora"));
				emergencia.setUrlFoto1(ImagemValidator.caregarImagem(jsonObject.getString("foto"),  "primeiro"+emergencia.getId()+".jpg"));						
				emergencia.setUrlFoto2(ImagemValidator.caregarImagem(jsonObject.getString("foto2"), "segundo" +emergencia.getId()+".jpg"));				
				emergencia.setUrlFoto3(ImagemValidator.caregarImagem(jsonObject.getString("foto3"), "terceiro"+emergencia.getId()+".jpg"));
				
				
				JSONObject jsCliente = new JSONObject(jsonObject.getJSONObject("cliente").toString());
				Cliente c = new Cliente();
				c.setId(jsCliente.getLong("id"));
				c.setNome(jsCliente.getString("nome"));
				emergencia.setCliente(c);
				
				
				lista.add(emergencia);				
				
			}
		} catch (JSONException e) {
			logger.info("erro ao carregar dados dos clientes " + e.getMessage());
		}		        
		
		return lista;
	}

	
	
}
