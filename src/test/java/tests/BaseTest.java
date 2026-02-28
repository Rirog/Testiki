package tests;

import lombok.Getter;
import lombok.Setter;
@Getter
public abstract class BaseTest {

    protected String projectId = "3503";


    protected String email = "testing252@gmail.com";

    @Setter
    protected String record = "testiki";
}
