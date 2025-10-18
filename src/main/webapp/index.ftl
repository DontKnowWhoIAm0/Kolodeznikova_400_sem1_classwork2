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

        <#if profileImage??>
            <img src="${profileImage}" alt="Profile Image" style="max-width:500px; max-height:500px;">
        <#else>
            <p>No profile image</p>
        </#if>

    </#macro>