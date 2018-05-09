package tv.superawesome.lib.saevents.mocks.models;

import tv.superawesome.lib.samodelspace.vastad.SAVASTEvent;

/**
 * Created by gabriel.coman on 09/05/2018.
 */

public class MockVastEvent extends SAVASTEvent {

    public MockVastEvent (String event) {
        this.event = event;
        this.URL = "https://event.api.com/vast/" + event;
    }
}
