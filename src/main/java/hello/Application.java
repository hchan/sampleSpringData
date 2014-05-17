package hello;

import hello.model.Customer;
import hello.model.Item;
import hello.model.ItemType;
import hello.repository.CustomerRepository;
import hello.repository.ItemRepository;

import java.util.List;
import java.util.PropertyResourceBundle;

import javax.sql.DataSource;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.jdbc.datasource.*;



// https://spring.io/guides/gs/accessing-data-jpa/
public class Application {
	public static boolean USE_INMEMORYDB = false;
	public static void main(String[] args) {
		ConfigurableApplicationContext context = null;
		if (USE_INMEMORYDB) {
			context = SpringApplication.run(MemoryDB.class); 
			// most likley use Hyersonic : org.hibernate.dialect.H2Dialect
		} else {
			context = SpringApplication.run(MySqlDB.class);
			// org.hibernate.dialect.MySQL5Dialect
		}
				
		CustomerRepository repository = context
				.getBean(CustomerRepository.class);
		
		ItemRepository itemRepository = context
				.getBean(ItemRepository.class);

		

		// createCustomers(repository);

		// fetchCustomers(repository);
		createCustomer0(repository, itemRepository);
		Customer customer = repository.findOne(1L);
		
		
		
		customer.setFirstName("HENRY");
		//customer.setItems(null);
		repository.save(customer); // note that the orphans aren't removed
		
		context.close();
	}

	

	private static void createCustomer0(CustomerRepository repository, ItemRepository itemRepository) {
		Customer customer0 = new Customer("Henry", "Chan");
		customer0 = repository.save (customer0);
		Item item0 = new Item((Long) null, ItemType.NONPERISHABLE,
				"Pokemon Cards", 5.2f);
		item0.setCustomerId(customer0.getId());
		itemRepository.save(item0);
	}

	private static void fetchCustomers(CustomerRepository repository) {
		// fetch all customers
		Iterable<Customer> customers = repository.findAll();
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer by ID
		Customer customer = repository.findOne(1L);
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();

		// fetch customers by last name
		List<Customer> bauers = repository.findByLastName("Bauer");
		System.out.println("Customer found with findByLastName('Bauer'):");
		System.out.println("--------------------------------------------");
		for (Customer bauer : bauers) {
			System.out.println(bauer);
		}
	}

	private static void createCustomers(CustomerRepository repository) {
		// save a couple of customers

		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));

	}

}