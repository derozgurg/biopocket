package com.spi.biopocket;

import java.util.ArrayList;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PockBroadcastReceiver extends BroadcastReceiver {


	public ArrayList<OnReceiveHandlerIF> receiveHandlers = new ArrayList<OnReceiveHandlerIF>();

	@Override
	public void onReceive(Context context, Intent intent) {		
		
		for(int i = 0 ; i < receiveHandlers.size();i++){
			OnReceiveHandlerIF handler = receiveHandlers.get(i);				
			handler.onReceive(intent);
		}			
	}
	
	/**
	 * Add onreceive broadcast handler
	 * 
	 * @param onReceiveEventer Receive Handler 
	 */
	public void addReceiveHandler(OnReceiveHandlerIF onReceiveEventer){
		receiveHandlers.add(onReceiveEventer);
	}
}
