$(document).ready(function () {

    $('#user-form').validate({ // initialize the plugin
        rules: {
        	email: {
                required: true,
                email: true,
                minlength: 5,
                maxlength: 45
            },
            lastname: {
                required: true,
                minlength: 5,
                maxlength: 45
            },
            firstname: {
                required: true,
                minlength: 5,
                maxlength: 45
            },
            telephone: {
                required: true,
                number: true,
                minlength: 10
            },
            password: {
                required: true,
                minlength: 6
            },
            confirmation: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            }      
        }
    });

});