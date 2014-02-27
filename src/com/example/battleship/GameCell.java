package com.example.battleship;

import android.graphics.Point;

/**
 * User: lockersoft
 * Date: 2/24/14
 * Time: 11:45 AM
 * <p/>
 * Class: GameCell
 */
public class GameCell {
  private Boolean has_ship;
  private Boolean miss;
  private Boolean hit;
  private Boolean waiting;
  private Point topLeft;
  private Point bottomRight;
  private Point viewOrigin;
  private int cellHeight, cellWidth;

  public GameCell() {
    has_ship = false;
    miss = false;
    hit = false;
    waiting = false;
    topLeft = null;
    bottomRight = null;
    cellHeight = 0;
    cellWidth = 0;
  }

  // GameCell Constructor
  public GameCell( Boolean _has_ship, Boolean _miss, Boolean _hit, Boolean _waiting, Point _topLeft, Point _bottomRight ) {
    has_ship = _has_ship;
    miss = _miss;
    hit = _hit;
    waiting = _waiting;
    topLeft = _topLeft;
    bottomRight = _bottomRight;
  }

  public void setViewOrigin( Point _origin ) {
    viewOrigin = _origin;
  }

  public Point getViewOrigin() {
    return viewOrigin;
  }

  public void setCellHeight( int _cellHeight ) {
    cellHeight = _cellHeight;
  }

  public int getCellHeight() {
    return cellHeight;
  }

  public void setCellWidth( int _cellWidth ) {
    cellWidth = _cellWidth;
  }

  public int getCellWidth() {
    return cellWidth;
  }

  // has_ship Getter
  public Boolean getHas_ship() {
    return has_ship;
  }

  // has_ship Setter
  public void setHas_ship( Boolean _has_ship ) {
    has_ship = _has_ship;
  }

  // miss Getter
  public Boolean getMiss() {
    return miss;
  }

  // miss Setter
  public void setMiss( Boolean _miss ) {
    miss = _miss;
  }

  // hit Getter
  public Boolean getHit() {
    return hit;
  }

  // hit Setter
  public void setHit( Boolean _hit ) {
    hit = _hit;
  }

  // waiting Getter
  public Boolean getWaiting() {
    return waiting;
  }

  // waiting Setter
  public void setWaiting( Boolean _waiting ) {
    waiting = _waiting;
  }

  // topLeft Getter
  public Point getTopleft() {
    return topLeft;
  }

  // topLeft Setter
  public void setTopleft( Point _topLeft ) {
    topLeft = _topLeft;
  }

  // bottomRight Getter
  public Point getBottomright() {
    return bottomRight;
  }

  // bottomRight Setter
  public void setBottomright( Point _bottomRight ) {
    bottomRight = _bottomRight;
  }

}