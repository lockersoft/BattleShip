package com.example.battleship;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * User: lockersoft
 * Date: 2/14/14
 * Time: 6:25 PM
 * <p/>
 * Class: Preferences
 */
public class Preferences extends BaseActivity {
  TextView etAvatar, etFirst, etLast, etPassword, etEmail;
  TextView txXP, txLevel, txCoins, txOnline, txAvailable, txGaming, txWon, txLost, txTied;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.preferences );

    initializeApp();
  }

  private void initializeApp() {
    etAvatar = (TextView)findViewById( R.id.etAvatar );
    etFirst = (TextView)findViewById( R.id.etFirst );
    etLast = (TextView)findViewById( R.id.etLast );
    etEmail = (TextView)findViewById( R.id.etEmail );
    etPassword = (TextView)findViewById( R.id.etPassword );

    txXP = (TextView)findViewById( R.id.txXP );
    txLevel = (TextView)findViewById( R.id.txLevel );
    txCoins = (TextView)findViewById( R.id.txCoins );
    txOnline = (TextView)findViewById( R.id.txOnline );
    txAvailable = (TextView)findViewById( R.id.txAvailable );
    txGaming = (TextView)findViewById( R.id.txGaming );
    txWon = (TextView)findViewById( R.id.txWon );
    txLost = (TextView)findViewById( R.id.txLost );
    txTied = (TextView)findViewById( R.id.txTied );

    SetFieldsFromGamer();
  }

  void SetFieldsFromGamer() {
    etAvatar.setText( currentUser.getAvatarName() );
    etFirst.setText( currentUser.getFirstName() );
    etLast.setText( currentUser.getLastName() );
    etEmail.setText( currentUser.getEmail() );

    txXP.setText( currentUser.getXp().toString() );
    txLevel.setText( currentUser.getLevel().toString() );
    txCoins.setText( currentUser.getCoins().toString() );

    txOnline.setText( currentUser.getOnlineDisplay() );
    txAvailable.setText( currentUser.getAvailableDisplay() );
    txGaming.setText( currentUser.getGamingDisplay() );

    txWon.setText( currentUser.getBattlesWon().toString() );
    txLost.setText( currentUser.getBattlesLost().toString() );
    txTied.setText( currentUser.getBattlesTied().toString() );
  }

  public void savePrefsOnClick( View v ) {

  }
}
