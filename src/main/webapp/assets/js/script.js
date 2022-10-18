console.log("here")
$("#signup").click(function() {
    $("#first").fadeOut("fast", function() {
        $("#second").fadeIn("fast");
    });
});

$("#signin").click(function() {
    $("#second").fadeOut("fast", function() {
        $("#first").fadeIn("fast");
    });
});



$(function() {
    $("form[name='login']").validate({
        rules: {

            email: {
                required: true,
                // email: true
            },
            password: {
                required: true,

            }
        },
        messages: {
            email: "Please enter email",

            password: {
                required: "Please enter password",

            }

        },
        submitHandler: function(form) {

            console.log("here3")
            form.submit();
            console.log("here2")
        }
    });
});

$(function() {
    $("form[name='repass']").validate({
        rules: {

            username: {
                required: true,
                // email: true
            },
            phoneNumber: {
                required: true,

            },
            newpass: {
                required: true,
                validatePassword: true,
                minlength: 6
            },
            retypepass: {
                required: true,
                equalTo: "#newpass",
                minlength: 6
            }
        },
        messages: {
            username: "Please enter your username",
            phoneNumber: "Please enter your phoneNumber",
            newpass: {
                required: "Please provide a new password",
                minlength: "Your password must be at least 6 characters long"
            },
            retypepass: {
                required: "Please provide a password again",
                minlength: "Your password must be at least 6 characters long"
            },
        },

        submitHandler: function(form) {
            form.submit();
        }
    });
});

$.validator.addMethod("validatePassword", function (value, element) {
    return this.optional(element) || /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,16}$/i.test(value);
}, "Please enter a password between 6 and 16 characters including uppercase, lowercase and at least some letters");


$(function() {

    $("form[name='registration']").validate({
        rules: {
            username: "required",
            fullName: "required",
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                validatePassword: true,
                minlength: 6
            },
            passwordAgain: {
                required: true,
                equalTo: "#password",
                minlength: 6
            }
        },

        messages: {
            username: "Please enter your username",
            fullName: "Please enter your fullName",
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 6 characters long"
            },
            passwordAgain: {
                required: "Please provide a password again",
                minlength: "Your password must be at least 6 characters long"
            },
            email: "Please enter a valid email address"
        },

        submitHandler: function(form) {
            form.submit();
        }
    });
});
