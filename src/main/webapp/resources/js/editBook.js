$(document).ready(function () {

    $('#book-form').validate({ // initialize the plugin
        rules: {
        	title: {
                required: true,
                minlength: 5,
                maxlength: 45
            },
            autor: {
                required: true,
                minlength: 5,
                maxlength: 45
            },
            type: {
                required: true,
                minlength: 5,
                maxlength: 45
            },
            description: {
                required: true,
                minlength: 5
            },
        }
    });

});