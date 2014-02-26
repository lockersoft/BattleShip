package com.example.battleship;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

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

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    initializeApp();
    setContentView( R.layout.gameboard );
    gameBoard = (View)findViewById( R.id.boardView );
  }

  private void initializeApp() {
    // Initialize the gameGrid
    for(int y=0;y<11;y++){
      for(int x=0;x<11;x++){
        gameGrid[x][y] = new GameCell();
      }
    }
    gameGrid[1][1].setHas_ship( true );
    gameGrid[1][2].setHit( true );

  }

  public static float[] getRelativeCoords(Activity activity,
                                          MotionEvent e)
  {
    // MapView
    View contentView= activity.getWindow().
        findViewById( Window.ID_ANDROID_CONTENT);
    return new float[] {
        e.getRawX() - contentView.getLeft(),
        e.getRawY() - contentView.getTop()};
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int eventAction = event.getAction();

    switch (eventAction) {
      case MotionEvent.ACTION_DOWN:
        // finger touches the screen
        break;

      case MotionEvent.ACTION_MOVE:
        // finger moves on the screen
        break;

      case MotionEvent.ACTION_UP:
        // finger leaves the screen
        Log.i( "TOUCH", "x:" + event.getX() + " y:" + event.getY() );
        float[] xy = getRelativeCoords( customGrid,event );
//        Point indicies = findXYIndexes( event.getX(), event.getY() );
        Point indicies = findXYIndexes( xy[0], xy[1] );
        Log.i("TOUCH", "ix: " + indicies.x + " iy: " + indicies.y);
        gameGrid[indicies.x][indicies.y].setWaiting( true );
        //getWindow().getDecorView().getRootView().invalidate();
        gameBoard.invalidate();
        break;
    }

    // tell the system that we handled the event and no further processing is required
    return true;
  }

  Point findXYIndexes( float x, float y){
    // Given X, Y, find the grid indexes that contain that point
    int height = gameGrid[0][0].getCellHeight();
    int width = gameGrid[0][0].getCellWidth();
    int xo = Game.gameGrid[0][0].getViewOrigin().x;
    int yo = Game.gameGrid[0][0].getViewOrigin().y;

    return new Point( (int)((x-xo)/width),
                      (int)((y-50-yo)/height) );
  }
}
