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

    <!-- Ensure user is signed in before signing out -->
    <#if currentUser??>
      <form action="./signout" method="POST">
        <label>Currently signed in as: ${currentUser.name}</label>
        <br><button type="submit">Sign Out</button><br>
      </form>
    <#else>
      <!-- TODO: Redirect to home on signout -->
    </#if>

  </div>

</div>
</body>

</html>
