package hello;

import hello.model.Customer;
import hello.repository.CustomerRepository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

@Configuration
//@ConfigurationProperties(prefix="jdbc")
//@PropertySources (value = {@PropertySource("classpath:application.properties")})
@EnableAutoConfiguration
@ImportResource("classpath:applicationContext.xml")
public class Application {

    public static void main(String[] args) {
    	
    	ConfigurableApplicationContext context = SpringApplication.run(Application.class);
    	//ConfigurableApplicationContext  context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"}); 
    	
        //context.setParent(contextParent);
    	
       
    	
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        
      
        CustomerRepository repository = context.getBean(CustomerRepository.class);

        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));

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

        context.close();
    }

}