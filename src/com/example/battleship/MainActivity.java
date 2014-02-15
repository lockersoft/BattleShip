package com.example.battleship;

import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends BaseActivity {
  EditText username, password;
  String base64EncodedCredentials = "";
  Button loginBtn, showUsersBtn;
  String getUsersUrl = "http://battlegameserver.com/users/index.json";
  String loginUrl = "http://battlegameserver.com/users/login.json";
  public JSONObject usersJSON;
  ProgressBar progressBar;
  ListView userListView;
  TextView availableLabel;
  Drawable avatar_image;

  @Override
  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.main );

    loginBtn = (Button)findViewById( R.id.loginButton );
    username = (EditText)findViewById( R.id.username );
    password = (EditText)findViewById( R.id.password );
    showUsersBtn = (Button)findViewById( R.id.showUsersBtn );
    progressBar = (ProgressBar)findViewById( R.id.progressBar );
    availableLabel = (TextView)findViewById( R.id.availableLabel );

  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onPause() {
    dataFragment.setData( currentUser );
    RetainedFragment df = (RetainedFragment) getFragmentManager().findFragmentByTag( "CurrentUserData" );
    Log.i("Test", df.toString());
    super.onPause();
    // store the data in the fragment
  }

  public void loginToServer( View view ) {
    String credentials = username.getText().toString() + ":" + password.getText().toString();
    base64EncodedCredentials = Base64.encodeToString( credentials.getBytes(), Base64.NO_WRAP );
    //request.addHeader("Authorization", "Basic " + base64EncodedCredentials);
    ServerRequest sr = new ServerRequest( loginUrl, ServerCommands.LOGIN );
    GetJSONAsync task = new GetJSONAsync();
    task.execute( new ServerRequest[] { sr } );
  }

  public void getUsersOnClick( View v ) {
    progressBar.setVisibility( View.VISIBLE );
    ServerRequest sr = new ServerRequest( getUsersUrl, ServerCommands.GET_USERS );
    GetJSONAsync task = new GetJSONAsync();
    task.execute( new ServerRequest[] { sr } );
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
        in = new BufferedInputStream( urlConnection.getErrorStream() );
        BufferedReader reader = null;
        try {
          reader = new BufferedReader(
              new InputStreamReader( in, "UTF8" ), 8 );
          StringBuilder sb = new StringBuilder();
          String line = null;
          while( ( line = reader.readLine() ) != null ) {
            sb.append( line ).append( "\n" );
          }
          in.close();
          params[0].setErrorString( sb.toString() );
        } catch( Exception e1 ) {
          e1.printStackTrace();
        }

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
      String jsonData = sr.getJsonDataResult();

      switch( sr.getCommand() ) {

        case GET_USERS:
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
          break;

        case LOGIN:
          if( sr.getErrorString() != null ) {
            toastIt( "Login: " + sr.getErrorString() );
          } else {
            // Put info into a user profile object
            try {
              JSONObject user = new JSONObject( jsonData );
              Log.i( "JSON", user.getString( "first_name" ) + " " + user.getString( "last_name" ) + "\n" );
              // Put into a gamer object
              // String _first_name, String _last_name, String _email, Integer _online,
              // Integer _available, Integer _gaming, String _avatar
              currentUser = new Gamer(
                  user.getString( "first_name" ),
                  user.getString( "last_name" ),
                  user.getString( "email" ),
                  user.getBoolean( "online" ),
                  user.getBoolean( "available" ),
                  user.getBoolean( "gaming" ),
                  user.getString( "avatar_name" ),
                  user.getString( "avatar_image" ),
                  user.getInt( "level" ),
                  user.getInt( "coins" ),
                  user.getInt( "battles_won" ),
                  user.getInt( "battles_lost" ),
                  user.getInt( "battles_tied" )
                  );
              dataFragment.setData( currentUser );
            } catch( Exception e ) {
              e.printStackTrace();
              toastIt( e.getLocalizedMessage() );
            }

//            sr = new ServerRequest( "", ServerCommands.GET_AVATAR );
//            GetJSONAsync task = new GetJSONAsync();
//            task.execute( new ServerRequest[] { sr } );

            showUsersBtn.setEnabled( true );
            showUsersBtn.setVisibility( View.VISIBLE );
            availableLabel.setVisibility( View.VISIBLE );
          }
          break;

        case GET_AVATAR:
          avatar_image = LoadImageFromWeb( currentUser.getAvatarName(), "http://battlegameserver.com/assets/" + currentUser.getAvatarPath() );
          ImageView image = (ImageView)findViewById( R.id.avatar );
          image.setImageDrawable( avatar_image );
          break;

        default:
          break;
      }
    }
  }

}
