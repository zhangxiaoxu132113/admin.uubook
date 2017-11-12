/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	
	config.filebrowserUploadUrl= "/ecp/topic/uploadImg";
    config.filebrowserImageUploadUrl= "/ecp/topic/uploadImg"; //要上传的Controller


	var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    //config.filebrowserImageUploadUrl = projectName+'/system/userArticleFileUpload.do'; //固定路径
    //设置toolbar的样式
	config.toolbar = 'Full';
	//设置文本编辑启动额高度
	config.height = 500;
	
	
	config.toolbar_Full = [ 
	                       
                   ['Source','-','Save','NewPage','Preview','-','Templates'], 
                   
                   ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print'], 
                   
                   ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'], 
                   
//                   ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'], 
//                   ['Link','Unlink','Anchor'], 
                   
                   ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'], 
                   
                   '/', 
                   
                   ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'], 
                   
                   ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'], 
                   
                   ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'], 
                   
                   ['Styles','Format','Font','FontSize'], 
                   
                   ['TextColor','BGColor'] 
                   
                   ];
};













		//设置文件的浏览路径 
		//editorObj.config.filebrowserBrowseUrl = ""; 
		//设置图片的浏览路径 
		//editorObj.config.filebrowserImageBrowseUrl = ""; 
		//设置flash文件浏览路径 
		//editorObj.config.filebrowserFlashBrowseUrl = ""; 
		//设置文件上传文件地址 
		//editorObj.config.filebrowserUploadUrl = ""; 
		//设置图片文件上传地址 
		//editorObj.config.filebrowserImageUploadUrl = "新地址"; 
		//设置flash文件上传地址 
		//editorObj.config.filebrowserFlashUploadUrl = ""; 




