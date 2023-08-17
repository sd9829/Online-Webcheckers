<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

    <#--  Provide username input field and submit button  -->
    <#if !currentUser??>
      <form action="./signin" method="POST">
        <label for="username">User Name:</label>
          <br><input type="text" id="inputUser" name="inputUser"><br>
        <br><button type="submit">Sign In</button><br>
      </form>
    </#if>

    <#--  <form action="./signin" method="POST">
      <label for="username">User Name:</label>
        <br><input type="text" id="inputUser" name="inputUser"><br>
      <br><button type="submit">Sign In</button><br>
    </form>  -->

    

  </div>

</div>
</body>

</html>
