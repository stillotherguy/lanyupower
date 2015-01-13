/**
 * 系统帮助类函数
 */
define(function(require, exports, module) {
	
	var templates = require('teams/component/template');

	var datepickerHelper = {
		defaults : {
			el : 'input.datepicker',
			callback : function(e) {},
			eventType:"focusin.datePicker"
		},
		init : function(options) {
			var optionsArray = [];
			if($.isArray(options)){
				optionsArray = options;
			}else{
				optionsArray.push(options);
			}
			for (var i=0; i<optionsArray.length; i++){
				var options = optionsArray[i];
				//只传了一个function参数时
				if($.isFunction(options)){
					var fn = options;
					options = {};
					options.callback = fn;
				}
				options = $.extend(true, this.defaults, options);
				(function(options){
					var $el = $(options.el);
					var format = $el.attr('format')||'yyyy-mm-dd';
					/*
					 * 0 or 'hour' for the hour view
					 * 1 or 'day' for the day view
					 * 2 or 'month' for month view (the default)
					 */
					var startView = $el.attr('startView')||'month';
					var minView = $el.attr('minView')||'month';
					var maxView = $el.attr('maxView')||'decade';
					var position = $el.attr('position')||'bottom-right';
					var dateGroup = $el.attr('dateGroup');
					var callback = options.callback;
					var writeValue = $el.attr('writeValue');
					$el.each(function(){
						var $this = $(this);
						$this.on('focusin.datePicker',function(){
							$this.datetimepicker({
								format:format,
						        language:  'zh-CN',
								todayHighlight:true,
								todayBtn:dateGroup,
								autoclose: true,
								initialDate:new Date(),
								startView:startView,
								minView:minView,
								maxView:maxView,
								pickerPosition:position,
						        showMeridian: false,
						        writeValue:writeValue
						    });
							$this.datetimepicker('show');
							$this.off('focusin.datePicker');
						}).on('changeDate', function(ev){
					    	callback(ev);
					    });
					});
				})(options);
			}
		},
		remove:function(els){
			if(!arguments)	return;
			
			for(var i=0;i<arguments.length;i++){
				var el = arguments[i];
				$(el).datetimepicker('remove');
			}
		}
	};
	
	var layoutHelper = {
		/**
		 * 设置布局:1.设置高度；2.设置滚动条; 3. 添加固定事件
		 * @param key 页面区域标识,key值不可重复,具体key值定义请参考layout.getConfig()
		 */
		setLayout : function(targetEl, callbacks) {
			
			if(targetEl.indexOf("#print") == 0){
				$(targetEl).removeClass("scrollwrapper");//打印页面不需要滚动条
				return;
			}
			var obj = $(targetEl);
			if(!obj) return;
			var height = obj.attr('height');
			if(!height){
				var marginbottom = obj.attr('marginbottom') || 0;
				var top = obj.offset().top;
				height = $(window).height()- top - marginbottom;
			}
			if(obj.parents("#entitybox").get(0)!=null){	//模态窗中的滚动条
				var entitybox=obj.parents("#entitybox").find(".modal-content");	//模态窗对象
				var ebHeight=entitybox.height();	//模态窗高度
				var ebTop = entitybox.offset().top;	//模态窗上边距
				var ebBottom=$(window).height()-ebHeight-ebTop;	//模态窗底部边距
				height=height-ebBottom;	//滚动条元素高度减掉模态窗底部高度
			}
			obj.css('height',height);
			
			var theTheme = obj.attr('theme')?obj.attr('theme'):'darkblue';
			if (!obj.hasClass('mCustomScrollbar')) {
				if(callbacks){
					if(!callbacks.onScroll){
						callbacks.onScroll = function(){
							$('body .goto-top').removeClass('hide');
						};
					}
					callbacks.onTotalScrollBack = function(){
						$('body .goto-top').addClass('hide');
					} ;
					
					obj.mCustomScrollbar({
						theme:theTheme,
						callbacks:callbacks
					});
				}else{
					obj.mCustomScrollbar({
						theme:theTheme,
						callbacks:{
							onScroll : function(){
								$('body .goto-top').removeClass('hide');
							},
							onTotalScrollBack:function(){
								$('body .goto-top').addClass('hide');
							} 
						}
					});
				}
				
				if(targetEl == '#chat-container'){ //微信默认消息显示 滚动条在最底部
					obj.mCustomScrollbar("update");
					obj.mCustomScrollbar("scrollTo","bottom"); 
				}
				setTimeout(function(){
					$('body .goto-top').addClass('hide');
				}, 200);
			}
		},
		destroy:function(targetEl){
			$(targetEl).mCustomScrollbar("destroy");
		}
		
	};
	
	/**
	 * 对外暴露的函数
	 */
	var Utils = {
			
		layout : function(targetEl, callbacks) {
			layoutHelper.setLayout(targetEl, callbacks);
		},
		
		/**
		 * 回到顶部事件
		 * @Param targetEl 添加滚动条的id
		 */
		gotoTop:function(targetEl, callback){
			if(!targetEl){
				$(".mCustomScrollbar").mCustomScrollbar("update");
				$(".mCustomScrollbar").mCustomScrollbar("scrollTo","top");
			}else if($(targetEl).hasClass('mCustomScrollbar')){
				$(targetEl).mCustomScrollbar("update");
				$(targetEl).mCustomScrollbar("scrollTo","top");
			}
			if(callback){
				callback();
			};
			setTimeout(function(){
				$('body .goto-top').addClass('hide');
			}, 200);
		},
		
		destroyLayout : function(targetEl) {
			layoutHelper.destroy(targetEl);
		},
		/**
		 * @param key 模块唯一标识，命名规则：包名+文件名（无扩展名） 如home.left
		 * @param json(optional) 用于渲染模版的json数据,改参数可为空，为空时直接返回模版内容
		 */
		template :function(key,json){
			var tpl = templates[key];
			if(json)	return _.template(tpl,json);
			return tpl;
		},
		
		/**
		 * 日历控件
		 */
		datepicker:function(options){
			datepickerHelper.init(options);
		},
		removeDatepicker:function(els){
			datepickerHelper.remove(els);
		},
		/**
		 * alert弹出提示框
		 * @param message 	alert提示内容
		 * @param callback	回调函数
		 */
		alert:function(message,callback){
			callback = callback||function(){};
			bootbox.alert(message, callback);
		},
		/**
		 * 确认框
		 * @param message 	消息内容
		 * @param callback	回调函数，回调函数的参数result为用户选择的结果（确定为true，取消为false）
		 */
		confirm:function(message,callback){
			bootbox.confirm(message, function(result){
				if(callback)	callback(result);
			});
		},
		/**
		 * 弹出输入框
		 * @param message 	消息内容
		 * @param callback	回调函数，回调函数的参数result为用户用户输入的内容
		 */
		prompt:function(message,callback){
			bootbox.prompt(message, function(result){
				if(callback)	callback(result);
			});
		},
		/**
		 * 消息提示框
		 * @param message 	消息内容
		 * @param title 	消息标题(可选参数)
		 * @param type 		消息类型（可选参数,info，error，success三种，默认为notice）
		 * @param needHide 是否需要自动隐藏消息框  ，true:自动隐藏，false:不自动隐藏
		 * */
		notify:function(message,title,type,needHide){
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
		},
		/**
		 *序列化表单数据 
		 */
		serialize:function(formEl){
			$form = $(formEl);
			var serialize = "";
			$form.find("input[type!='radio'],textarea").each(function(){
				var name = $(this).attr("name");
				var value = $(this).val();
				if(name&&value){
					serialize += name+"="+value.replace(/\r\n|\n/g, "")+"&";
				}
			});
			$form.find("input[type='radio']").each(function(){
				var name = $(this).attr("name");
				var value = $(this).val();
				if(name&&value&&$(this).prop("checked")){
					serialize += name+"="+value+"&";
				}
			});
			serialize = serialize.substring(0, serialize.length-1);
			return serialize;
		},
		/**
		 *json键值对转换函数 将序列化数据转换为json
		 */
		toSimpleJSONString:function(serialize){
			var map = "";
			if(serialize){
				var array = serialize.split("&");
				for(var i=0,j=array.length; i<j; i++){
					var array1 = array[i].split("=");
					if(array1[1]){
						map += "\""+array1[0]+"\":\""+array1[1]+"\",";
					}
				}
				if(map){
					map = map.substring(0,map.length-1);
					map = "{"+map+"}";
				}
			}
			return map;
		},
		
		/**
		 * 匹配替换字符串中的url连接
		 * @param messages
		 * @param message
		 * @returns
		 */
		initUrl:function(messages, message){
			if(!messages) return message;
			var seleted = '';
			for(var j =0; j < messages.length; j++){
				var msg = $.trim(messages[j]);
				if(seleted.indexOf(msg) >= 0) continue; //防止重复操作 
				if(msg.indexOf('http') == 0 || msg.indexOf('https') == 0 || msg.indexOf('ftp') == 0) //判断是否有http协议头
				{	var temp = message.split(msg);
					var tempmsg = ''; 
					for(var i =0; i < temp.length -1; i++){
						tempmsg += (temp[i]+'<a target="_blank" href="'+msg+'">'+msg+'</a>');
					}
					message =  tempmsg+temp[temp.length -1];
					//message = message.replace(re ,'<a target="_blank" href="'+msg+'">'+msg+'</a>');
					//message = message.replace(msg ,'<a target="_blank" href="'+msg+'">'+msg+'</a>');
				}else{ 
					var temp = message.split(msg);
					var tempmsg = ''; 
					for(var i =0; i < temp.length -1; i++){
						tempmsg += (temp[i]+'<a target="_blank" href="'+msg+'">'+msg+'</a>');
					}
					message =  tempmsg+temp[temp.length -1];
					//message = message.replace(re,'<a target="_blank" href="http://'+msg+'">'+msg+'</a>');
					//message = message.replace(msg,'<a target="_blank" href="http://'+msg+'">'+msg+'</a>');
				}	
				seleted += messages[j]+"  ";
			}
			 
			return message;
		},
		/**
		 * 处理message标签信息等
		 * @param message
		 * @returns
		 */
		initMessage:function(message){
			var view = this;
			message=message.replace(/</g,'&lt').replace(/>/g,'&gt').replace(/\r\n|\n/g, ' <br/>').replace(/ /g, '  ');
			//var parten1 = new RegExp('(((ht|f)tp(s?))\://)?(www.|([a-zA-Z]+[\.]{1}))[a-zA-Z0-9\-\.]+\.(com|edu|gov|mil|net|org|biz|info|name|museum|us|ca|uk|cn)(\.[a-zA-Z]+)?(\:[0-9]+)*(/($|[a-zA-Z0-9\.\,\;\?\'\\\+&amp;%\$#\=~_\-])+)*','g');
			var parten1 = new RegExp('(((ht|f)tp(s?))\://)?(www.|([a-zA-Z]+[\.]{1}))[a-zA-Z0-9\-\.]*(com|edu|gov|mil|net|org|biz|info|name|museum|us|ca|uk|cn)(/($|[a-zA-Z0-9\:\.\,\;\?\'\\\+&amp;%\$#\=~_\-])+)*','g');
			var mes1 = message.match(parten1);
			message = view.initUrl(mes1, message);
			return message;
		},
		
		
		/**
		 *序列化表单数据,并转换为json字符串 
		 */
		toJSONString:function(formEl){
			$form = $(formEl);
			return this.toSimpleJSONString(this.serialize(formEl));
		},
		/**
		 * 返回List<Map<String,String>>集合
		 */
		getList:function(el){
			var list ="";
			var obj = this;
			$(el).each(function(){
				var map1 = obj.toJSONString(this);
				if(!map1){
					return;
				}
				list += map1+',';
			});
			if(list){
				list = list.substring(0, list.length-1);
			}
			return "["+list+"]";
		},
		/**
		 * 返回Map<String,String>集合
		 */
		getMap:function(el){
			var list ="";
			var obj = this;
			$(el).each(function(){
				var map1 = obj.toJSONString(this);
				if(!map1){
					return;
				}
				list += map1.substring(0,map1.length-1).substring(1)+',';
			});
			if(list){
				list = list.substring(0, list.length-1);
			}
			return "{"+list+"}";
		},
		//上一行
	     getPrev:function(el){
	    	  var index = 0;
	    	  var list = $(el);
	    	  list.each(function(i){
	    		  if($(this).hasClass('active')){
	    			  index = i;
	    			  return;
	    		  }
	    	  });
	    	  if(index == 0){//当前行在第一行
	    		  return null;
	    	  }else{
	    		  return $(list[index-1]);
	    	  }
	      },
	      //下一行
	      getNext:function(el){
	    	  var index = 0;
	    	  var list = $(el);
	    	  list.each(function(i){
	    		  if($(this).hasClass('active')){
	    			  index = i;
	    			  return;
	    		  }
	    	  });
	    	  if(index == list.length-1){//当前行在最后一行
	    		  return null;
	    	  }else{
	    		  return $(list[index+1]);
	    	  }
	      },
	      /**
	       * 高亮显示指定行
	       */
	      highLight:function(el,id){
	    	  $(el+' li.active').removeClass('active');
	          $(el+' #'+id).addClass('active');
	          $(el+' #' + id).find('.title').focus();
	      },
	      /**
	       * 重新生成序号
	       */
	      rebuildSN:function(container){
	          var children = $(container).children();
	          container.data('index',children.length);
	          for(var i=0;i<children.length;i++){
	        	  $(children[i]).find('.sn').html(i+1);
	          }
	      },
	      
	      /**
	       * 将文本中的回车，换行，空格转换成html
	       */
	      convert2Html: function(text){
	    	  if(!text) return text;
	          return text.replace(/\r\n|\n/g, '<br/>').replace(/[ ]/g, '&nbsp;');
	      }
	      
	};

	module.exports = Utils;
});

