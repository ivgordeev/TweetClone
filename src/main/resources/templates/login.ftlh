<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Login page
    <br>
    <@l.login "/login" />
    <a href="/registration">Sign up</a>
</@c.page>
