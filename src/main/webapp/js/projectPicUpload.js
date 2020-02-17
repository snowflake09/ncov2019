//图库管理
function initWebUploader(ctx, filePicker, fileList, groupNo) {
	var uploader = WebUploader.create({
		// 选完文件后，是否自动上传。
		auto : true,
		// swf文件路径
		swf : ctx + '/js/webuploader-0.1.5/Uploader.swf',
		// 文件接收服务端。
		server : ctx + '/biz/projectGroup/picUpload',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#' + filePicker,
		// 只允许选择图片文件。
		accept : {
			title : 'Images',
			extensions : 'jpg,jpeg,bmp,png',
			mimeTypes : 'image/jpg,image/jpeg,image/png'
		},
		formData : {
			groupNo : groupNo
		}
	});

	$.ajax({
		type : "GET",
		url : ctx + '/biz/projectGroup/picList',
		data : {
			groupNo : groupNo
		},
		dataType : "json",
		success : function(data) {
			if (data.length != 0) {
				for (var i = 0; i < data.length; i++) {
					var $li = $('<dl id="' + data[i].id
							+ '" class="file-item thumbnail">' + '<a href="'
							+ data[i].filePath + '"></a>' + '<img>'
							+ '<div class="delete"></div>' + '</dl>');
					$img = $li.find('img')
					$img.attr('src', data[i].filePath).height(100);
					$li.attr('fileId', data[i].id);
					$li.on('click', '.delete', function() {
						deletePic(ctx, $(this).parent().attr('fileId'));
						$(this).parent().remove();
					})
					$("#" + fileList).append($li);

				}
			}
		}
	});

	// 当有文件添加进来的时候
	uploader.on('fileQueued', function(file) {
		var $li = $('<dl id="' + file.id + '" class="file-item thumbnail">'
				+ '<img>' + '<div class="delete"></div>' + '</dl>');
		$img = $li.find('img')
		$li.on('click', '.delete', function() {
			uploader.removeFile(file);
			deletePic(ctx, $li.attr('fileId'));
			$(this).parent().remove();
		})
		// $list为容器jQuery实例
		$("#" + fileList).append($li);

		// 创建缩略图
		// 如果为非图片文件，可以不用调用此方法。
		// thumbnailWidth x thumbnailHeight 为 100 x 100
		uploader.makeThumb(file, function(error, src) {
			if (error) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}
			//$img.attr('src', src);
		}, 100, 100);
	});

	// 文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress', function(file, percentage) {
		var $li = $('#' + file.id), $percent = $li.find('.progress span');

		// 避免重复创建
		if (!$percent.length) {
			$percent = $('<p class="progress"><span></span></p>').appendTo($li).find('span');
		}
		$percent.css('width', percentage * 100 + '%');
	});

	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on('uploadSuccess', function(file, response) {
		$('#' + file.id).addClass('upload-state-done');
		$('#' + file.id).attr('fileId', response.obj.id);
		$('#' + file.id).find("img").attr('src',response.obj.filePath).height(100);
	});

	// 文件上传失败，显示上传出错。
	uploader.on('uploadError', function(file) {
		var $li = $('#' + file.id), $error = $li.find('div.error');

		// 避免重复创建
		if (!$error.length) {
			$error = $('<div class="error"></div>').appendTo($li);
		}
		$error.text('上传失败');
	});

	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').remove();
	});
}

// 删除图片
function deletePic(ctx, picId) {
	progressLoad();
	$.ajax({
		type : "POST",
		url : ctx + '/biz/projectGroup/picDelete',
		data : {
			picId : picId
		},
		dataType : "json",
		success : function(data) {
			progressClose();
		}
	});
}
