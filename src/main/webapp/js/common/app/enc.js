var signature = angular.module('Signature', ['Encryption']);

signature.service("Sign", function(Md5) {
	this.ProcessParams = function (url_param_map) {
		var keys = Object.keys(url_param_map).sort();
		var param_str = "";
	    var data_processed = {};
	    for (var i = 0; i < keys.length; i++) {
	    	if (!keys[i] || !url_param_map[keys[i]]) {
	    		continue;
	    	}
	    	data_processed[keys[i]] = url_param_map[keys[i]];
	    	param_str += keys[i] + "=" + url_param_map[keys[i]] + "&";
	    }
	    var key = "037Z6Z3127604N4ZKW8KKT1KW4NW78W3";
	    var key_ = SwapSpaces(HTMLEscape(Affine(-1, key, 3, 10)));
	    param_str += "key=" + key_;
	    var sign = Md5.md5(param_str).toUpperCase();
	    data_processed["sign"] = sign;
	    return data_processed;
	}
});