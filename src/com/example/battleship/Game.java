package com.example.battleship;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User: lockersoft
 * Date: 2/23/14
 * Time: 3:31 PM
 * <p/>
 * Class: Game
 */
public class Game extends BaseActivity {

  public static GameCell[][] gameGrid = new GameCell[11][11];
  View gameBoard = null;
  Activity customGrid = this;
  public static boolean gameStarted = false;
  Spinner shipSpinner;
  String[] shipsArray;
  Map<String, Integer> shipsMap = new HashMap<String, Integer>();
  ArrayAdapter<String> spinnerArrayAdapter;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    initializeApp();
    setContentView( R.layout.gameboard );
    gameBoard = findViewById( R.id.boardView );
    shipSpinner = (Spinner)findViewById( R.id.shipSpinner );
    gameStarted = false;
  }

  private void initializeApp() {
    // Initialize the gameGrid
    for( int y = 0; y < 11; y++ ) {
      for( int x = 0; x < 11; x++ ) {
        gameGrid[x][y] = new GameCell();
      }
    }
    gameGrid[1][1].setHas_ship( true );   // For debugging
    gameGrid[1][2].setHit( true );
    // API Call to create a game.
    ChallengeComputer();
    GetShips();
  }

  public void GetShips() {
    AsyncHttpClient client = new AsyncHttpClient();
    client.setBasicAuth( loginUsername, loginPassword );
    String challengeUrl = "http://battlegameserver.com/api/v1/available_ships.json";
    client.get( challengeUrl, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess( JSONObject ships ) {
        // Successfully got a response so parse JSON object
        try {
          // Fill in the spinner with ship info
          Iterator iter = ships.keys();
          while( iter.hasNext() ) {
            String key = (String)iter.next();
            Integer value = ships.getInt( key );
            shipsMap.put( key + "(" + value + ")", value );
          }
          int size = shipsMap.keySet().size();
          shipsArray = new String[size];
          shipsArray = shipsMap.keySet().toArray( new String[0] );

          spinnerArrayAdapter = new ArrayAdapter<String>( getApplicationContext(),
              android.R.layout.simple_spinner_item, shipsArray );
          spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item ); // The drop down view
          shipSpinner.setAdapter( spinnerArrayAdapter );

        } catch( Exception e ) {
          e.printStackTrace();
          toastIt( e.getLocalizedMessage() );
        }
      }

      @Override
      public void onFailure( int statusCode, Header[] headers, byte[] responseBody, Throwable error ) {
        // Response failed :(
        toastIt( error.getLocalizedMessage() );
      }
    } );
  }


  public void ChallengeComputer() {
    AsyncHttpClient client = new AsyncHttpClient();
    client.setBasicAuth( loginUsername, loginPassword );
    String challengeUrl = "http://battlegameserver.com/api/v1/challenge_computer.json";
    client.get( challengeUrl, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess( JSONObject user ) {
        // Successfully got a response so parse JSON object
        try {
          gameID = Integer.parseInt( user.getString( "game_id" ) );
        } catch( Exception e ) {
          e.printStackTrace();
          toastIt( e.getLocalizedMessage() );
        }
      }

      @Override
      public void onFailure( int statusCode, Header[] headers, byte[] responseBody, Throwable error ) {
        // Response failed :(
        toastIt( responseBody.toString() );
      }
    } );
  }

  public void onClickAddShip( View view ) {
    // Get the ship information,
    // call the server,
    // remove the ship from the spinner,
    // update the grid
    String selectedShip = shipSpinner.getSelectedItem().toString();
    drawShip( selectedShip );

    shipsMap.remove( selectedShip );
    int size = shipsMap.keySet().size();
    shipsArray = new String[size];
    shipsArray = shipsMap.keySet().toArray( new String[0] );
    spinnerArrayAdapter = new ArrayAdapter<String>( getApplicationContext(),
        android.R.layout.simple_spinner_item, shipsArray );
    shipSpinner.setAdapter( spinnerArrayAdapter );
    if( size == 0 ) {
      gameStarted = true;
      gameBoard.invalidate();
    }
  }

  public void drawShip( String ship ) {

  }

  public static float[] getRelativeCoords( Activity activity,
                                           MotionEvent e ) {
    // MapView
    View contentView = activity.getWindow().
        findViewById( Window.ID_ANDROID_CONTENT );
    return new float[] {
        e.getRawX() - contentView.getLeft(),
        e.getRawY() - contentView.getTop() };
  }

  @Override
  public boolean onTouchEvent( MotionEvent event ) {
    int eventAction = event.getAction();

    switch( eventAction ) {
      case MotionEvent.ACTION_DOWN:
        // finger touches the screen
        break;

      case MotionEvent.ACTION_MOVE:
        // finger moves on the screen
        break;

      case MotionEvent.ACTION_UP:
        // finger leaves the screen
        Log.i( "TOUCH", "x:" + event.getX() + " y:" + event.getY() );
        float[] xy = getRelativeCoords( customGrid, event );
//        Point indicies = findXYIndexes( event.getX(), event.getY() );
        Point indicies = findXYIndexes( xy[0], xy[1] );
        Log.i( "TOUCH", "ix: " + indicies.x + " iy: " + indicies.y );
        gameGrid[indicies.x][indicies.y].setWaiting( true );
        //getWindow().getDecorView().getRootView().invalidate();
        gameBoard.invalidate();
        break;
    }

    // tell the system that we handled the event and no further processing is required
    return true;
  }

  Point findXYIndexes( float x, float y ) {
    // Given X, Y, find the grid indexes that contain that point
    int height = gameGrid[0][0].getCellHeight();
    int width = gameGrid[0][0].getCellWidth();
    int xo = Game.gameGrid[0][0].getViewOrigin().x;
    int yo = Game.gameGrid[0][0].getViewOrigin().y;

    return new Point( (int)( ( x - xo ) / width ),
        (int)( ( y - gridTop - yo ) / height ) );
  }
}