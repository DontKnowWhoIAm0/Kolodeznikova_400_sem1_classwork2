<#include "base.ftl">
<#macro title>Main Page</#macro>

<#macro content>

    <h3> Hello, ${user}! Login successful
        <br>
        Session ID = ${sessionId}
        <br>
        Cookie user = ${cookieUser}
        <br>
        Login amount = ${counter}
    </h3>

</#macro>