package org.mcallydevelops;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/asyncbook")
public class AsyncBookController {


    private final WorkflowOptions options;
    private final WorkflowClient client;

    public AsyncBookController(WorkflowOptions options, WorkflowClient workflowClient) {
        this.options = options;
        this.client = workflowClient;
    }


    @PostMapping
    public void post(@RequestBody BookRequest request) {

        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        BookWorkflow workflow = client.newWorkflowStub(BookWorkflow.class, options);
        // Synchronously execute the Workflow and wait for the response.
        WorkflowClient.execute(workflow::addBook, request.getTitle());
    }
}
