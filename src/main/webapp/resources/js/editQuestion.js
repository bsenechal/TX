$(document).ready(function () {

    $('#question-form').validate({ // initialize the plugin
        rules: {
        	libelle: {
                required: true,
                minlength: 5,
                maxlength: 45
            },
            valMax: {
                required: true,
                number: true
            },
            ponderation: {
                required: true,
                number: true
            }
        }
    });
});