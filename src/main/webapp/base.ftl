<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title><@title></@title></title>
    </head>

    <body>
        <div id="header">
            <#if user??>
                <a href="logout">Выйти</a>
            </#if>
        </div>

        <div id="content">
            <div class="content">
                <@content></@content>
            </div>
        </div>
    </body>

</html>