package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * class that demonstrates gestures in android
 */
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;
    private Button clearButton;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    private GestureDetector mGestureDetector;

    /**
     * on app start handles initilizing views and values
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesLogTextView= (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView= (TextView) findViewById(R.id.singleTapsCountTextView);
        doubleTapTextView=(TextView) findViewById(R.id.doubleTapsCountTextView);
        gesturesScrollView= (ScrollView) findViewById(R.id.gesturesScrollView);
        longPressTextView= (TextView) findViewById(R.id.longPressCountTextView);
        scrollTextView=(TextView) findViewById(R.id.scrollsCountTextView);
        flingTextView=(TextView) findViewById(R.id.flingsCountTextView);

        clearButton= (Button) findViewById(R.id.clearButton);

        mGestureDetector= new GestureDetector(gesturesScrollView.getContext(), this);

        //need to override one more method



    }

    /**
     * sends the touch event to all the children in the viewGroup
     * scroll view to the text view
     * @param ev motion event triggering the touch
     * @return returns true if the event was handled, false otherwise
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    /**
     * handles a single tap gesture. not part of the double taps
     * @param motionEvent motion event triggering the touch
     * @return returns true if the event was handled, false otherwise
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
       if(clearButton.isActivated()) {
           gesturesLogTextView.append("\nonSingletap gesture occurred");
           singleTapTextView.setText(String.valueOf(++singleTaps));
       }
       clearButton.setActivated(true);
        return true;
    }

    /**
     *  responds two singletap in succession events aka DoubleTap
     *  within a certain android dictated duration
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTap gesture occured");
        doubleTapTextView.setText(String.valueOf(++doubleTaps));
        return true;
    }

    /**
     * during a double tap another event occurs ( including move).
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {

        return false;
    }

    /**
     * user made contact with device
     * every GESTURE BEGINS WITH onDOWN
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown gesture occured");
        return true;
    }

    /**
     * this is a down event where user does not let go a shirt duration of time so it
     * is nota a long press
     * @param motionEvent
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    /**
     * Simiiar o onSingleTapConfirmed but it could be part of double -tap
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {

        return false;
    }

    /**
     * down event without letting go where the user moves laterally
     * @param motionEvent event where the scroll started
     * @param motionEvent1 event where the scroll ended up
     * @param distanceX the distance in the x direction (pixels)
     * @param distanceY the distance in Y dorection (pixels)
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
        gesturesLogTextView.append("\n Scroll gesture occured, distanceX= " +distanceX + ", distanceY = "+ distanceY);
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    /**
     * User down event holds for a long duration before letting go
     *
     * @param motionEvent
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {
gesturesLogTextView.append("\nonLongPress gesture occured");
        longPressTextView.setText(String.valueOf(++longPresses));
    }

    /**
     * Similiar to scroll, with faster velocity and user releases contact with device units are (pixels/second)
     * velocity in the x and the velocity in Y
     * @param motionEvent
     * @param motionEvent1
     * @param velocityX
     * @param velocityY
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
        gesturesLogTextView.append("\n FLing gesture occured, Velocity in the X direction= " +velocityX + ", velocity in Y direction = "+ velocityY);
        flingTextView.setText(String.valueOf(++flings));
        return true;
    }

    /**
     * resets all the views to base values
     * @param v
     */
    public void clearAll(View v)
    {
clearButton.setActivated(false);
        gesturesLogTextView.setText("");
        singleTapTextView.setText("0");
        doubleTapTextView.setText("0");
        scrollTextView.setText("0");
        flingTextView.setText("0");
        longPressTextView.setText("0");

        singleTaps=0;
        doubleTaps=0;
        scrolls=0;
        flings=0;
        longPresses=0;
    }

}
