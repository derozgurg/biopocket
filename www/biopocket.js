
var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    pocketman = require('./BiopocketConfig');

const PluginMethod ={ 
	HAS_OR_GET_START_PERMISSION : "has_or_get_startpermission",
	REGISTER_FILTER : "register_filter",
	EXISTS_AN_APPLICATION : "exists_an_application",
	CHECK_AN_APPLICATION_RUNNING : "check_an_application_running",
	SWITCH_APPLICATION : "switch_application",
	REGISTER_NFC_STATE_LISTENER : "register_nfc_state_listener",
	GET_NFC_STATE: "get_nfc_state"

};


var Biopocket = function() {};

Biopocket.getLicense = function(){
	return pocketman.config.license
};

Biopocket.registerNFCStateListener = function(stateHandler,onfail){
	cordova.exec(stateHandler,onfail,"Biopocket",PluginMethod.REGISTER_NFC_STATE_LISTENER,[]);	
};



Biopocket.getNFCstate = function(onsucceed,onfail){
	cordova.exec(onsucceed,onfail,"Biopocket",PluginMethod.GET_NFC_STATE,[]);	
};

module.exports = Biopocket;