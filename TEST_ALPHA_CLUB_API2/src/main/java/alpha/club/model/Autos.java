package alpha.club.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

//
//cada automóvil tiene sus propiedades 
//(color, placas, id_cliente, id_chip, marca, modelo, fecha de alta, fecha de actualización, activo). 
//
//import javax.persistence.*; // for Spring Boot 2
import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "autos")
public class Autos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_auto;

	@Column(name = "color")
	private String color;

	@Column(name = "placas")
	private String placas;
	
	@Column(name = "cliente")
	private long cliente;
	
	@Column(name = "id_chip")
	private Integer id_chip;
	
	@Column(name = "marca")
	private String marca;

	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "fecha_alta")
	private Date fecha_alta;

	@Column(name = "fecha_actualizacion")
	private Date fecha_actualizacion;

	@Column(name = "activo")
	private boolean activo;
	/*
	 @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      },
		      mappedBy = "autos")
		  @JsonIgnore
		  private Set<Clientes> clientes = new HashSet<>();
*/
	public Autos() {

	}
	/*
	  public Set<Clientes> getClientes() {
		    return clientes;
		  }

		  public void setClientes(Set<Clientes> clientes) {
		    this.clientes = clientes;
		  }  
*/
	
	

	public long getId_auto() {
		return id_auto;
	}





	public void setId_auto(long id_auto) {
		this.id_auto = id_auto;
	}





	public String getColor() {
		return color;
	}





	public void setColor(String color) {
		this.color = color;
	}





	public String getPlacas() {
		return placas;
	}





	public void setPlacas(String placas) {
		this.placas = placas;
	}





	public long getCliente() {
		return cliente;
	}





	public void setCliente(long cliente) {
		this.cliente = cliente;
	}





	public Integer getId_chip() {
		return id_chip;
	}





	public void setId_chip(Integer id_chip) {
		this.id_chip = id_chip;
	}





	public String getMarca() {
		return marca;
	}





	public void setMarca(String marca) {
		this.marca = marca;
	}





	public String getModelo() {
		return modelo;
	}





	public void setModelo(String modelo) {
		this.modelo = modelo;
	}





	public Date getFecha_alta() {
		return fecha_alta;
	}





	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}





	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}





	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}





	public boolean isActivo() {
		return activo;
	}





	public void setActivo(boolean activo) {
		this.activo = activo;
	}





	public Autos(long id_auto, long cliente, Integer id_chip,  String marca, String modelo, String placas, String color, Date fecha_alta, Date fecha_actualizacion, boolean activo) {
		this.id_auto = id_auto;
		this.id_chip = id_chip;
		this.marca = marca;
		this.modelo = modelo;
		this.placas = placas;
		this.color = color;
		this.activo = activo;
		this.fecha_alta = fecha_alta;
		this.fecha_actualizacion = fecha_actualizacion;
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		return "Auto [ id_auto=" + id_auto + ", id_chip=" + id_chip + ", activo=" + activo + ", marca=" + marca + ", modelo=" + modelo + ", placas=" + placas + ", color=" + color + "]";
	}
}