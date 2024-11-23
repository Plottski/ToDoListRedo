function login() {
    $(document).getElementById("loginButton").click();

    $.ajax({
        method: "POST",
        url: "/login",
        dataType: "json",
        data: JSON.stringify({
            username: document.getElementById("userName").value,
            password: document.getElementById("password").value}),
        success: function (data) {
            if (data.status == 200) {
                console.log(data);
            }
        }
    })
}



