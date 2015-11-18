package br.com.jortec.model;

import br.com.jortec.dao.UsuarioDao;

public class Teste {
  public static void main(String[] args) {
	Usuario u  = new Usuario();
	u.setNome("jorliano");
	u.setEmail("jorliano@hotmail.com");
	u.setLogin("jorliano");
	u.setSenha("leandro");
   
	new UsuarioDao().salvar(u);
	
	Usuario us  = new Usuario();
	us.setNome("leandro");
	us.setEmail("jorliano@hotmail.com");
	us.setLogin("leandro");
	us.setSenha("jorliano");
   
	new UsuarioDao().salvar(us);
}
}
