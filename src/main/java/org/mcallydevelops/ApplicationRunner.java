package org.mcallydevelops;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.mcallydevelops.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {


    private final BookRepository bookRepository;

    public ApplicationRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        // Create a Worker factory that can be used to create Workers that poll specific Task Queues.
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(Shared.SYNC_QUEUE);
        // This Worker hosts both Workflow and Activity implementations.
        // Workflows are stateful, so you need to supply a type to create instances.
        worker.registerWorkflowImplementationTypes(BookWorkflowImpl.class);
        // Activities are stateless and thread safe, so a shared instance is used.
        worker.registerActivitiesImplementations(new CreateBookActivityImpl(), new SaveBookActivityImpl(bookRepository));

        // Start polling the Task Queue.
        factory.start();
    }
}
