package com.example.battleship;

/**
 * User: lockersoft
 * Date: 2/11/14
 * Time: 12:38 PM
 *
 * Class: Gamer
 */
public class Gamer {
  String first_name;
  String last_name;
  String email;
  Integer online;
  Integer available;
  Integer gaming;
  // Gamer Constructor

  public Gamer( String _first_name, String _last_name, String _email, Integer _online, Integer _available, Integer _gaming ) {
    first_name = _first_name;
    last_name = _last_name;
    email = _email;
    online = _online;
    available = _available;
    gaming = _gaming;
  }

  // first_name Getter
  public String getFirst_name() {
    return first_name;
  }

  // first_name Setter
  public void setFirst_name( String _first_name ) {
    first_name = _first_name;
  }

  // last_name Getter
  public String getLast_name() {
    return last_name;
  }

  // last_name Setter
  public void setLast_name( String _last_name ) {
    last_name = _last_name;
  }

  // email Getter
  public String getEmail() {
    return email;
  }

  // email Setter
  public void setEmail( String _email ) {
    email = _email;
  }

  // online Getter
  public Integer getOnline() {
    return online;
  }

  // online Setter
  public void setOnline( Integer _online ) {
    online = _online;
  }

  // available Getter
  public Integer getAvailable() {
    return available;
  }

  // available Setter
  public void setAvailable( Integer _available ) {
    available = _available;
  }

  // gaming Getter
  public Integer getGaming() {
    return gaming;
  }

  // gaming Setter
  public void setGaming( Integer _gaming ) {
    gaming = _gaming;
  }

}