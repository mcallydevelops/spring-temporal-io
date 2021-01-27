package org.mcallydevelops;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.mcallydevelops.models.Book;

@ActivityInterface
public interface SaveBookActivity {
    @ActivityMethod
    Book save(Book book);
}
