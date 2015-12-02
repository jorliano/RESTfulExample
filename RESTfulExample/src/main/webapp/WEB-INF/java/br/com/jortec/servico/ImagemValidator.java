package br.com.jortec.servico;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.jortec.controller.ClienteBean;
import br.com.jortec.model.Emergencia;
import br.com.jortec.util.Alerta;


public class ImagemValidator  {
	// Log4j
	static Logger logger = Logger.getLogger(ClienteBean.class);

	public static String caregarImagem(String dados, String caminho) {
		logger.info("caregarImagem  chamado "+dados);
		try {
			if (dados.length() > 10) {				

				// Converte a string em bytes
				byte[] imagem = new sun.misc.BASE64Decoder().decodeBuffer(dados);					
				
				ServletContext servletContext = (ServletContext) FacesContext
						.getCurrentInstance().getExternalContext()
						.getContext();

				FileOutputStream outPut = new FileOutputStream(
						servletContext.getRealPath("") + caminho);
				outPut.write(imagem);
				outPut.flush();
				outPut.close();
				logger.info("Dados da imagem gravdaos "+servletContext.getRealPath("") + caminho);
				return caminho;
			}else{
				logger.info("Fundo branco chamado "+caminho);
				return "imagens/fundo_branco.jpg";
			}
		} catch (FileNotFoundException e) {
			logger.info("erro FileNotFoundException "+e.getMessage());
		} catch (IOException e) {
			logger.info("erro IOException "+e.getMessage());
		}
		return "imagens/fundo_branco.jpg";
	}

	
}
