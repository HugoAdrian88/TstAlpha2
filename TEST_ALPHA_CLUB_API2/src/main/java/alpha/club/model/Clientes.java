package alpha.club.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

//
//cada automóvil tiene sus propiedades 
//(color, placas, id_cliente, id_chip, marca, modelo, fecha de alta, fecha de actualización, activo). 
//
//import javax.persistence.*; // for Spring Boot 2
import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "clientes")
public class Clientes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_cliente;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "club")
	private Integer club;
	
	@Column(name = "fecha_alta")
	private Date fecha_alta;

	@Column(name = "fecha_actualizacion")
	private Date fecha_actualizacion;

	@Column(name = "activo")
	private boolean activo;
	
	@Column(name = "acceso_permitido")
	private boolean acceso_permitido;
	

	/*
	  @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
		  @JoinTable(name = "clientes_autos",
		        joinColumns = { @JoinColumn(name = "clienteid")},
		        inverseJoinColumns = { @JoinColumn(name = "autoid") })
		  private Set<Autos> autos = new HashSet<>();
	  
	  public void addAutos(Autos auto) {
		    this.autos.add(auto);
		    auto.getClientes().add(this);
		  }
		  */
		 
	
		public Clientes() {

	}
		

	public long getId_cliente() {
			return id_cliente;
		}


		public void setId_cliente(long id_cliente) {
			this.id_cliente = id_cliente;
		}


		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public Integer getClub() {
			return club;
		}


		public void setClub(Integer club) {
			this.club = club;
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


		public boolean isAcceso_permitido() {
			return acceso_permitido;
		}


		public void setAcceso_permitido(boolean acceso_permitido) {
			this.acceso_permitido = acceso_permitido;
		}


	public Clientes(Long id_cliente, Integer club,  String nombre, Date fecha_alta, Date fecha_actualizacion, boolean acceso_permitido, boolean activo) {
		this.id_cliente = id_cliente;
		this.club = club;
		this.nombre = nombre;
		this.fecha_alta = fecha_alta;
		this.fecha_actualizacion = fecha_actualizacion;
		this.activo = activo;
		this.acceso_permitido = acceso_permitido;
	}

	@Override
	public String toString() {
		return "Cliente [idcliente=" + id_cliente + ", club=" + club + ", activo=" + activo + ", nombre=" + nombre + ", acceso_permitido=" + acceso_permitido + ", fecha_alta=" + fecha_alta + ", fecha_actualizacion=" + fecha_actualizacion + "]";
	}
}