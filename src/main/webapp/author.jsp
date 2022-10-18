<%@ page import="model.Account" %>
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
    <!-- Custom styles for this template -->
    <link href="assets/css/mediumish.css" rel="stylesheet">
    <!-- Icon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <link rel="stylesheet" href="assets/css/header.css">

    <!-- author css -->
    <link rel="stylesheet" href="./assets/css/author.css">
    <%--    dialog css--%>
    <link rel="stylesheet" href="./assets/css/dialog.css">
    <style type="text/css">
        .catechoice {
            display: none;

        }
        .labelCate {
            background-color: #dedede;
            border-radius: 3px;
            margin: 2px;
            padding: 5px;
        }
        .cate {
            width: 300px;
            height: 120px;
            border: 1px solid black;
            border-radius: 2px;
            padding: 5px;
            margin-bottom: 10px;
        }
        input:checked + label {
            background-color: #8fcafe;
        }
        .img_dialog {
            margin-bottom: 5px;
        }
    </style>
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
        <%--        <div class="header__user col-md-4 col-lg-3">--%>
        <%--            <a href="/login/login.jsp" class="header__user-link"><span>Login</span><i class="fas fa-sign-in-alt"></i></a>--%>
        <%--            <a href="/login/register.jsp" class="header__user-link"><span>Register</span><i class="fas fa-user-plus"></i></a>--%>
        <%--        </div>--%>
        <div class="header__user col-md-4 col-lg-3">
            <%
                if(session.getAttribute("username") == null){
            %>
            <a href="/log-in?action=login" class="header__user-link"><span>Login</span><i class="fas fa-sign-in-alt"></i></a>
            <a href="/log-in?action=register" class="header__user-link"><span>Register</span><i class="fas fa-user-plus"></i></a>
            <%} else {%>
            <a href="/author?action=authorPage&currentUser=${sessionScope.username}" style="display: inline; font-size: 1.1rem; margin-right: 10px;" class="header__user-link"><span>${sessionScope.fullName}</span><i class="fas fa-users-cog"></i></a>
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

<!-- Begin Top Author Page
================================================== -->
<div class="container">
    <div class="row">
        <div class="author__avt col-lg-2">
            <div class="author-img"></div>
            <!-- <img class="author-img" src="./assets/img/demopic/1.jpg" alt="avt"> -->
        </div>
        <div class="author__info col-lg-10">
            <div class="author__name">
                ${currentUser.fullname}
            </div>
            <div class="author__description">
                ${currentUser.description}
            </div>
            <div class="author__numberBlog">
                200 blogs
            </div>
            <%if(session.getAttribute("username")!=null && session.getAttribute("status") == (Integer)1 &&
                    session.getAttribute("username").equals(((Account) request.getAttribute("currentUser")).getUsername())) {%>
            <div class="author__nav">
                <a onclick="showAddBlogDialog('','','','');" class="author__nav-link addblog"><i class="fas fa-plus-circle"></i><span>Add new blog</span></a>
                <a href="#" class="author__nav-link changeinfo"><i class="fas fa-pen"></i><span>Change infomation</span></a>
            </div>
            <%}%>
            <%if(session.getAttribute("status") == (Integer)0) {%>
            <div class="author__nav">
                <a href="#" class="author__nav-link changeinfo"><i class="fas fa-user-slash"></i><span>You are blocked</span></a>
            </div>
            <%}%>
        </div>
    </div>
</div>
<!-- End Top Author Meta
================================================== -->

<!-- Begin Author Posts
================================================== -->
<div class="graybg authorpage">
    <div class="container">
        <div class="listrecent listrelated">
            <c:forEach items = '${requestScope["listBlogCategory"]}' var = "aBlogCategory">
                <!-- begin post -->
                <div class="authorpostbox">
                    <div class="card">
                        <a href="/post?blogID=${aBlogCategory.getKey().getBlogID()}&accountID=${currentUser.getAccountID()}">
                            <img class="img-fluid img-thumb" src="${aBlogCategory.getKey().getImage()}" alt="A blog image.">
                        </a>
                        <div class="card-block">
                            <h2 class="card-title"><a href="/post?blogID=${aBlogCategory.getKey().getBlogID()}&accountID=${currentUser.getAccountID()}">${aBlogCategory.getKey().getTitle()}</a></h2>
                            <h4 class="card-text">${aBlogCategory.getKey().getContent().substring(0, aBlogCategory.getKey().getContent().length() > 100 ? 100 : aBlogCategory.getKey().getContent().length() )}...</h4>
                            <div class="metafooter">
                                <div class="wrapfooter">
                                    <span class="meta-footer-thumb">
                                        <a href="/author?action=authorPage&currentUser=${currentUser.username}"><img class="author-thumb" src="https://www.gravatar.com/avatar/e56154546cf4be74e393c62d1ae9f9d4?s=250&amp;d=mm&amp;r=x" alt="Sal"></a>
                                    </span>
                                        <span class="author-meta">
                                        <span class="post-name"><a href="/author?action=authorPage&currentUser=${currentUser.username}">${currentUser.fullname}</a></span><br/>
                                        <span class="post-date">${aBlogCategory.getKey().getCreateAt()}</span><span class="dot"></span><span class="post-read">6 min read</span>
                                    </span>
                                    <p style="display: none" id="${aBlogCategory.getValue()}">${aBlogCategory.getValue()}</p>
                                    <span class="post-read-more">
                               <%if(session.getAttribute("username")!=null && session.getAttribute("status") == (Integer)1 &&
                                       session.getAttribute("username").equals(((Account) request.getAttribute("currentUser")).getUsername())) {%>
                                   <a style="display: inline-block; font-size: 1.2rem; margin: 5px;" class="readmore_icon" onclick="showAddBlogDialog('${aBlogCategory.getKey().getBlogID()}', '${aBlogCategory.getKey().getImage()}', '${aBlogCategory.getKey().getTitle()}', '${aBlogCategory.getKey().getContent()}', '${aBlogCategory.getValue()}')"><i class="fas fa-edit"></i></a>
                                   <a style="display: inline-block; font-size: 1.2rem; margin: 5px;" class="readmore_icon" onclick="showDeleteBlogDialog(${aBlogCategory.getKey().getBlogID()})"><i class="fas fa-trash-alt"></i></a>
                               <%}%>
                           </span>
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
<!-- End Author Posts
================================================== -->

<!-- Begin Footer
================================================== -->
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
<!-- End Footer
================================================== -->

<%--================== Begin dialog ================ --%>
<div class="list_dialog">
    <dialog style="border: 1px solid gray; border-radius: 10px" id="add_blog-dialog">
        <form action="/author?action=postBlog" method="post">
            <Strong>Blog infomations</Strong>
            <hr>
            <img class="img_dialog" id="add_blog_image" width="400px" src="https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png"
                 alt="Blog image">
            <input id="blogID" type="text" style="display: none" name="blogID">
            <table class="edit_table" style="border: 5px solid white">
                <tr>
                    <td><label for="image_blog_input">Blog image</label></td>
                    <td><input name="imageSource" id="image_blog_input" type="text" onchange="changeImageSource(this.value);"></td>
                </tr>
                <tr>
                    <td><label for="title_blog_input">Blog title</label></td>
                    <td><input name="title" id="title_blog_input" type="text"></td>
                </tr>
                <tr>
                    <td style="vertical-align: center"><label for="content_blog_input">Content</label></td>
                    <td><textarea name="content" id="content_blog_input" cols="30" rows="10"></textarea></td>
                </tr>
                <tr>
                    <td><p>Category</p></td>
                    <td>
                        <div class="cate">
                            <c:forEach items = '${requestScope["listAllCategory"]}' var = "category">
                                <input class="catechoice" type="checkbox" name="${category.getCategoryID()}" id="${category.getCategoryID()}">
                                <label for="${category.getCategoryID()}" class="labelCate">${category.getName()}</label>

                            </c:forEach>
                        </div>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;">
                <input id="editBlogButton" type="submit" value="Edit">
                <input id="postNewBlogButton" type="submit" value="Post">
                <input type="button" value="Cancel" onclick="closeAddBlogDialog()">
            </div>
        </form>
    </dialog>
    <dialog style="border: 1px solid gray; border-radius: 10px" id="deleteBlogDialog">
        <form id="deleteBlogForm" method="post" action="author?">
            <p>Do you want to delete this blog?</p>
            <input type="submit" value="Yes">
            <input type="button" value="No" onclick="closeDeleteBlogDialog()">
        </form>
    </dialog>
</div>
<%--================== End dialog ================ --%>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="assets/js/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
<%--<script src="assets/js/dialog.js">--%>
<script>
    function showAddBlogDialog(blogID, imageSource, title, content, category) {
        let blogDialog = document.getElementById('add_blog-dialog')
        let postButton = document.getElementById('postNewBlogButton')
        let editButton = document.getElementById('editBlogButton')
        let inputID = document.getElementById('blogID')
        inputID.value =  blogID.trim()
        // console.log(inputID.value + "id")
        if (inputID.value === "") {
            editButton.style.display = 'none';
            postButton.style.display = 'inline';
        } else {
            document.getElementById('image_blog_input').value = imageSource
            document.getElementById('title_blog_input').value = title
            document.getElementById('content_blog_input').value = content

            let categoryList = category.split(',');
            if(categoryList.length > 0) {
                for(let i = 0; i < categoryList.length; i++) {
                    console.log(categoryList[i])
                    if(document.getElementById(categoryList[i]) != null) document.getElementById(categoryList[i]).checked = true;
                }
            }


            // document.getElementById('content_blog_input').value = content
            postButton.style.display = 'none';
            editButton.style.display = 'inline'
        }
        blogDialog.showModal()
    }
    function changeImageSource(source) {
        let blogImage = document.getElementById('add_blog_image')
        if (source=="") {
            blogImage.src = "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png"
        }
        else {
            blogImage.src = source
        }
    }

    // deleteBlogDialog
    function showDeleteBlogDialog(blogID) {
        document.getElementById('deleteBlogForm').action = "author?action=deleteBlog&id=" + blogID
        document.getElementById("deleteBlogDialog").showModal()
    }
    function closeDeleteBlogDialog() {
        document.getElementById("deleteBlogDialog").close()
    }
    function closeAddBlogDialog() {

        document.getElementById('image_blog_input').value = ""
        document.getElementById('title_blog_input').value = ""
        document.getElementById('content_blog_input').value = ""
        document.getElementById('blogID').value = ""

        let inputTag = document.getElementsByClassName('catechoice')
        for(let i = 0; i< inputTag.length; i++) {
            inputTag[i].checked = false;
        }
        document.getElementById('add_blog-dialog').close()
    }

</script>
</body>
</html>
