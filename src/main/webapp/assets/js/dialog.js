function showAddBlogDialog(blogID){
    if(blogID == "") {
        document.getElementById('add_blog-dialog').showModal()
    }
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