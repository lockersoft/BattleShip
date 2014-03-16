package com.example.battleship;

import com.loopj.android.http.*;
import org.apache.http.*;
import org.json.*;

/**
 * Created by dljones on 3/14/14.
 */
public class BattleShipAPI extends BaseActivity {
  final String loginUrl = "http://battlegameserver.com/api/v1/login.json";
  String userName, userPassword;

  /**
   * @param _userName
   * @param _password
   */
  BattleShipAPI( String _userName, String _password ) {
    userName = _userName;
    userPassword = _password;
  }

  /**
   *
   */
  public void challengeComputer() {
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
        apiFailure( statusCode, headers, responseBody, error );
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

  /**
   * @param statusCode
   * @param headers
   * @param responseBody
   * @param error
   */
  public void apiFailure( int statusCode, Header[] headers, byte[] responseBody, Throwable error ) {
    // Response failed :(String decodedResponse = ;
    try {
      toastIt( new String( responseBody, "UTF-8" ) );
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }
}
