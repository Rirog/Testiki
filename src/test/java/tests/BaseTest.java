package tests;

import lombok.Setter;

public abstract class BaseTest {

    protected String projectId = "3503";

    @Setter
    protected String collectionSlug; //?

    protected String email = "testing252@gmail.com";

    @Setter
    protected String recordId; //?

    @Setter
    protected String record = "testiki";
}
