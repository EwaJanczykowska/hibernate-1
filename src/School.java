import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="schools")
public class School implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@Column
	private String address;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="school_id")
	private Set<SchoolClass> schoolClassSet;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Set<SchoolClass> getSchoolClasses() {
		return schoolClassSet;
	}
	
	public void setSchoolClasses(Set<SchoolClass> schoolClassSet) {
		this.schoolClassSet = schoolClassSet;
	}
	
	public String toString() {
		return "School: " + getName() + " (" + getAddress() + ")";
	}

}
