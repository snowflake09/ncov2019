"use strict";

var page = require('webpage').create(),
system=require('system');
var address = system.args[1];
var output = system.args[2];

page.viewportSize = { width: system.args[3] || '100%', height: system.args[4] || '100%' };
page.open(address, function (status) {
    if (status !== 'success') {
        console.log('Unable to load the address!');
        phantom.exit();
    } else {
    	var timer = window.setInterval(function (){
		    var title = page.evaluate(function () {
		    	return document.title;
		    });
			console.log(title)
			if (title === "donedone") {
				clearInterval(timer);
	            page.render(output);
	            phantom.exit();
			}
		}, 1000)
    }
});