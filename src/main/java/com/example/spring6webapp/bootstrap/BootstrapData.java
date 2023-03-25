package com.example.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.spring6webapp.domain.Author;
import com.example.spring6webapp.domain.Book;
import com.example.spring6webapp.domain.Publisher;
import com.example.spring6webapp.repositories.AuthorRepository;
import com.example.spring6webapp.repositories.BookRepository;
import com.example.spring6webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		Author eric = new Author();
		eric.setFirstName("Eric");
		eric.setLastName("Evans");
		
		Book ddd = new Book();
		ddd.setTitle("Domain Driven Design");
		ddd.setIsbn("123456");
		
		Publisher pub = new Publisher();
		pub.setPublisherName("Random House");
		pub.setAddress("123 Main Street");
		pub.setCity("Denver");
		pub.setState("CO");
		pub.setZip("01234");
		
		Author ericSaved = authorRepository.save(eric);
		Book dddSaved = bookRepository.save(ddd);
		publisherRepository.save(pub);
		
		Author rod = new Author();
		rod.setFirstName("Rod");
		rod.setLastName("Johnson");
		
		Publisher pub2 = new Publisher();
		pub2.setPublisherName("Cengage");
		pub2.setAddress("456 City Street");
		pub2.setCity("New York");
		pub2.setState("NY");
		pub2.setZip("98765");
		
		Book noEJB = new Book();
		noEJB.setTitle("J2EE Development without EJB");
		noEJB.setIsbn("54757585");
		
		Author rodSaved = authorRepository.save(rod);
		Book noEJBSaved = bookRepository.save(noEJB);
		publisherRepository.save(pub2);
		
		ericSaved.getBooks().add(dddSaved);
		rodSaved.getBooks().add(noEJBSaved);
		
		authorRepository.save(ericSaved);
		authorRepository.save(rodSaved);
		
		System.out.println("In Bootstrap");
		System.out.println("Author Count: " + authorRepository.count());
		System.out.println("Book Count: " + bookRepository.count());
		System.out.println("Publisher Count: " + publisherRepository.count());
	}

}
