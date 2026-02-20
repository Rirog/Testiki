package tests;

import lombok.Setter;

public abstract class BaseTest {

    protected String projectId = "3503";
//    protected String projectSlug = "dupling-cocoa-studio";
    @Setter
    protected String collectionSlug;
    protected String email = "duplingha@gmail.com";
    @Setter
    protected String recordId;
    @Setter
    protected String record = "testiki";
}
