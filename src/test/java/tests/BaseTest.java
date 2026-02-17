package tests;

import lombok.Setter;
import tests.steps.AppUserSteps;
import tests.steps.CollectionsStep;



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
    protected CollectionsStep collectionsStep = new CollectionsStep();
    protected AppUserSteps appUserSteps = new AppUserSteps();
}
