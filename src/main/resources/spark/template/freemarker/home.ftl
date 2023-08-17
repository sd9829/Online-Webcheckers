<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
  <style>
  textarea {
    resize: none;
    padding: 5px;
  }
  .btn-group button {
    background-color: #04AA6D; /* Green background */
    border: 1px solid green; /* Green border */
    color: white; /* White text */
    padding: 5px 5px; /* Some padding */
    cursor: pointer; /* Pointer/hand icon */
    width: 25%; /* Set a width if needed */
    display: block; /* Make the buttons appear below each other */
    text-align: left;
  }

  .btn-group button:not(:last-child) {
    border-bottom: none; /* Prevent double borders */
  }

  /* Add a background color on hover */
  .btn-group button:hover {
    background-color: #3e8e41;
  }
  </style>
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">
  <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

    <#if currentUser??>

      <!-- List active users -->
      <form action="./" method="POST">
      <script>
        function getButtonValue(e) {
          document.getElementById("selectedPlayer").value = e.value;
        }
      </script>
        <b>Active Players</b>
        <input id="selectedPlayer" name="selectedPlayer" type="hidden">
        <div class="btn-group">
          <#list currentPlayers as player>
            <button onclick="getButtonValue(this)" id="${player.name}" value="${player.name}">${player.name}</button>
          </#list>
        </div>
      </form>
    <#else>
      <!-- Display current player count demo -->
      <b>Active Players: </b> ${currentPlayers?size}
    </#if>
    

    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

  </div>

</div>
</body>

</html>
