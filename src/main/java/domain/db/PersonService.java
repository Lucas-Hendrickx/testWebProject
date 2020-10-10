package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
	private Map<String, Person> persons = new HashMap<>();
	
	public PersonService () {
		Person administrator = new Person("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
		Person klant1 = new Person("r0748690", "r0748690@ucll.be", "P@ssw0rd", "Lucas", "Hendrickx");
		Person klant2 = new Person("r0754629", "r0754629@ucll.be", "dr0wss@P", "Oliver", "Fuentes Bühler");
		Person klant3 = new Person("r0849791", "r0849791@ucll.be", "JellyBeanzz", "Stephan", "Fuentes Bühler");
		add(administrator);
		add(klant1);
		add(klant2);
		add(klant3);
	}
	
	public Person get(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		return persons.get(personId);
	}
	
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if (persons.containsKey(person.getUserid())) {
			throw new DbException("User already exists");
		}
		persons.put(person.getUserid(), person);
	}
	
	public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}
	
	public void delete(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		persons.remove(personId);
	}

	public int getNumberOfPersons() {
		return persons.size();
	}
}
