<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Spring Boot Form Submission</title>
        <link href="/css/main.css" rel="stylesheet">
    </head>
    <body>
        <img src=https://upload.wikimedia.org/wikipedia/commons/1/1e/Calculator_icon.svg alt="CA" style="width:50%;">
        <h1>Assignment 2</h1>
        <h1>Calculator Web App</h1>
        <h2>Group 30</h2>
        <p>
          <#if calc?? >
              Your Submitted Expression<br>
              Expression: ${calc.input}<br>
              Solution: ${calc.answer}<br>
          <#else>
        </p>
            <form action="/form" method="post">
                <div class="one">Please Enter a Mathematical Expression</div>
                <input type="text" name="input"/>
                <br/><br/>
                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/>
            </form>

            <div class="four">Members:</div>

            <div class="three">
            	  <div class="one">Karthik Venkatesh Nagaraj.. (kvnkarthik02)</div>
                <div class="one">William Walsh-Dowd.. (sanechuckles2)</div>
                <div class="one">Gerald Moore Paris.. (gmbparis)</div>
                <div class="one">Jhellen Kyle Daniel Agsalud.. (xkylus)</div>
            </div>

            <div class="two"></div>

            <div class="five">
            	  <div class="one">Link to Github Repository</div>
                <a href="https://github.com/kvnkarthik02/SWENG-CI-CD">SWENG Assignment 2 Repository</a>
            </div>

        </#if>
        <script src="/js/main.js"></script>
    </body>
</html>
