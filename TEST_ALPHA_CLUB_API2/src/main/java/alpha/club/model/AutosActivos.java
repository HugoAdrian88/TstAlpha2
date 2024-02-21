package alpha.club.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//
//cada automóvil tiene sus propiedades 
//(color, placas, id_cliente, id_chip, marca, modelo, fecha de alta, fecha de actualización, activo). 
//
//import javax.persistence.*; // for Spring Boot 2
import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "autos")
public class AutosActivos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_auto;

	public long getId_auto() {
		return id_auto;
	}


	public void setId_auto(long id_auto) {
		this.id_auto = id_auto;
	}



	@Column(name = "placas")
	private String placas;
	
	@Column(name = "id_cliente")
	private Integer id_cliente;

	@Column(name = "id_chip")
	private Integer id_chip;
	
	@Column(name = "cnombre")
	private String cnombre;
	
	@Column(name = "activo")
	private boolean activo;
	
	@Column(name = "club")
	private Integer club;
	

	public Integer getClub() {
		return club;
	}


	public void setClub(Integer club) {
		this.club = club;
	}


	/*
	@Column(name = "nombre")
	private String nombre;
	*/
	/*

*/
	public AutosActivos() {

	}
	/*
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	    @JoinTable(name = "clientes",
	        joinColumns = @JoinColumn(name = "idcliente")
	        ,inverseJoinColumns = @JoinColumn(name = "id")
	    )*/
	
	    //private List<AutosActivos> autos = new ArrayList<>();
		//private Set<AutosActivos> autos = new HashSet<>();

	
	
/*
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	
*/

	public Integer getId_chip() {
		return id_chip;
	}


	public void setId_chip(Integer id_chip) {
		this.id_chip = id_chip;
	}
	
	public String getCnombre() {
		return cnombre;
	}


	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}
	
	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	


	/*
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	*/
	public String getPlacas() {
		return placas;
	}


	public void setPlacas(String placas) {
		this.placas = placas;
	}


	public Integer getId_cliente() {
		return id_cliente;
	}


	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}


/*

	*/

	public AutosActivos(long id_auto, Integer id_chip, String cnombre, String placas, Integer id_cliente, boolean activo, Integer club) {
		this.id_cliente = id_cliente;
		this.id_chip = id_chip;
		this.id_auto = id_auto;
		this.placas = placas;
		this.activo = activo;
		this.cnombre = cnombre;
		this.club = club;
	}



	@Override
	public String toString() {
		//return "Auto [id=" + id + ", id_cliente=" + id_cliente + ", id_chip=" + id_chip + ", activo=" + activo + ", marca=" + marca + ", modelo=" + modelo + ", placas=" + placas + ", color=" + color + "]";
		return "Auto [id=  id_chip=" + id_chip + ", ]";
	}
}