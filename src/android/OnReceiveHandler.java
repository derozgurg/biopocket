package com.spi.biopocket;

import org.apache.cordova.CallbackContext;

import android.content.Intent;
import android.util.Log;

public class OnReceiveHandler implements OnReceiveHandlerIF {
	
	public CallbackContext callbackContext;

	public OnReceiveHandler(CallbackContext callbackContext){
		
		this.callbackContext = callbackContext;
		Log.d(Biopocket.TAG,"Construct");
	}
	
	@Override
	public void onReceive(Intent intent) {
		// TODO Auto-generated method stub
		
	}
	

}
