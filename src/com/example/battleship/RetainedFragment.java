package com.example.battleship;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * User: lockersoft
 * Date: 2/14/14
 * Time: 11:34 PM
 * <p/>
 * Class: RetainedFragment
 * Directly from :  http://developer.android.com/guide/topics/resources/runtime-changes.html#RetainingAnObject
 */
public class RetainedFragment extends Fragment {

  // data object we want to retain
  private Gamer data;

//  public RetainedFragment(){
//    super();
//    setRetainInstance( true );
//  }
  // this method is only called once for this fragment
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // retain this fragment across activities
    this.setRetainInstance(true);
  }
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated( savedInstanceState );
    setRetainInstance(true);//toggle this
  }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return null;
  }

  public void setData(Gamer data) {
    this.data = data;
  }

  public Gamer getData() {
    return data;
  }
 }
