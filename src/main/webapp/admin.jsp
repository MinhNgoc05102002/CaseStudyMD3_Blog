<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="assets/img/favicon.ico">
    <title>Mediumish - A Medium style template by WowThemes.net</title>
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Fonts -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Righteous%7CMerriweather:300,300i,400,400i,700,700i" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="assets/css/mediumish.css" rel="stylesheet">
    <!-- Icon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <link rel="stylesheet" href="assets/css/header.css">
    <!-- admin.css -->
    <link rel="stylesheet" href="assets/css/admin.css">
</head>
<body>

<!-- Begin Nav
================================================== -->
<div class="header container">
    <div class="header__top row">
        <div class="header__social col-md-4 col-lg-3">
            <a href="" class="header__social-link"><i class="fab fa-facebook"></i></a>
            <a href="" class="header__social-link"><i class="fab fa-instagram"></i></a>
            <a href="" class="header__social-link"><i class="fab fa-linkedin"></i></a>
            <a href="" class="header__social-link"><i class="fab fa-facebook-messenger"></i></a>
        </div>
        <div class="header__search col-md-4 col-lg-6">
            <div class="admin__title">
                <a style="color: black" href="/">Mediumish Manager</a>
            </div>
            <!-- <form class="form-inline">
                <input class="form-control" type="text" placeholder="Search">
                <span><i class="fas fa-search"></i></span>
            </form> -->
        </div>
        <div class="header__user col-md-4 col-lg-3">
            <%
                if(session.getAttribute("fullName") == null){
            %>
                <a href="/log-in?action=login" class="header__user-link"><span>Login</span><i class="fas fa-sign-in-alt"></i></a>
                <a href="/log-in?action=register" class="header__user-link"><span>Register</span><i class="fas fa-user-plus"></i></a>
            <%} else {%>
            <p style="display: inline; font-size: 1.1rem; margin-right: 10px;" class="header__user-link"><span>${sessionScope.fullName}</span><i class="fas fa-users-cog"></i></p>
            <a href="/log-in?action=login" class="header__user-link"><span>Log out</span><i class="fas fa-sign-out-alt"></i></a>
            <%}%>
        </div>
    </div>
    <hr>
</div>
<!-- End Nav
================================================== -->

