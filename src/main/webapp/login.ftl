<#include "base.ftl">
<#macro title>Log In</#macro>

<#macro content>

    <#if error??>
        <#if error == "1">
            <p>Invalid username or password</p>
        </#if>
    </#if>

    <form method="post" action="login">
        Login: <input type="text" name="login" placeholder="type your login"><br>
        Password: <input type="password" name="password" placeholder="type your password"><br>
        <input type="submit" value="Log In">
    </form>

    <form method="get" action="signup">
        <input type="submit" value="To Sign Up Page">
    </form>

</#macro>