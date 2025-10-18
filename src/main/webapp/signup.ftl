<html lang="en">
<meta charset="UTF-8">
<#include "base.ftl">
<#macro title>Sign Up</#macro>

<#macro content>

    <p id="login-error">
        <#if error?? && error == "1">
            Fill all gaps
        </#if>
    </p>

    <form method="post" action="signup" enctype="multipart/form-data">
        Name: <input type="text" name="name" placeholder="enter your name"><br>
        Lastname: <input type="text" name="lastname" placeholder="enter your lastname"><br>
        Login: <input type="text" id="login" name="login" placeholder="create login"><br>
        Password: <input type="password" name="password" placeholder="create password"><br>
        Profile image: <input type="file" name="profile_image"><br>
        <input type="submit" id="signup-btn" value="Sign Up">
    </form>

    <form method="get" action="login">
        <input type="submit" value="To Log In Page">
    </form>

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>

        const contextPath = window.location.pathname.split('/')[1];
        const url = '/' + contextPath + '/ajax/checkLogin';

        $(document).on("input", "#login", function() {
            const login = $(this).val().trim();

            if (login === "") {
                $("#login-error").text("");
                return;
            }

            $.get(url, { login: login }, function(response) {
                if (response === "exists") {
                    $("#login-error").text("Such login already exists");
                    $("#signup-btn").prop("disabled", true);
                } else if (response === "free") {
                    $("#login-error").text("");
                    $("#signup-btn").prop("disabled", false);
                }
            })

        })

    </script>

</#macro>
</html>