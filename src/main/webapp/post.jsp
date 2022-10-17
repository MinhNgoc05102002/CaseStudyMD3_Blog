<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10/2/2022
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <!-- Post.css -->
    <link rel="stylesheet" href="assets/css/post.css">
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
<%--            <form class="form-inline">--%>
<%--                <input class="form-control" type="text" placeholder="Search">--%>
<%--                <span><i class="fas fa-search"></i></span>--%>
<%--            </form>--%>
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

    <!-- Nav Bar -->
    <div class="navbar">
        <ul class="nav__list">
            <li class="nav__item"><a href="/?action=" class="nav__item-link">Home</a></li>
            <li class="nav__item"><a href="" class="nav__item-link">Stories</a></li>
            <li class="nav__item"><a href="" class="nav__item-link">Category</a></li>
            <li class="nav__item"><a href="" class="nav__item-link">Author</a></li>
        </ul>
    </div>
    <!-- End nav bar -->
</div>
<!-- End Nav
================================================== -->

<!-- Begin Article
================================================== -->
<div class="container">
    <div class="row">

        <!-- Begin Fixed Left Share -->
        <div class="left-info col-md-3 col-lg-3">
            <div class="author">
                <!-- Author Infomation -->
                <div class="author-avt">
                    <a href="/author?action=authorPage&currentUser=${author.getUsername()}"><img class="author-thumb author-img" src="https://www.gravatar.com/avatar/e56154546cf4be74e393c62d1ae9f9d4?s=250&amp;d=mm&amp;r=x" alt="author avt"></a>
                </div>
                <div class="author-info">
                    <a class="author-name" href="/author?action=authorPage&currentUser=${author.getUsername()}">${author.fullname}</a>
                    <p class="author-description">${author.getDescription()}. <b>" Mediumish "</b> theme that you're currently previewing. Developing professional premium themes, templates, plugins, scripts since 2012.</p>

                </div>
                <!-- End Author Infomation -->
            </div>
            <div class="share">
                <p>Share</p>
                <div class="share-icon">
                    <a href="" class="share-icon-link"><i class="fab fa-facebook"></i></a>
                    <span class="line">|</span>
                    <a href="" class="share-icon-link"><i class="fab fa-instagram"></i></a>
                    <span class="line">|</span>
                    <a href="" class="share-icon-link"><i class="fab fa-twitter"></i></a>
                    <span class="line">|</span>
                    <a href="" class="share-icon-link"><i class="fab fa-facebook-messenger"></i></a>
                </div>
            </div>
        </div>
        <!-- End Fixed Left Share -->

        <!-- Begin Post -->
        <div class="right-content col-md-9 col-lg-9 col-sm-12">
            <div class="mainheading">
                <!-- Post Categories -->
                <div class="post-tags">
                    <ul class="tags">
                        <c:forEach items = "${categoryList}" var = "category">
                            <li>
                                <a href="#">${category.getName()}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <!-- End Categories -->

                <h1 class="posttitle">${blog.getTitle()}</h1>
                <span class="post-date">${blog.getCreateAt()}</span><span class="dot"></span><span class="post-read">6 min read</span>
            </div>

            <!-- Begin Featured Image -->
            <img class="featured-image img-fluid" src="${blog.getImage()}" alt="">
            <!-- End Featured Image -->

            <!-- Begin Post Content -->
            <div class="article-post">
                <p>
                    ${blog.getContent()}
                </p>
            </div>
            <!-- End Post Content -->

            <!-- Begin Tags -->
            <!-- <div class="after-post-tags">
                <ul class="tags">
                    <li><a href="#">Design</a></li>
                    <li><a href="#">Growth Mindset</a></li>
                    <li><a href="#">Productivity</a></li>
                    <li><a href="#">Personal Growth</a></li>
                </ul>
            </div> -->
            <!-- End Tags -->

        </div>
        <!-- End Post -->

    </div>
</div>
<!-- End Article
================================================== -->

<div class="hideshare"></div>

<!-- Begin Related
================================================== -->
<div class="graybg">
    <div class="container">
        <div class="row listrecent listrelated">
            <c:forEach items = "${relatedBlogAuthorList}" var = "aRelatedBlogAuthor">
            <!-- begin post -->
            <div class="col-md-4">
                <div class="card">
                    <a href="/post?blogID=${aRelatedBlogAuthor.getKey().getBlogID()}&accountID=${aRelatedBlogAuthor.getValue().getAccountID()}">
                        <img class="img-fluid img-thumb" src="${aRelatedBlogAuthor.getKey().getImage()}" alt="">
                    </a>
                    <div class="card-block">
                        <h2 class="card-title"><a href="/post?blogID=${aRelatedBlogAuthor.getKey().getBlogID()}&accountID=${aRelatedBlogAuthor.getValue().getAccountID()}">${aRelatedBlogAuthor.getKey().title}</a></h2>
                        <div class="metafooter">
                            <div class="wrapfooter">
								<span class="meta-footer-thumb">
								<a href="/author?action=authorPage&currentUser=${aRelatedBlogAuthor.getValue().username}"><img class="author-thumb" src="https://www.gravatar.com/avatar/e56154546cf4be74e393c62d1ae9f9d4?s=250&amp;d=mm&amp;r=x" alt="Sal"></a>
								</span>
                                <span class="author-meta">
								<span class="post-name"><a href="/author?action=authorPage&currentUser=${aRelatedBlogAuthor.getValue().username}">${aRelatedBlogAuthor.getValue().fullname}</a></span><br/>
								<span class="post-date">${aRelatedBlogAuthor.getKey().getCreateAt()}</span><span class="dot"></span><span class="post-read">6 min read</span>
								</span>
                                <span class="post-read-more"><a href="/post?blogID=${aRelatedBlogAuthor.getKey().getBlogID()}&accountID=${aRelatedBlogAuthor.getValue().getAccountID()}" title="Read Story"><svg class="svgIcon-use" width="25" height="25" viewbox="0 0 25 25"><path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path></svg></a></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end post -->
            </c:forEach>
        </div>
    </div>
</div>
<!-- End Related Posts
================================================== -->



<!-- Begin Footer
================================================== -->
<div class="container">
    <div class="footer">
        <p class="pull-left">
            Copyright &copy; 2022 Mediumish
        </p>
        <p class="pull-right">
            Mediumish theme by <a target="_blank" href="https://www.wowthemes.net">Ngoc.provip</a>
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

