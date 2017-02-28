package tv.superawesome.lib.saevents;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sautils.SAUtils;

public class SAViewableModule {

    // constants
    private final static short  MAX_DISPLAY_TICKS = 1;
    private final static short  MAX_VIDEO_TICKS   = 2;
    private final static int    DELAY             = 1000;

    // member variables (internal)
    private short               ticks        = 0;
    private short               check_tick   = 0;
    private Handler             handler      = null;
    private Runnable            runnable     = null;

    private SAAd ad;

    public SAViewableModule (SAAd ad) {
        this.ad = ad;
    }

    /**
     * Method that sends a viewable impression for a view. SuperAwesome calculates viewable
     * impression conditions for banner, interstitial, etc, ads using IAB standards
     *
     * @param child     the child view group
     * @param maxTicks  max ticks to check the view is visible on the screen before triggering the
     *                  viewable impression event
     * @param listener  an instance of the internal Listener to receive the answer on
     */
    public void checkViewableStatusForView (final ViewGroup child, final int maxTicks, final Listener listener) {
        // safety check
        if (ad == null || child == null) {
            if (listener != null) {
                listener.saDidFindViewOnScreen(false);
            }
            return;
        }

        // create handler
        if (handler == null) {
            handler = new Handler();
        }

        // create a new runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                //
                // End: if this view has been visible for the number of ticks specified by the
                // method, then trigger the viewable impression
                if (ticks >= maxTicks) {
                    if (check_tick == maxTicks) {
                        if (listener != null) {
                            listener.saDidFindViewOnScreen(true);
                        }
                    } else {
                        if (listener != null) {
                            listener.saDidFindViewOnScreen(false);
                        }
                    }
                }
                // In progress: else just continue ticking
                else {
                    ticks++;

                    // if the child becomes invalidated (e.g. view disappears from the screen
                    // while this runner works, then just kill it all and don't send a
                    // viewable impression)
                    if (child == null) {
                        return;
                    }

                    // get the parent
                    View parent = (View) child.getParent();

                    // do one check to see if the parent is null - also useful if the
                    // view's parent disappears from the screen (and thus the view as well)
                    // if that's the case, just kill it all and don't send a viewable impression
                    if (parent == null) {
                        return;
                    }

                    // now get the child position
                    int[] childPos = new int[2];
                    child.getLocationInWindow(childPos);
                    int childX = childPos[0];
                    int childY = childPos[1];
                    int childW = child.getWidth();
                    int childH = child.getHeight();
                    Rect childRect = new Rect(childX, childY, childW, childH);

                    // and the parent position
                    int[] parentPos = new int[2];
                    parent.getLocationInWindow(parentPos);
                    int parentX = parentPos[0];
                    int parentY = parentPos[1];
                    int parentW = parent.getWidth();
                    int parentH = parent.getHeight();
                    Rect parentRect = new Rect(parentX, parentY, parentW, parentH);

                    // and the whole screen position
                    Activity context = (Activity) child.getContext();
                    SAUtils.SASize screenSize = SAUtils.getRealScreenSize(context, false);
                    int screenX = 0;
                    int screenY = 0;
                    int screenW = screenSize.width;
                    int screenH = screenSize.height;
                    Rect screenRect = new Rect(screenX, screenY, screenW, screenH);

                    // if the child is in the parent and the child is also on screen,
                    // increment the counter that verifies for how long a view has been visible
                    if (SAUtils.isTargetRectInFrameRect(childRect, parentRect) && SAUtils.isTargetRectInFrameRect(childRect, screenRect)){
                        check_tick++;
                    }

                    // log
                    Log.d("SuperAwesome", "Viewability count " + ticks + "/" + maxTicks);

                    // run again
                    handler.postDelayed(runnable, DELAY);
                }
            }
        };

        // start
        handler.postDelayed(runnable, DELAY);
    }

    /**
     * Shorthand method to send a viewable impression for a Display ad
     *
     * @param layout    the child view group
     * @param listener  listener to send the response on
     */
    public void checkViewableStatusForDisplay (ViewGroup layout, Listener listener) {
        checkViewableStatusForView(layout, MAX_DISPLAY_TICKS, listener);
    }

    /**
     * Shorthand method to send a viewable impression for a Video ad
     *
     * @param layout    the child view group
     * @param listener  listener to send the respnse on
     */
    public void checkViewableStatusForVideo (ViewGroup layout, Listener listener) {
        checkViewableStatusForView(layout, MAX_VIDEO_TICKS, listener);
    }

    public interface Listener {
        void saDidFindViewOnScreen (boolean success);
    }
}
