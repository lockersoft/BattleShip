package com.example.battleship;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
  EditText username, password;
  Button loginBtn;

  @Override
  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.main );
    loginBtn = (Button) findViewById( R.id.loginButton );
    username = (EditText) findViewById( R.id.username );
    password = (EditText) findViewById( R.id.password );
  }

  public void loginToServer( View view ){

  }
}
