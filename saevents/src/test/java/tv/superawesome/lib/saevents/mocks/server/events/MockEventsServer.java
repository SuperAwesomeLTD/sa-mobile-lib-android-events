package tv.superawesome.lib.saevents.mocks.server.events;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import tv.superawesome.lib.saevents.mocks.server.MockAbstractWebServer;
import tv.superawesome.lib.saevents.mocks.server.ResponseFactory;

/**
 * Created by gabriel.coman on 09/05/2018.
 */
public class MockEventsServer extends MockAbstractWebServer {

    @Override
    public MockResponse handleRequest(RecordedRequest request) {

        String line = request.getRequestLine();
        /*
         * Click event
         */
        if (line.contains("/click")) {
            if (line.contains("placement=1000")) {
                return ResponseFactory.successResponse();
            } else {
                return ResponseFactory.timeoutResponse();
            }
        }
        /*
         * Video click event
         */
        else if (line.contains("/video/click")) {
            if (line.contains("placement=1000")) {
                return ResponseFactory.successResponse();
            } else {
                return ResponseFactory.timeoutResponse();
            }
        }
        /*
         * Impression event
         */
        else if (line.contains("/impression")) {
            if (line.contains("placement=1000")) {
                return ResponseFactory.successResponse();
            } else {
                return ResponseFactory.timeoutResponse();
            }
        }
        /*
         * Viewable impression event
         */
        else if (line.contains("/event")) {
            if (line.contains("placement%22%3A1000")) {
                return ResponseFactory.successResponse();
            } else {
                return ResponseFactory.timeoutResponse();
            }
        }
        /*
         * Any type of URL event
         */
        else if (line.contains("/vast/event")) {
            if (line.contains("placement=1000")) {
                return ResponseFactory.successResponse();
            } else {
                return ResponseFactory.timeoutResponse();
            }
        }
        else {
            return ResponseFactory.timeoutResponse();
        }
    }
}
