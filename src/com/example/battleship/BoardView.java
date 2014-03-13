package com.example.battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * User: lockersoft
 * Date: 2/23/14
 * Time: 7:17 PM
 * <p/>
 * Class: BoardView
 */

public class BoardView extends ImageView {
  int border = 10;    // Width of the outer border
  int topBoardX = border;
  int topBoardY = 50;
  int fontSize = 30;
  Point[][] grid = new Point[11][11];
  String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
  String[] numbers = { " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", "10" };
  private static Paint paint;
  Point origin = new Point( this.getLeft(), this.getTop() );

  public BoardView( Context context, AttributeSet attrs ) {
    super( context, attrs );

    paint = new Paint();
    paint.setStrokeWidth( 10 );
    paint.setColor( Color.BLUE );
    paint.setStyle( Paint.Style.FILL_AND_STROKE );
    paint.setTextSize( fontSize );
  }

  @Override
  protected void onDraw( Canvas canvas ) {
    super.onDraw( canvas );
    this.getLeft();
    int screenHeight = this.getMeasuredHeight();
    int screenWidth = this.getMeasuredWidth();
    int middle = screenHeight / 2;
    // Erase the view first
    paint.setColor( Color.BLACK );
    canvas.drawRect( topBoardX, topBoardY, screenWidth - border, middle - border, paint );

    paint.setColor( Color.BLUE );

    // Calculate cell width based on width
    int cellWidth = ( screenWidth / 11 ) - 1;
    int cellHeight = ( middle - border ) / 12;   // 10 cells + 1 for letters and 1 for Attack/Defend Boards

    // Draw a border
    canvas.drawRect( topBoardX, topBoardY, screenWidth - border, middle - border, paint );
    paint.setColor( Color.GREEN );
    paint.setStrokeWidth( 2 );

    if( Game.gameStarted ) {

      Game.gameGrid[0][0].setCellHeight( cellHeight );
      Game.gameGrid[0][0].setCellWidth( cellWidth );
      Game.gameGrid[0][0].setViewOrigin( origin );

      for( int y = 0; y < 11; y++ ) {
        for( int x = 0; x < 11; x++ ) { // TODO:  add the points to the gameGrid
          Game.gameGrid[x][y].setTopleft( new Point( x * cellWidth + border, y * cellHeight + topBoardY ) );
          Game.gameGrid[x][y].setBottomright( new Point( ( x + 1 ) * cellWidth + border, ( y + 1 ) * cellHeight + topBoardX ) );
        }
      }

      // Draw the horizontal lines
      for( int i = 0; i < 11; i++ ) {
        canvas.drawLine( i * cellWidth + border, topBoardY, i * cellWidth + border, middle - border, paint );    // Vertical Lines
        canvas.drawLine( border, i * cellHeight + topBoardY, screenWidth - border, i * cellHeight + topBoardY, paint );  // Horizontal Lines
      }
      paint.setStrokeWidth( 2 );
      paint.setColor( Color.WHITE );
      paint.setStyle( Paint.Style.FILL_AND_STROKE );
      canvas.drawText( "Attacking Board", 40, 40, paint );

      float w = paint.measureText( numbers[0], 0, numbers[0].length() );
      float center = ( cellWidth / 2 ) - ( w / 2 );
      for( int x = 0; x < 10; x++ ) {
        canvas.drawText( letters[x], Game.gameGrid[x][0].getTopleft().x + center + cellWidth, Game.gameGrid[x][0].getTopleft().y + fontSize + border, paint );
      }
      for( int y = 0; y < 10; y++ ) {
        canvas.drawText( numbers[y], Game.gameGrid[0][y].getTopleft().x + center, Game.gameGrid[0][y + 1].getTopleft().y + cellHeight - border, paint );
      }

      paint.setColor( Color.WHITE );
      // Draw the contents of the grid
      for( int y = 0; y < 11; y++ ) {
        for( int x = 0; x < 11; x++ ) {
          if( Game.gameGrid[x][y].getHas_ship() )
            drawCell( "S", x, y, center, canvas );
          if( Game.gameGrid[x][y].getWaiting() )
            drawCell( "W", x, y, center, canvas );
          if( Game.gameGrid[x][y].getMiss() )
            drawCell( "M", x, y, center, canvas );
          if( Game.gameGrid[x][y].getHit() )
            drawCell( "H", x, y, center, canvas );
        }

      }
    }
  }

  void drawCell( String contents, int x, int y, float center, Canvas canvas ) {
    canvas.drawText( contents, Game.gameGrid[x][y].getTopleft().x + center, Game.gameGrid[x][y].getTopleft().y + center, paint );
  }

}