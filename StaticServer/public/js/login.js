function login(login, password) {
    $.ajax({
            url: "http://localhost:8080/login",
            type: "POST",
            headers: {
                'login': login,
                'password': password
            },
            async:false,
            success: function (data, textStatus, request) {
                let token = request.getResponseHeader('Auth-Token');
                document.cookie = "Auth-Token=" + token;
                window.location = '/product.html';
            },
            error: function (data, textStatus, request) {
                window.location = '/login.html';
            }
        }
    )
}
