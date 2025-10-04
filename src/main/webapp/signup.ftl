<html lang="en">
<meta charset="UTF-8">
<#include "base.ftl">
<#macro title>Sign Up</#macro>

<#macro content>

    <#if error??>
        <#if error == "1">
            <p>Fill all gaps</p>
        <#elseif error == "2">
            <p>Such login already exists</p>
        </#if>
    </#if>

    <form method="post" action="signup">
        Name: <input type="text" name="name" placeholder="enter your name"><br>
        Lastname: <input type="text" name="lastname" placeholder="enter your lastname"><br>
        Login: <input type="text" name="login" placeholder="create login"><br>
        Password: <input type="password" name="password" placeholder="create password"><br>
        <input type="submit" value="Sign Up">
    </form>

    <form method="get" action="login">
        <input type="submit" value="To Log In Page">
    </form>

</#macro>
</html>