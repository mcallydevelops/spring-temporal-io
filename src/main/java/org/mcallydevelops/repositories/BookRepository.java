package org.mcallydevelops.repositories;

import org.mcallydevelops.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
