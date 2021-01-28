package org.mcallydevelops;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.mcallydevelops.models.Book;

import java.time.Duration;

public class BookWorkflowImpl implements BookWorkflow {

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
//            .setMaximumAttempts(500)
            .build();
    private final ActivityOptions options = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            // Optionally provide customized RetryOptions.
            // Temporal retries failures by default, this is simply an example.
            .setRetryOptions(retryoptions)
            .build();

    // ActivityStubs enable calls to Activities as if they are local methods, but actually perform an RPC.
    private final CreateBookActivity createBookActivity = Workflow.newActivityStub(CreateBookActivity.class, options);
    private final SaveBookActivity saveBookActivity = Workflow.newActivityStub(SaveBookActivity.class, options);

    @Override
    public Book addBook(String title) {
        Book book = createBookActivity.createBook(title);
        return saveBookActivity.save(book);
    }
}
