package com.pluralsight.bookstore.repositories;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Date;


@RunWith(Arquillian.class)
public class BookRepositoryTest {

    @Inject
    private BookRepository bookRepository;
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void createBook() throws Exception{
        // test bookrepository
        assert(Long.valueOf(0).equals(bookRepository.countBooks()));
        assert(0 == bookRepository.findAllBook().size());

        // create a book

        Book book = new Book("isbn","a title", 12F, 123, Language.ENGLISH, new Date(), "http://blahblah", "description");
        Long bookId = book.getId();

        assert (bookId != null);

        Book book1 = bookRepository.findOneBook(bookId);

        assert ("a title".equals(book1.getTitle()));

        assert(Long.valueOf(1).equals(bookRepository.countBooks()));
        assert(1 == bookRepository.findAllBook().size());
    }
}
