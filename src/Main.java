import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		main.addNewData();
		main.printSchools();
		main.close();
	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}

	private void printSchools() {
		Criteria crit = session.createCriteria(School.class);
		List<School> schools = crit.list();

		System.out.println("### Schools");
		for (School s : schools) {
			System.out.println(s);
			for (SchoolClass sc : s.getSchoolClasses()) {
				System.out.println("   " + sc);
				System.out.println("   >  Students: ");
				for (Student st : sc.getStudentsSet()) {
					System.out.println("          " + st);
				}
			}
		}
	}
	
	//odpowiedz dp pkt.5 w cwiczeniu 4.: Wywo³anie za ka¿dym razem tej metody spowoduje utworzenie kolejnej kopii szko³y (w tym klas i uczniów) w bazie danych.
	private void addNewData() {
		Student stud1 = new Student("Ewa","Kowalska", "87020309345");
		Student stud2 = new Student("Anna", "Nowak", "84030103456");
		Student stud3 = new Student("Karolina", "Stefañczyk", "82091203456");
		Student stud4 = new Student("Joanna", "Józefiak", "90063004399");
		
		SchoolClass cl1 = new SchoolClass();
		cl1.setStartYear(2007);
		cl1.setCurrentYear(3);
		cl1.setProfile("budownictwo");
		Set<Student> clSet1 = new HashSet<>();
		clSet1.add(stud1);
		clSet1.add(stud2);
		cl1.setStudentsSet(clSet1);
		
		SchoolClass cl2 = new SchoolClass();
		cl2.setStartYear(2012);
		cl2.setCurrentYear(4);
		cl2.setProfile("marketing");
		Set<Student> clSet2 = new HashSet<>();
		clSet2.add(stud3);
		clSet2.add(stud4);
		cl2.setStudentsSet(clSet2);
		
		
		School sch = new School();
		sch.setName("PK");
		sch.setAddress("ul. Warszawska 24");
		Set<SchoolClass> schSet = new HashSet<>();
		schSet.add(cl1);
		schSet.add(cl2);
		sch.setSchoolClasses(schSet);
		
		Transaction transaction = session.beginTransaction();
		session.save(sch);
		transaction.commit();
		
	}
}
