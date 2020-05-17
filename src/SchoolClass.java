import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="schoolClasses")
public class SchoolClass implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private int startYear;
	
	@Column
	private int currentYear;
	
	@Column
	private String profile;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="class_id")
	private Set<Student> studentsSet;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public Set<Student> getStudentsSet() {
		return studentsSet;
	}

	public void setStudentsSet(Set<Student> studentsSet) {
		this.studentsSet = studentsSet;
	}

	public String toString() {
		return "Class: " + profile + " (Started: " + getStartYear() + ", Current year: " + getCurrentYear() + ")";
	}
}