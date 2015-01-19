function notify(message,title,type,needHide){
	if(needHide==undefined) needHide=true;
    var opts = {
		sticker: false,
		shadow: false,
		history: false,
		hide:needHide,
		opacity: .95 ,
		animation: {'effect_in':'slide','effect_out':'none'},
        text: message,
        title : title
    };
    switch (type) {
    case 'error':
        opts.type = "error";
        break;
    case 'info':
        opts.type = "info";
        break;
    case 'success':
        opts.type = "success";
        break;
    default:
    	break;
    }
    $.pnotify_remove_all();
    $.pnotify(opts);
}