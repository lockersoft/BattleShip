package com.example.battleship;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;

/**
 * User: lockersoft
 * Date: 2/14/14
 * Time: 6:25 PM
 * <p/>
 * Class: Preferences
 */
public class Preferences extends BaseActivity {
  TextView etFirst, etLast;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.preferences );

    initializeApp();
  }

  private void initializeApp() {
    etFirst = (TextView)findViewById( R.id.etFirst );
    etLast = (TextView)findViewById( R.id.etLast );

    SetFieldsFromGamer();  // TODO:  Get the current_user to work across intents.
  }

  void SetFieldsFromGamer(){
    etFirst.setText( currentUser.getFirstName() );
    etLast.setText( currentUser.getLastName() );
  }

  public void savePrefsOnClick( View v ){

  }
}
