package com.example.battleship;

import android.util.Log;
import android.view.View;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONObject;

/**
 * Created by dljones on 3/14/14.
 */
public class BattleShipAPI {
  final String loginUrl = "http://battlegameserver.com/api/v1/login.json";
  String userName, userPassword;

  BattleShipAPI(String _userName, String _password){
    userName = _userName;
    userPassword = _password;
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
