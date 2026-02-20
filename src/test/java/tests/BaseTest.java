package tests;

import lombok.Setter;

public abstract class BaseTest {

    protected String projectId = "3503";

    @Setter
    protected String collectionSlug;

    protected String email = "duplingha@gmail.com";

    @Setter
    protected String recordId;

    @Setter
    protected String record = "testiki";
}
