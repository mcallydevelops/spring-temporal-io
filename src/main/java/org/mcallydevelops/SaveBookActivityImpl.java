package org.mcallydevelops;

import org.mcallydevelops.models.Book;
import org.mcallydevelops.repositories.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveBookActivityImpl implements SaveBookActivity{
    private final BookRepository bookRepository;

    public SaveBookActivityImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
