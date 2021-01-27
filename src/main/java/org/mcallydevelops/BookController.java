package org.mcallydevelops;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.mcallydevelops.models.Book;
import org.mcallydevelops.repositories.BookRepository;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final WorkflowOptions options;
    private final WorkflowClient client;
    private final BookRepository bookRepository;

    public BookController(WorkflowOptions options, WorkflowClient workflowClient, BookRepository bookRepository) {
        this.options = options;
        this.client = workflowClient;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> get() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @PostMapping
    public Book post(@RequestBody BookRequest request) {

        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        BookWorkflow workflow = client.newWorkflowStub(BookWorkflow.class, options);
        // Synchronously execute the Workflow and wait for the response.

        Book book = workflow.addBook(request.getTitle());
        return book;
    }


}
