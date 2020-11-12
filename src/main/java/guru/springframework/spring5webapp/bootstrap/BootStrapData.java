package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher redHook = new Publisher("Red Hook Publishing Company", "3 Main St", "Seattle", "WA", "98021");
        Publisher orangeBar = new Publisher("Orange Bar Publishing Company", "4 Main St", "Seattle", "WA", "98012");

        publisherRepository.save(redHook);
        publisherRepository.save(orangeBar);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design Principles", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(redHook);
        redHook.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(redHook);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "321546346345");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(orangeBar);
        orangeBar.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(orangeBar);

        System.out.println("Started in bootstrap");
        System.out.println("No of books :" + bookRepository.count());
        System.out.println("No of publishers : " + publisherRepository.count());
        System.out.println("Publisher : " + orangeBar.getName() + " : Number of Books: " + orangeBar.getBooks().size());
        System.out.println("Publisher : " + redHook.getName() + " : Number of Books: " + redHook.getBooks().size());

    }
}
