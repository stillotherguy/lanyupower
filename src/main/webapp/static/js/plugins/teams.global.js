$.fn.outerHTML = function(){
    // IE, Chrome & Safari will comply with the non-standard outerHTML, all others (FF) will have a fall-back for cloning
    return (!this.length) ? this : (this[0].outerHTML || (
      function(el){
          var div = document.createElement('div');
          div.appendChild(el.cloneNode(true));
          var contents = div.innerHTML;
          div = null;
          return contents;
    })(this[0]));
};
//Cloned objects, repair the Placeholder compatibility problems
$.fn.fixPlaceholder = function(){
	var supportPlaceholder = false;
	if ("placeholder" in document.createElement("input")){
		supportPlaceholder = true;
	}
	if(!supportPlaceholder){
		var active = document.activeElement;
		$(this).find(':text,textarea').bind("focus", function () {
			if ($(this).attr("placeholder") != "" && $(this).val() == $(this).attr("placeholder")) {
				$(this).val("");
			}
		}).bind("blur", function () {
			if ($(this).attr("placeholder") != "" && ($(this).val() == "" || $(this).val() == $(this).attr("placeholder"))) {
				$(this).val($(this).attr("placeholder"));
			}
		});
		$(active).focus();
	}
};
window.alert=function(){};
var console = console || {log : function(){return false;}};