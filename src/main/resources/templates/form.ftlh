<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Spring Boot Form Submission</title>
        <link href="/css/main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Assignment 2 : Calculator Web App </h1>
        <h2>Group 30</h2>
        <#if calc?? >
            Your submitted data<br>
            Name: ${calc.input}<br>
            Message: ${calc.answer}<br>
        <#else>
            <form action="/form" method="post">
                <div>Input:</div>
                <input type="text" name="input"/>
                <br/><br/>
                <div>Message:</div>
                <textarea rows="2" name="message"></textarea>
                <br/><br/>
                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/>
            </form>
        </#if>
        <script src="/js/main.js"></script>
    </body>
</html>
