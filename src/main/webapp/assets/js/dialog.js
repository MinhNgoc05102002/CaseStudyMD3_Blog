function showAddBlogDialog(blogID, imageSource, title, content) {
    let blogDialog = document.getElementById('add_blog-dialog')
    let postButton = document.getElementById('postNewBlogButton')
    let editButton = document.getElementById('editBlogButton')
    let inputID = document.getElementById('blogID')
    inputID.value =  blogID.trim()
    console.log(inputID.value + "id")
    if (inputID.value === "") {
        editButton.style.display = 'none';
    } else {
        document.getElementById('image_blog_input').value = imageSource
        document.getElementById('title_blog_input').value = title
        document.getElementById('content_blog_input').value = content

        let categoryList = category.split(',');

        for(let i = 0; i < categoryList.length; i++) {
            console.log(categoryList[i])
            // document.getElementById(categoryList[i]).checked = true;
        }

        // document.getElementById('content_blog_input').value = content
        postButton.style.display = 'none';
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

//deleteBlogDialog
function showDeleteBlogDialog(blogID) {
    document.getElementById('deleteBlogForm').action = "author?action=deleteBlog&id=" + blogID
    document.getElementById("deleteBlogDialog").showModal()
}
function closeDeleteBlogDialog() {
    document.getElementById("deleteBlogDialog").close()
}