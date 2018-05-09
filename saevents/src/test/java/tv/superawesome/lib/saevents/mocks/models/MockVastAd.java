package tv.superawesome.lib.saevents.mocks.models;

import java.util.ArrayList;

import tv.superawesome.lib.samodelspace.vastad.SAVASTAd;
import tv.superawesome.lib.samodelspace.vastad.SAVASTAdType;

/**
 * Created by gabriel.coman on 09/05/2018.
 */

public class MockVastAd extends SAVASTAd {

    public MockVastAd () {
        this.redirect = null;
        this.type = SAVASTAdType.InLine;
        this.url = "http://some/url";
        this.media = new ArrayList<>();
        this.media.add(new MockVastMedia());
        this.events = new ArrayList<>();
        this.events.add(new MockVastEvent("vast_impression"));
        this.events.add(new MockVastEvent("vast_click_through"));
        this.events.add(new MockVastEvent("vast_creativeView"));
        this.events.add(new MockVastEvent("vast_start"));
        this.events.add(new MockVastEvent("vast_firstQuartile"));
        this.events.add(new MockVastEvent("vast_midpoint"));
        this.events.add(new MockVastEvent("vast_thirdQuartile"));
        this.events.add(new MockVastEvent("vast_complete"));
        this.events.add(new MockVastEvent("vast_click_tracking"));
    }
}
