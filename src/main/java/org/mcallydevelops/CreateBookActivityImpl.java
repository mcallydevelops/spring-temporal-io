package org.mcallydevelops;

import org.mcallydevelops.models.Book;
import org.springframework.stereotype.Component;


@Component
public class CreateBookActivityImpl implements CreateBookActivity {
    @Override
    public Book createBook(String title) {
        return new Book(title);
//        throw new RuntimeException();
    }
}
