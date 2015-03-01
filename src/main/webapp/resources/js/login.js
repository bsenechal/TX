$(document).ready(function () {

    $('#login-form').validate({ // initialize the plugin
        rules: {
        	username: {
                required: true,
                email : true
            },
            password: {
                required: true,
                minlength: 6
            }
        }
    });
});