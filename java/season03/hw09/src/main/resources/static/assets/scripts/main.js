;(function(global,$){
	
	function EmptyObject(obj){
    	for(o in obj){
    		if(o){
    			return true;
    		}
    	}
    	return false;
    }
	
    function translateSize(size){
    	var str = (size+'').toLowerCase();
    	var sizeLen = str.length
    	if(str.search(/kb/i) === sizeLen - 2){
    		return parseFloat(str.substring(0,sizeLen-2)*1024);
    	} else if(str.search(/mb/i) === sizeLen - 2){
    		return parseFloat(str.substring(0,sizeLen-2)*1048576);
    	} else if(str.search(/gb/i) === sizeLen - 2){
    		return parseFloat(str.substring(0,sizeLen-2)*1073741824);
    	} else if(str.search(/b/i) === sizeLen - 1){
    		return parseFloat(str.substring(0,sizeLen-1));
    	} else if(str.search(/s/i) === sizeLen - 1){
    		return parseFloat(str.substring(0,sizeLen-1));
    	} else if(str.search(/m/i) === sizeLen - 1){
    		return parseFloat(str.substring(0,sizeLen-1)*60);
    	} else if(str.search(/h/i) === sizeLen - 1){
    		return parseFloat(str.substring(0,sizeLen-1)*3600);
    	}
    }
    function parseTemplate(tpl,obj) {
        if (obj == null) {
            return;
        }
        return tpl.replace(/({{([\s\S]+?)}})/g, function (_, key, value) {
            var v = value.trim();
            if (Object.prototype.hasOwnProperty.call(obj, v)) {
                return obj[v];
            } else {
                return '';
            }
        });
    }
    
    var Utils = global.Utils || {};

    Utils.translateSize = translateSize;
    Utils.EmptyObject = EmptyObject;
    Utils.parseTemplate = parseTemplate;
    global.Utils = Utils;

}(window,jQuery,undefined));

