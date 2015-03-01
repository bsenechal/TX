$(document).ready(function () {

    $('#evaluation-form').validate({ // initialize the plugin
        rules: {
        	note: {
                required: true,
                number: true
            },
        }
    });

});