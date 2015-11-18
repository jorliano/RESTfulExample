package br.com.jortec.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Encoded;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "emergencia")
@XmlRootElement
public class Emergencia {
   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)		
	private int id;
	private String tipo;
	private String longitude;
	private String latitude;
	@Lob @Basic(fetch = FetchType.LAZY)	
	@Column(length=16777215)
	private byte[] foto;
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=16777215)
	private byte[] foto2;
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=16777215)
	private byte[] foto3;
	private Date data;
	private String hora;
	
	@ManyToOne	
	@JoinColumn(name=" id_cliente", nullable=false, insertable=true, updatable=true)
	private Cliente cliente;
 
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}	

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	

	public byte[] getFoto2() {
		return foto2;
	}

	public void setFoto2(byte[] foto2) {
		this.foto2 = foto2;
	}

	public byte[] getFoto3() {
		return foto3;
	}

	public void setFoto3(byte[] foto3) {
		this.foto3 = foto3;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	
}
