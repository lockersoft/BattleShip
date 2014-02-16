package com.example.battleship;

/**
 * User: lockersoft
 * Date: 2/11/14
 * Time: 12:38 PM
 *
 * Class: Gamer
 */
public class Gamer {
 private String first_name;
 private String last_name;
 private String email;
 private Boolean online;
 private Boolean available;
 private Boolean gaming;
 private String avatar_name;
 private String avatar_path;
 private Integer level, coins, battles_won, battles_lost, battles_tied, xp;

  // Gamer Constructor
  public Gamer( String _first_name, String _last_name,
                String _email, Boolean _online, Boolean _available, Boolean _gaming,
                String _avatar_name, String _avatar_path,
                Integer _level,
                Integer _coins,
                Integer _battles_won,
                Integer _battles_lost,
                Integer _battles_tied,
                Integer _xp
  ) {
    first_name = _first_name;
    last_name = _last_name;
    email = _email;
    online = _online;
    available = _available;
    gaming = _gaming;
    avatar_name = _avatar_name;
    avatar_path = _avatar_path;
    level = _level;
    coins = _coins;
    battles_won = _battles_won;
    battles_lost = _battles_lost;
    battles_tied = _battles_tied;
    xp = _xp;
  }

  // first_name Getter
  public String getFirstName() {
    return first_name;
  }

  // first_name Setter
  public void setFirst_name( String _first_name ) {
    first_name = _first_name;
  }

  // last_name Getter
  public String getLastName() {
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
  public Boolean getOnline() {
    return online;
  }

  // online Setter
  public void setOnline( Boolean _online ) {
    online = _online;
  }

  // available Getter
  public Boolean getAvailable() {
    return available;
  }

  // available Setter
  public void setAvailable( Boolean _available ) {
    available = _available;
  }

  // gaming Getter
  public Boolean getGaming() {
    return gaming;
  }

  // gaming Setter
  public void setGaming( Boolean _gaming ) {
    gaming = _gaming;
  }

  // avatarName Getter
  public String getAvatarName() {
    return avatar_name;
  }

  // avatarName Setter
  public void setAvatarPath( String _avatar_name ) {
    avatar_name = _avatar_name;
  }

  // avatarPath Getter
  public String getAvatarPath() {
    return avatar_path;
  }

  // avatarPath Setter
  public void setAvatarName( String _avatar_path ) {
    avatar_path = _avatar_path;
  }

  // level Getter
  public Integer getLevel() {
    return level;
  }

  // level Setter
  public void setLevel( Integer _level ) {
    level = _level;
  }
  // coins Getter
  public Integer getCoins() {
    return coins;
  }

  // coins Setter
  public void setCoins( Integer _coins ) {
    coins = _coins;
  }

  // battles_won Getter
  public Integer getBattlesWon() {
    return battles_won;
  }

  // battles_won Setter
  public void setBattlesWon( Integer _battles_won ) {
    battles_won = _battles_won;
  }
  // battles_lost Getter
  public Integer getBattlesLost() {
    return battles_lost;
  }

  // battles_lost Setter
  public void setBattlesLost( Integer _battles_lost ) {
    battles_lost = _battles_lost;
  }

  // battles_tied Getter
  public Integer getBattlesTied() {
    return battles_tied;
  }

  // battles_tied Setter
  public void setBattlesTied( Integer _battles_tied ) {
    battles_tied = _battles_tied;
  }
  // xp Getter
  public Integer getXp() {
    return xp;
  }

  // xp Setter
  public void setXp( Integer _xp ) {
    xp = _xp;
  }

  public String getAvailableDisplay(){
    return available ? "NO" : "YES";
  }
  public String getOnlineDisplay(){
    return online ? "NO" : "YES";
  }
  public String getGamingDisplay(){
    return gaming ? "NO" : "YES";
  }

}