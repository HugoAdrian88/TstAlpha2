package alpha.club.model;

import java.sql.Date;

//
//cada automóvil tiene sus propiedades 
//(color, placas, id_cliente, id_chip, marca, modelo, fecha de alta, fecha de actualización, activo). 
//
//import javax.persistence.*; // for Spring Boot 2
import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "clubes")
public class Clubes {
	//(id, club, nombre_club , direccion)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "club")
	private Integer club;
	
	@Column(name = "nombre_club")
	private String nombre_club;
	
	@Column(name = "direccion")
	private String direccion;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getClub() {
		return club;
	}

	public void setClub(Integer club) {
		this.club = club;
	}

	public String getNombre_club() {
		return nombre_club;
	}

	public void setNombre_club(String nombre_club) {
		this.nombre_club = nombre_club;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Clubes() {

	}

	public Clubes( Integer club,  String nombre_club, String direccion) {
		this.club = club;
		this.nombre_club = nombre_club;
		this.direccion = direccion;
		
	}

	@Override
	public String toString() {
		return "Clubes [id=" + id + ", club=" + club + ", nombre_club=" + nombre_club + ", direccion=" + direccion + "]";
	}
}