package br.com.jortec.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import br.com.jortec.util.Alerta;

@Controller
@Scope("request")
public class SistemaBean implements Serializable {

	
	private List list = new ArrayList();
	
		
	@Autowired
	Alerta alerta;

	@PostConstruct
	public void load() {

		//lista
	}

	
}
