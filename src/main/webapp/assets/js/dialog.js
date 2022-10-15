function showAddBlogDialog(blogID){
    let blogDialog = document.getElementById('add_blog-dialog')
    let postButton = document.getElementById('postNewBlogButton')
    let editButton = document.getElementById('editBlogButton')
    let image = document.getElementById('image_blog_input')
    // image.value = 'http://localhost'
    // if(blogID.trim() == "") {
    //     editButton.style.display = 'inline-block';
    // }
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