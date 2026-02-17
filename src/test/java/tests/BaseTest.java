package tests;

import lombok.Setter;

public abstract class BaseTest {

    protected String projectId = "3297";
    protected String projectSlug = "duplingha-neon-cloud";
    @Setter
    protected String collectionSlug;
    protected String email = "duplingha@gmail.com";
    @Setter
    protected String recordId;
    @Setter
    protected String record = "testiki";
}
