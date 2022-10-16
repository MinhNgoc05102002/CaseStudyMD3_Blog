<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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
    <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="assets/css/mediumish.css" rel="stylesheet">
    <!-- Icon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <link rel="stylesheet" href="assets/css/header.css">
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
            <form class="form-inline">
                <input class="form-control" type="text" placeholder="Search">
                <span><i class="fas fa-search"></i></span>
            </form>
        </div>
<%--        <div class="header__user col-md-4 col-lg-3">--%>
<%--            <a href="/login/login.jsp" class="header__user-link"><span>Login</span><i class="fas fa-sign-in-alt"></i></a>--%>
<%--            <a href="/login/register.jsp" class="header__user-link"><span>Register</span><i class="fas fa-user-plus"></i></a>--%>
<%--        </div>--%>
        <div class="header__user col-md-4 col-lg-3">
            <%
                if(session.getAttribute("fullName") == null){
            %>
            <a href="/log-in?action=login" class="header__user-link"><span>Login</span><i class="fas fa-sign-in-alt"></i></a>
            <a href="/log-in?action=register" class="header__user-link"><span>Register</span><i class="fas fa-user-plus"></i></a>
            <%} else {%>
            <a href="/author?action=authorPage&currentUser=${sessionScope.username}" style="display: inline; font-size: 1.1rem; margin-right: 10px;" class="header__user-link"><span>${sessionScope.fullName}</span><i class="fas fa-users-cog"></i></a>
            <a href="/log-in?action=login" class="header__user-link"><span>Log out</span><i class="fas fa-sign-out-alt"></i></a>
            <%}%>
        </div>
    </div>
    <hr>
    <div class="mainheading">
        <h1 class="sitetitle">Mediumish</h1>
        <p class="lead">
            A website with medium style, simply perfect for bloggers
        </p>
    </div>
    <!-- Nav Bar -->
    <div class="navbar">
        <ul class="nav__list">
            <li class="nav__item"><a href="" class="nav__item-link">Home</a></li>
            <li class="nav__item"><a href="" class="nav__item-link">Stories</a></li>
            <li class="nav__item"><a href="" class="nav__item-link">Category</a></li>
            <li class="nav__item"><a href="" class="nav__item-link">Author</a></li>
        </ul>
    </div>
    <!-- End nav bar -->
</div>
<!-- End Nav
================================================== -->


<div class="container">
    <!-- Begin Featured
    ================================================== -->
    <section class="featured-posts">
        <div class="section-title" style="margin-top: 30px;">
            <h2><span>Featured</span></h2>
        </div>
        <div class="card-columns listfeaturedtag">
            <c:forEach items = "${FeatureBlogAuthor}" var = "aBlogAuthor">
                <c:if test = "${aBlogAuthor.getKey().getStatus() == 1}">
                <!-- begin post -->
                    <div class="card">
                        <div class="row">
                            <div class="col-md-5 wrapthumbnail">
                                <a href="/post?blogID=${aBlogAuthor.getKey().getBlogID()}&accountID=${aBlogAuthor.getValue().getAccountID()}">
                                    <div class="thumbnail" style="background-image:url(${aBlogAuthor.getKey().getImage()});">
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-7">
                                <div class="card-block">
                                    <h2 class="card-title"><a href="/post?blogID=${aBlogAuthor.getKey().getBlogID()}&accountID=${aBlogAuthor.getValue().getAccountID()}">${aBlogAuthor.getKey().title}</a></h2>
                                    <h4 class="card-text">${aBlogAuthor.getKey().getContent().substring(0, aBlogAuthor.getKey().getContent().length() > 100 ? 100 : aBlogAuthor.getKey().getContent().length()) }...</h4>
                                    <div class="metafooter">
                                        <div class="wrapfooter">
                                    <span class="meta-footer-thumb">
                                    <a href="/author?action=authorPage&currentUser=${aBlogAuthor.getValue().username}"><img class="author-thumb" src="https://www.gravatar.com/avatar/e56154546cf4be74e393c62d1ae9f9d4?s=250&amp;d=mm&amp;r=x" alt="Sal"></a>
                                    </span>
                                            <span class="author-meta">
                                    <span class="post-name"><a href="/author?action=authorPage&currentUser=${aBlogAuthor.getValue().username}">${aBlogAuthor.getValue().getFullname()}</a></span><br/>
                                    <span class="post-date">${aBlogAuthor.getKey().getCreateAt()}</span><span class="dot"></span><span class="post-read">6 min read</span>
                                    </span>
                                            <span class="post-read-more"><a href="/post?blogID=${aBlogAuthor.getKey().getBlogID()}&accountID=${aBlogAuthor.getValue().getAccountID()}" title="Read Story"><svg class="svgIcon-use" width="25" height="25" viewbox="0 0 25 25"><path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path></svg></a></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                <!-- end post -->
                </c:if>
            </c:forEach>

        </div>
    </section>
    <!-- End Featured
    ================================================== -->

    <!-- Begin List Posts
        ================================================== -->
    <section class="recent-posts">
        <div class="section-title">
            <h2><span>All Stories</span></h2>
        </div>
        <div class="card-columns listrecent">
            <c:forEach items = '${requestScope["blogAuthor"]}' var = "aBlogAuthor">
                <c:if test = "${aBlogAuthor.getKey().getStatus() == 1}">
                    <!-- begin post -->
                    <div class="card">
                        <a href="/post?blogID=${aBlogAuthor.getKey().getBlogID()}&accountID=${aBlogAuthor.getValue().getAccountID()}">
                            <img class="img-fluid" src="${aBlogAuthor.getKey().getImage()}" alt="Post Image">
                        </a>
                        <div class="card-block">
                            <h2 class="card-title"><a href="/post?blogID=${aBlogAuthor.getKey().getBlogID()}&accountID=${aBlogAuthor.getValue().getAccountID()}">${aBlogAuthor.getKey().getTitle()}</a></h2>
                            <h4 class="card-text">${aBlogAuthor.getKey().getContent().substring(0, aBlogAuthor.getKey().getContent().length() > 120 ? 120 : aBlogAuthor.getKey().getContent().length()) }...</h4>
                            <div class="metafooter">
                                <div class="wrapfooter">
                            <span class="meta-footer-thumb">
                            <a href="/author?action=authorPage&currentUser=${aBlogAuthor.getValue().username}"><img class="author-thumb" src="https://www.gravatar.com/avatar/e56154546cf4be74e393c62d1ae9f9d4?s=250&amp;d=mm&amp;r=x" alt="Sal"></a>
                            </span>
                                    <span class="author-meta">
                            <span class="post-name"><a href="/author?action=authorPage&currentUser=${aBlogAuthor.getValue().username}">${aBlogAuthor.getValue().getFullname()}</a></span><br/>
                            <span class="post-date">${aBlogAuthor.getKey().getCreateAt()}</span><span class="dot"></span><span class="post-read">6 min read</span>
                            </span>
                                    <span class="post-read-more"><a href="/post?blogID=${aBlogAuthor.getKey().getBlogID()}&accountID=${aBlogAuthor.getValue().getAccountID()}" title="Read Story"><svg class="svgIcon-use" width="25" height="25" viewbox="0 0 25 25"><path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path></svg></a></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end post -->
                </c:if>

            </c:forEach>

        </div>
    </section>
    <!-- End List Posts
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
    <!-- End Footer
    ================================================== -->
</div>
<!-- /.container -->

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="assets/js/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
