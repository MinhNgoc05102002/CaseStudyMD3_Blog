<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
                            <h1>Login</h1>
                        </div>
                    </div>
                    <form action="/log-in?action=submitLogin" method="post" name="login">
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="text" name="email"  class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password"  class="form-control" aria-describedby="emailHelp" placeholder="Enter Password">
                        </div>
                        <div class="form-group">
                            <p class="text-center">Forget password? <a href="/log-in?action=repass">Click here to re-pass</a></p>
                        </div>
                        <div class="col-md-12 text-center ">
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Login</button>
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
                            <p class="text-center">Don't have account? <a href="/log-in?action=register" id="signup">Sign up here</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
