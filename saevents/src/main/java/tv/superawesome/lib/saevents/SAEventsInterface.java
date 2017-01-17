/**
 * @Copyright:   SuperAwesome Trading Limited 2017
 * @Author:      Gabriel Coman (gabriel.coman@superawesome.tv)
 */
package tv.superawesome.lib.saevents;

/**
 * The main interface used by SAEvents to send the status of an event back
 */
public interface SAEventsInterface {

    /**
     * Only method in the interface; will be returned when an event finishes firing.
     *
     * @param success   success state of the event request
     * @param status    status of the event request
     */
    void saDidGetEventResponse(boolean success, int status);
}
