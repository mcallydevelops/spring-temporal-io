package org.mcallydevelops;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.mcallydevelops.models.Book;

@WorkflowInterface
public interface BookWorkflow {
    @WorkflowMethod
    Book addBook(String bookTitle);

}
