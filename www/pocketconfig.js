var BiopocketConfig = {};

BiopocketConfig.ApplicationLicense = {
	STANDBY 	: "standby",
	VABK 		: "vabk",
	MOBILFORM	: "mobilform"
}

BiopocketConfig.config = {
	license: BiopocketConfig.ApplicationLicense.STANDBY
}

module.exports  = BiopocketConfig;