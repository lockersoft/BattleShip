package com.example.battleship;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by dljones on 3/14/14.
 */
public class BattleShipAPI extends BaseActivity {
  final String loginUrl = "http://battlegameserver.com/api/v1/login.json";
  String userName, userPassword;

  BattleShipAPI(String _userName, String _password){
    userName = _userName;
    userPassword = _password;
  }

  public void  challengeComputer(){
    AsyncHttpClient client = new AsyncHttpClient();
    client.setBasicAuth( loginUsername, loginPassword );
    String challengeUrl = "http://battlegameserver.com/api/v1/challenge_computer.json";
    client.get( challengeUrl, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess( JSONObject game ) {
        // Successfully got a response so parse JSON object
        Game.challengeComputerSuccess( game );
      }

      @Override
      public void onFailure( int statusCode, Header[] headers, byte[] responseBody, Throwable error ) {
        apiFailure( statusCode,headers,responseBody,error );
      }
    } );
  }

//  public void availableShips(){
//    AsyncHttpClient client = new AsyncHttpClient();
//    client.setBasicAuth( loginUsername, loginPassword );
//    String challengeUrl = "http://battlegameserver.com/api/v1/available_ships.json";
//    client.get( challengeUrl, new JsonHttpResponseHandler() {
//      @Override
//      public void onSuccess( JSONObject ships ) {
//        // Successfully got a response so parse JSON object
//        Game.getShipsSuccess( ships );
//      }
//
//      @Override
//      public void onFailure( int statusCode, Header[] headers, byte[] responseBody, Throwable error ) {
//        // Response failed :(
//        try {
//          toastIt( new String( responseBody, "UTF-8" ) );
//        } catch( Exception e ) {
//          e.printStackTrace();
//        }
//      }
//    } );
//
//  }

  public void apiFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error){
    // Response failed :(String decodedResponse = ;
    try {
      toastIt( new String( responseBody, "UTF-8" ) );
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }
//  JSONObject login(){
//    AsyncHttpClient client = new AsyncHttpClient();
//    client.setBasicAuth( userName, userPassword );
//    client.get( loginUrl, new AsyncHttpResponseHandler() {
//
////      @Override
////      public void onProgress( int position, int length ) {
////        Log.i("LOGIN", "pos: " + position + " len: " + length);
////        progressBar.setProgress( position );
////      }
//
//      @Override
//      public void onSuccess( String response ) {
//        Log.i( "LOGIN", response );
//
//
//      }
//
//      @Override
//      public void onFailure( int i, Throwable e, String errorMsg ) {
//        // Response failed :(
//        Log.i( "LOGIN", errorMsg + " i:" + i );
////        toastIt( "Connection Error: " + errorMsg );
//      }
//    } );
////    return XXX;
//  }

}
