package br.com.jortec.model;

import java.util.Date;

public class Emergencia {
  
	private long id;
	private String longitude;
	private String latitude;
	private String tipo;
	private String data;
	private String hora;
	private Cliente cliente;
	private byte[] foto;
	private byte[] foto2;
	private byte[] foto3;
	private String urlFoto1;
	private String urlFoto2;
	private String urlFoto3;	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getUrlFoto1() {
		return urlFoto1;
	}
	public void setUrlFoto1(String urlFoto1) {
		this.urlFoto1 = urlFoto1;
	}
	public String getUrlFoto2() {
		return urlFoto2;
	}
	public void setUrlFoto2(String urlFoto2) {
		this.urlFoto2 = urlFoto2;
	}
	public String getUrlFoto3() {
		return urlFoto3;
	}
	public void setUrlFoto3(String urlFoto3) {
		this.urlFoto3 = urlFoto3;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}