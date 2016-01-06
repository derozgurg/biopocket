package com.spi.biopocket;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Biopocket extends CordovaPlugin{
    
	public final static String  TAG="Biopocket";
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView)  {
    	Log.d(TAG, "Biopocket initialized");    	
    }    
    
    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {         
        
    	if(action.equals(PluginAction.REGISTER_NFC_STATE_LISTENER)){
    		Log.d(TAG,"register nfc state listener");
    		registerNfcStateListener(callbackContext);
            return true;  
        }                       
        if(action.equals(PluginAction.GET_NFC_STATE)){          
            NfcManager manager = (NfcManager) cordova.getActivity().getSystemService(Context.NFC_SERVICE);          
            NfcAdapter adapter = manager.getDefaultAdapter();           
            if (adapter != null && adapter.isEnabled()) {
                callbackContext.success(1);
            }   
            else{
                callbackContext.success(0); 
            }
    	   return true;  
        }
        
        return false;
    }    
    
    private void registerNfcStateListener(CallbackContext callbackContext){
    	
    	IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED);
    	
    	PockBroadcastReceiver receiver = new PockBroadcastReceiver();
    	
    	this.cordova.getActivity().registerReceiver(receiver, filter);
    	
    	receiver.addReceiveHandler(new OnReceiveHandler(callbackContext){			
			@Override
			public void onReceive(Intent intent) {				
				final String action = intent.getAction();				
				if(action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)){						
					final int nfcState = intent.getIntExtra(NfcAdapter.EXTRA_ADAPTER_STATE,NfcAdapter.STATE_OFF);		
					PluginResult pluginResult;
					switch (nfcState) {
						case NfcAdapter.STATE_OFF:
							Log.d(TAG,"NfcAdapter.STATE_OFF");
							pluginResult = new PluginResult(PluginResult.Status.OK,"off");
							pluginResult.setKeepCallback(true);
							this.callbackContext.sendPluginResult(pluginResult);
			            break;			            
			            case NfcAdapter.STATE_ON:
			            	Log.d(TAG,"NfcAdapter.STATE_ON");
			            	pluginResult = new PluginResult(PluginResult.Status.OK,"on");
							pluginResult.setKeepCallback(true);
							this.callbackContext.sendPluginResult(pluginResult);
			            break;			            
					}
				}					
			}
		});
    }   
}