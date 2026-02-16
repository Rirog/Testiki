package tests;

import tests.steps.AppUserSteps;
import tests.steps.CollectionsStep;


public class BaseTest {

    protected String projectId = "3297";
    protected String projectSlug = "duplingha-neon-cloud";
    protected String collectionSlug;
    protected String email = "duplingha@gmail.com";
    protected String recordId;
    protected String record = "testiki";
    protected CollectionsStep collectionsStep = new CollectionsStep();
    protected AppUserSteps appUserSteps = new AppUserSteps();


}
