package com.example.battleship;

import android.graphics.*;

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

  /**
   *
   */
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

  /**
   * @param _has_ship
   * @param _miss
   * @param _hit
   * @param _waiting
   * @param _topLeft
   * @param _bottomRight
   */
  public GameCell( Boolean _has_ship, Boolean _miss, Boolean _hit, Boolean _waiting, Point _topLeft, Point _bottomRight ) {
    has_ship = _has_ship;
    miss = _miss;
    hit = _hit;
    waiting = _waiting;
    topLeft = _topLeft;
    bottomRight = _bottomRight;
  }

  /**
   * @param _origin
   */
  public void setViewOrigin( Point _origin ) {
    viewOrigin = _origin;
  }

  /**
   * @return viewOrigin
   */
  public Point getViewOrigin() {
    return viewOrigin;
  }

  /**
   * @param _cellHeight
   */
  public void setCellHeight( int _cellHeight ) {
    cellHeight = _cellHeight;
  }

  /**
   * @return cellHeight
   */
  public int getCellHeight() {
    return cellHeight;
  }

  /**
   * @param _cellWidth
   */
  public void setCellWidth( int _cellWidth ) {
    cellWidth = _cellWidth;
  }

  /**
   * @return cellWidth
   */
  public int getCellWidth() {
    return cellWidth;
  }


  /**
   * has_ship Getter
   *
   * @return has_ship
   */
  public Boolean getHas_ship() {
    return has_ship;
  }

  /**
   * Getter
   *
   * @param _has_ship
   */
  public void setHas_ship( Boolean _has_ship ) {
    has_ship = _has_ship;
  }

  /**
   * @return miss
   */
  public Boolean getMiss() {
    return miss;
  }

  /**
   * @param _miss
   */
  public void setMiss( Boolean _miss ) {
    miss = _miss;
  }

  /**
   * @return hit
   */
  public Boolean getHit() {
    return hit;
  }

  /**
   * @param _hit
   */
  public void setHit( Boolean _hit ) {
    hit = _hit;
  }

  /**
   * @return waiting
   */
  public Boolean getWaiting() {
    return waiting;
  }

  /**
   * @param _waiting
   */
  public void setWaiting( Boolean _waiting ) {
    waiting = _waiting;
  }

  /**
   * @return topLeft
   */
  public Point getTopleft() {
    return topLeft;
  }

  /**
   * @param _topLeft
   */
  public void setTopleft( Point _topLeft ) {
    topLeft = _topLeft;
  }

  /**
   * @return bottomRight
   */
  public Point getBottomright() {
    return bottomRight;
  }

  /**
   * @param _bottomRight
   */
  public void setBottomright( Point _bottomRight ) {
    bottomRight = _bottomRight;
  }

}