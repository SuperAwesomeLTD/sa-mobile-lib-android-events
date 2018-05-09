package tv.superawesome.lib.saevents.mocks.server;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.SocketPolicy;
import tv.superawesome.lib.saevents.testutils.ResourceReader;

/**
 * Created by gabriel.coman on 09/05/2018.
 */

public class ResponseFactory {

    public static MockResponse successResponse() {
        String responseBody = ResourceReader.readString("mock_event_success_response.json");
        return new MockResponse().setBody(responseBody).setResponseCode(200);
    }

    public static MockResponse timeoutResponse () {
        return new MockResponse()
                .setBody("")
                .setSocketPolicy(SocketPolicy.NO_RESPONSE);
    }
}
