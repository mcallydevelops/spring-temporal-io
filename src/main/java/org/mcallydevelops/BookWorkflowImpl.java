package org.mcallydevelops;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.mcallydevelops.models.Book;
import org.springframework.stereotype.Component;

import java.time.Duration;

public class BookWorkflowImpl implements BookWorkflow {

    RetryOptions retryOptions = RetryOptions.newBuilder()
            .setMaximumAttempts(3)
            .setBackoffCoefficient(1)
            .build();
    ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(2))
            .setRetryOptions(retryOptions)
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
