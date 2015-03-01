function verif(classeId, classDiv){
	$(classDiv).removeClass();
	$(classeId).next('span').remove(); 
	
	$(classDiv).addClass("form-group");
		if ($(classeId).val() == '') {
			$(classDiv).addClass("has-error has-feedback");
			$(classeId).after("<span class=\"glyphicon glyphicon-remove form-control-feedback\" aria-hidden=\"true\"></span>");
		}
		else {
			$(classDiv).addClass("has-success has-feedback");
			$(classeId).after("<span class=\"glyphicon glyphicon-ok form-control-feedback\" aria-hidden=\"true\"></span>");
		}
}