package tv.superawesome.lib.saevents.mocks.models;

import tv.superawesome.lib.samodelspace.saad.SAMedia;
import tv.superawesome.lib.samodelspace.vastad.SAVASTAd;

/**
 * Created by gabriel.coman on 09/05/2018.
 */

public class MockMedia extends SAMedia {

    public MockMedia (SAVASTAd savastAd) {
        this.html = "<some></some>";
        this.path = "";
        this.url = "https://some.url";
        this.type = "mp4";
        this.isDownloaded = true;
        this.vastAd = savastAd;
    }
}
