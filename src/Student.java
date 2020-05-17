import java.io.Serializable;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name="students")
public class Student implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@Column
	private String surname;
	
	@Column
	private String pesel;
	
	public Student (String name, String surname, String pesel) {
		this.name=name;
		this.surname=surname;
		this.pesel=pesel;
	}
	
	//required by Hibernate
	private Student() {
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPesel() {
		return pesel;
	}

	@Override
	public String toString() {
		return name + " " + surname + " (" + pesel + ")";
	}
	

}