<!-- Begin Article
================================================== -->
<div class="container">
    <div class="row">

        <!-- Begin Fixed Left Share -->
        <div class="left__container col-md-3 col-lg-3">
            <div class="left_nav">
                <p class="left_nav-title">Manager</p>
                <ul class="left_nav-menu">
                    <li class="left_nav-item">
                        <label for="blog_manager" class="left_nav-link now">
                            <i class="fas fa-chevron-right"></i>
                            Blog manager
                        </label>
                    </li>
                    <li class="left_nav-item">
                        <label for="category_manager" class="left_nav-link">
                            <i class="fas fa-chevron-right"></i>
                            Category manager
                        </label>
                    </li>
                    <li class="left_nav-item">
                        <label for="account_manager" class="left_nav-link">
                            <i class="fas fa-chevron-right"></i>
                            Account manager
                        </label>
                    </li>
                </ul>
                <p class="left_nav-title">Other Option</p>
                <ul class="left_nav-menu">
                    <li class="left_nav-item">
                        <a href="" class="left_nav-link">
                            <i class="fas fa-chevron-right"></i>
                            Blocked account
                        </a>
                    </li>
                    <li class="left_nav-item">
                        <a href="" class="left_nav-link">
                            <i class="fas fa-chevron-right"></i>
                            Deleted blog
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- End Fixed Left Share -->

        <!-- Begin Post -->
        <div class="right__content col-md-9 col-lg-9 col-sm-12">
            <input class="admin_choice" type="radio" name="manager" id="blog_manager">
            <input class="admin_choice" type="radio" name="manager" id="category_manager">
            <input class="admin_choice" type="radio" name="manager" id="account_manager">
            <div class="blog__manager">
                <!-- <div class="manager_title">Blog manager</div> -->
                <table class="table">
                    <tr class="heading_table">
                        <th style="border-radius: 5px 0 0 0;">BlogID</th>
                        <th>AccountID</th>
                        <th>Create At</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>Category</th>
                        <th>Image</th>
                        <th>Status</th>
                        <th style="border-radius: 0 5px 0 0;"> </th>
                    </tr>
                    <c:forEach items = '${requestScope["listBlogCategory"]}' var = "blog">
                    <tr class="body_table">
                        <td>${blog.getKey().getBlogID()}</td>
                        <td>${blog.getKey().getAccountID()}</td>
                        <td>${blog.getKey().getCreateAt()}</td>
                        <td>${blog.getKey().getTitle()}</td>
                        <td>${blog.getKey().getContent().substring(0, blog.getKey().getContent().length() > 50 ? 50 : blog.getKey().getContent().length() )}...</td>
                        <td>${blog.getValue()}</td>
                        <td><img src="${blog.getKey().getImage()}" style="width: 100px;"></td>
                        <td style="text-align: center">
                            <c:if test = "${blog.getKey().getStatus()==1}">
                                <a class="eye-icon" href="/admin?action=hideBlog&blogId=${blog.getKey().getBlogID()}"><i class="fas fa-eye"></i></a>
                            </c:if>
                            <c:if test = "${blog.getKey().getStatus()==0}">
                                <a class="eye-icon" href="/admin?action=hideBlog&blogId=${blog.getKey().getBlogID()}"><i class="fas fa-eye-slash"></i></a>
                            </c:if>
                        </td>
                        <td style="text-align: center"><a class="delIcon" href="/admin?action=deleteBlog&blogId=${blog.getKey().getBlogID()}"><i class="fas fa-times"></i></a></td>
                    </tr>
                    </c:forEach>

                </table>
            </div>

            <!-- Category manager -->

            <div class="category__manager">
                <!-- <div class="manager_title">Blog manager</div> -->
                <table class="table">
                    <tr class="heading_table">
                        <th style="border-radius: 5px 0 0 0;">CategoryID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Edit</th>
                        <th style="border-radius: 0 5px 0 0;">x</th>
                    </tr>
                    <c:forEach items = '${requestScope["listAllCategory"]}' var = "category">
                    <tr class="body_table">
                        <td>${category.getCategoryID()}</td>
                        <td>${category.getName()}</td>
                        <td>${category.getDescription()}</td>
                        <td style="text-align: center"><a class="editIcon" href=""><i class="fas fa-edit"></i></a></td>
                        <td style="text-align: center"><a class="delIcon" href=""><i class="fas fa-times"></i></a></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>


            <div class="account__manager">
                <!-- <div class="manager_title">Blog manager</div> -->
                <table class="table">
                    <tr class="heading_table">
                        <th style="border-radius: 5px 0 0 0;">AccountID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Fullname</th>
                        <th>Password</th>
                        <th>PhoneNumber</th>
                        <th>Description</th>
                        <th>Role</th>
                        <th style="border-radius: 0 5px 0 0;"> </th>
                    </tr>
                    <c:forEach items = '${requestScope["listAllAccounts"]}' var = "account">
                    <tr class="body_table">
                        <td>${account.getAccountID()}</td>
                        <td>${account.getUsername()}</td>
                        <td>${account.getEmail()}</td>
                        <td>${account.getFullname()}</td>
                        <td>${account.getPassword()}</td>
                        <td>${account.getPhoneNumber()}</td>
                        <td>${account.getDescription().substring(0, account.getDescription().length() > 50 ? 50 : account.getDescription().length() )}...</td>
                        <td style="text-align: center">${account.getRole()==1?"admin":"user"}</td>
                        <td style="text-align: center">
                            <c:if test = "${account.getStatus()==1}">
                                <a class="enableIcon" href="/admin?action=blockAccount&accId=${account.getAccountID()}"><i class="fas fa-check-circle"></i></a>
                            </c:if>
                            <c:if test = "${account.getStatus()==0}">
                                <a class="blockIcon" href="/admin?action=blockAccount&accId=${account.getAccountID()}"><i class="fas fa-ban"></i></a>
                            </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>

        </div>
        <!-- End Post -->

    </div>
</div>
<!-- End Article
================================================== -->

<!-- Begin Footer
================================================== -->
<div class="container">
    <div class="footer">
        <p class="pull-left">
            Copyright &copy; 2022 Blog SyNgoc
        </p>
        <p class="pull-right">
            Mediumish Theme by <a target="_blank" href="https://www.wowthemes.net">SyNgoc.net</a>
        </p>
        <div class="clearfix">
        </div>
    </div>
</div>
<!-- End Footer
================================================== -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="assets/js/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="assets/js/mediumish.js"></script>
</body>
</html>
