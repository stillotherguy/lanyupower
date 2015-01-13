/*
 * 粘贴 粘贴板上的图片 实现上传
 * 目前支持IE11 FireFox Chrome粘贴图片，FireFox支持粘贴文件上传
 */
(function() {
	var $, getImageData, readImagesFromEditable;

	$ = jQuery;

	readImagesFromEditable = function(element, cb) {
		return setTimeout((function() {
			return $(element).find('img').each(function(i, img) {
				return getImageData(img.src, cb);
			});
		}), 1);
	};

	/*
	 * 粘贴图片上传
	 * 返回值包括dataURl和content type
	 */
	getImageData = function(src, cb) {
		var loader;
		loader = new Image();
		loader.onload = function() {
			var canvas, ctx, dataURL;
			canvas = document.createElement('canvas');
			canvas.width = loader.width;
			canvas.height = loader.height;
			ctx = canvas.getContext('2d');
			ctx.drawImage(loader, 0, 0, canvas.width, canvas.height);
			dataURL = null;
			try {
				dataURL = canvas.toDataURL('image/png');
			} catch (_error) {

			}
			if (dataURL) {
				return cb({
					dataURL : dataURL,
					dataContentType : 'image/png'
				});
			}
		};
		return loader.src = src;
	};
	
	/*
	 * 粘贴文件上传
	 * 返回值包括dataURl,file name,content type
	 */
	getFileData = function(src,data,cb) {
		return cb({
			pataFileName : data.name,
			dataContentType : data.type,
			dataURL : src
		});
	};

	/*
	 * 直接传div
	 */
	$.paste = function(div) {
		$(div).on('paste', function(ev) {
			//保留粘贴之前 div内的文本数据
			var content = $(div).text();
			var clipboardData, item, reader,  _i, _len, _ref, _ref1, _ref3 = this;
			if (((_ref = ev.originalEvent) != null ? _ref.clipboardData : void 0) != null) {
				clipboardData = ev.originalEvent.clipboardData;
				if (clipboardData.items) {//Chrome浏览器上传文件(目前只支持图片上传)
					_ref1 = clipboardData.items;
					for (_i = 0, _len = _ref1.length; _i < _len; _i++) {
						item = _ref1[_i];
						if (item.type === 'text/plain') {
							item.getAsString(function(string) {
								content += string;
							});
							return;
						}
						reader = new FileReader();
						var blob = item.getAsFile();
						reader.onload = function(event) {
							return getFileData(event.target.result,blob, function(data) {
								return $(div).trigger('pasteFile', data);
							});
						};
						reader.readAsDataURL(blob);
					}
				} else {//Firefox浏览器
					if (clipboardData.types.length > 0) {//上传文件
						for(var k=0; k< clipboardData.files.length; k++){
							var file = clipboardData.files[k];
							var reader = new FileReader();
				            reader.onload=function(event){
				            	return getFileData(event.target.result, file, function(data) {
									return $(div).trigger('pasteFile',data);
								});
				            };
				            reader.readAsDataURL(file);
						}
						//处理div内文本内容
						text = clipboardData.getData('Text');
						if(text != null){
							content = content + text;
							return;
						}
					} else {//粘贴截图等粘贴板上的图片内容
						readImagesFromEditable(div, function(data) {
							return $(div).trigger('pasteImage', data);
						});
					}
				}
			}
			if (clipboardData = window.clipboardData) {//IE
				if ((_ref3 = (text = clipboardData.getData('Text'))) != null ? _ref3.length: void 0) {//处理div内文本内容
					content = content + text;
					return;
				} else {//粘贴截图等粘贴板上的图片内容
					readImagesFromEditable(div, function(data) {
						return $(div).trigger('pasteImage', data);
					});
				}
			}
			return setTimeout((function() {
				return $(div).text(content);
			}), 2);
		});
		return $(div);
	};

}).call(this);
