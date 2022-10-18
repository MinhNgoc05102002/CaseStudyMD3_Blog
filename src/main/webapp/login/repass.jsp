<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Repass</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <script src="../assets/js/script.js"></script>
    <link rel="stylesheet" href="../assets/css/style.css">

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-5 mx-auto">
            <div id="first">
                <div class="myform form ">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <a style="margin: 0" href="/">
                                <img style="width: 200px;"
                                     src="https://www.logoai.com/uploads/output/2021/12/19/07532bb8bd0171f0fa684ff1f4f7debf.jpg" alt="Mediumish">
                            </a>
                            <h1>Reset password</h1>
                        </div>
                    </div>
                    <form action="/log-in?action=submitRepass" method="post" name="repass">
                        <div class="form-group">
                            <label for="username">Username or Email</label>
                            <input type="text" name="username"  class="form-control" id="username" aria-describedby="emailHelp" placeholder="Enter username or email">
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber">PhoneNumber</label>
                            <input type="text" name="phoneNumber"  class="form-control" id="phoneNumber" aria-describedby="emailHelp" placeholder="Enter phone number">
                        </div>
                        <div class="form-group">
                            <label for="newpass">New password</label>
                            <input type="password" name="newpass"  class="form-control" id="newpass" aria-describedby="emailHelp" placeholder="Enter new password">
                        </div>
                        <div class="form-group">
                            <label for="retypepass">Password</label>
                            <input type="password" name="retypepass" id="retypepass"  class="form-control" aria-describedby="emailHelp" placeholder="Retype new password">
                        </div>
                        <div class="col-md-12 text-center ">
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Reset</button>
                        </div>
                        <div class="col-md-12 ">
                            <div class="login-or">
                                <hr class="hr-or">
                                <span class="span-or">or</span>
                            </div>
                        </div>
                        <%--                        <div class="col-md-12 mb-3">--%>
                        <%--                            <p class="text-center">--%>
                        <%--                                <a href="&lt;%&ndash;javascript:void();&ndash;%&gt;" class="google btn mybtn"><i class="fa fa-google-plus">--%>
                        <%--                                </i> Signup using Google--%>
                        <%--                                </a>--%>
                        <%--                            </p>--%>
                        <%--                        </div>--%>
                        <div class="form-group">
                            <p class="text-center"><a href="/log-in?action=login" id="signup">Back to login</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
