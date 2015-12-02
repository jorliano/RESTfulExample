package br.com.jortec.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "cliente")
@XmlRootElement
public class Cliente  implements Serializable                                                                                                                                                                                        {	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long id;	
	private String login;
	private String senha;
	private String nome;
	private String email;
	private String telefone;
	private String Cidade;
	private String Sexo;
    
    
   /* @OneToMany(mappedBy="cliente", targetEntity= Emergencia.class, 
    		fetch = FetchType.LAZY, cascade = CascadeType.ALL)      
    private List<Emergencia> emergencia;
    */
   
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}
    
  /*	public List<Emergencia> getEmergencia() {
		return emergencia;
	}

	public void setEmergencia(List<Emergencia> emergencia) {
		this.emergencia = emergencia;
	}*/
	
	
}
