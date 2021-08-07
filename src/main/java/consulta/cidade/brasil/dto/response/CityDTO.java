package consulta.cidade.brasil.dto.response;

public class CityDTO {

	private Long id;	
	private String name;
	private Integer uf;
	private Integer ibge;
	private String geolocation;
	private String location;

	public CityDTO() {
		super();
	}

	public CityDTO(Long id, String name, Integer uf, Integer ibge, String geolocation, String location) {
		super();
		this.id = id;
		this.name = name;
		this.uf = uf;
		this.ibge = ibge;
		this.geolocation = geolocation;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUf() {
		return uf;
	}

	public void setUf(Integer uf) {
		this.uf = uf;
	}

	public Integer getIbge() {
		return ibge;
	}

	public void setIbge(Integer ibge) {
		this.ibge = ibge;
	}

	public String getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(String geolocation) {
		this.geolocation = geolocation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
