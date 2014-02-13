package com.example.battleship;

import android.os.AsyncTask;
import android.os.Bundle;
import org.json.JSONObject;

/**
 * User: lockersoft
 * Date: 2/10/14
 * Time: 7:43 PM
 *
 * Class: GetJSONAsync
 */

public class GetJSONAsync extends AsyncTask<String, Integer, JSONObject> {

  public JSONObject jso;

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
//    displayProgressBar( "Downloading..." );
  }

  @Override
  protected JSONObject doInBackground( String... params ) {
    String url = params[0];

    // Dummy code
    for( int i = 0; i <= 100; i += 5 ) {
      try {
        Thread.sleep( 50 );
      } catch( InterruptedException e ) {
        e.printStackTrace();
      }
      publishProgress( i );
    }
    return jso;
  }

  @Override
  protected void onProgressUpdate( Integer... values ) {
    super.onProgressUpdate( values );
//    updateProgressBar( values[0] );
  }

  @Override
  protected void onPostExecute( JSONObject _jso ) {
    super.onPostExecute( _jso );
    jso = _jso;
//    dismissProgressBar();
  }
}