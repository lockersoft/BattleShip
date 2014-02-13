package com.example.battleship;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends BaseActivity {
  EditText username, password;
  String base64EncodedCredentials = "";
  Button loginBtn, showUsersBtn;
  String url = "http://battlegameserver.com/users/index.json";
  public JSONObject usersJSON;
  ProgressBar progressBar;
  ListView userListView;

  @Override
  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.main );
    loginBtn = (Button)findViewById( R.id.loginButton );
    username = (EditText)findViewById( R.id.username );
    password = (EditText)findViewById( R.id.password );
    showUsersBtn = (Button)findViewById( R.id.showUsersBtn );
    progressBar = (ProgressBar)findViewById( R.id.progressBar );
    userListView = (ListView)findViewById( R.id.listView );
  }

  private class GetJSONAsync extends AsyncTask<ServerRequest, Integer, ServerRequest> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    }

    @Override
    protected ServerRequest doInBackground( ServerRequest... params ) {
      String usersUrl = params[0].getUrl();
      HttpURLConnection urlConnection = null;
      URL url = null;
      InputStream in = null;
      String jsonData = "";
      int progress = 0;

      // Get the data from the URL
      try {
        url = new URL( usersUrl );
        urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestProperty( "Authorization", "Basic " + base64EncodedCredentials );
        urlConnection.connect();
      } catch( IOException ioe ) {
        ioe.printStackTrace();
      }
      publishProgress( progress += 25 );
      // Convert response into a string
      try {
        if( urlConnection != null ) {
          in = new BufferedInputStream( urlConnection.getInputStream() );
        }
        BufferedReader reader = new BufferedReader(
            new InputStreamReader( in, "UTF8" ), 8 );
        StringBuilder sb = new StringBuilder();
        String line = null;
        while( ( line = reader.readLine() ) != null ) {
          sb.append( line ).append( "\n" );
        }
        in.close();
        jsonData = sb.toString();
      } catch( Exception e ) {
        e.printStackTrace();
      } finally {
        urlConnection.disconnect();
      }

      publishProgress( progress += 50 );

      Log.i( "JSON", jsonData );
      params[0].setJsonDataResult( jsonData );
      return params[0];   // Return same ServerRequest but with data filled in.
    }

    @Override
    protected void onProgressUpdate( Integer... values ) {
      super.onProgressUpdate( values );
      progressBar.setProgress( values[0] );
    }

    @Override
    protected void onPostExecute( ServerRequest sr ) {
      super.onPostExecute( sr );
//        Convert the info here   sr.getJsonDataResult()

      String jsonData = sr.getJsonDataResult();
      // Parse the jsonData into a JSONObject
      try {
        JSONArray allUsers = new JSONArray( jsonData );
        float numUsers = allUsers.length();
        for( int i = 0; i < numUsers; i++ ) {
          JSONObject user = (JSONObject)allUsers.get( i );
          Log.i( "JSON", user.getString( "first_name" ) + " " + user.getString( "last_name" ) + "\n" );
          // TODO: Put data into a ListAdapter to display!!
        }
      } catch( Exception e ) {
        e.printStackTrace();
        toastIt( e.getLocalizedMessage() );
      }

      progressBar.setVisibility( progressBar.getRootView().INVISIBLE );
//    dismissProgressBar();
    }

  }

  public void loginToServer( View view ) {
    String credentials = username.getText().toString() + ":" + password.getText().toString();
    base64EncodedCredentials = Base64.encodeToString( credentials.getBytes(), Base64.NO_WRAP );
    //request.addHeader("Authorization", "Basic " + base64EncodedCredentials);
    showUsersBtn.setEnabled( true );
  }

  public void getUsersOnClick( View v ) {
    ServerRequest sr = new ServerRequest( url, ServerCommands.LOGIN );
    GetJSONAsync task = new GetJSONAsync();
    task.execute( new ServerRequest[] { sr } );
    progressBar.setVisibility( View.VISIBLE );
  }

}
