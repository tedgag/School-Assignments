<?php
  $count=1;
  if (isset($_COOKIE["count"])){
    $count=$_COOKIE["count"];
    $count++;
  }
  if (isset($_COOKIE["lastVisit"])){
    $lastVisit=$_COOKIE["lastVisit"];
  }
  setcookie("count",$count, time()+3600);
  date_default_timezone_set("America/New_York");
  setcookie("lastVisit",date("d-m-Y H:i:s"), time()+3600);
  if ($count==1){
    echo "Welcome! You are a new customer here";
  } else {
    echo "Hello, this is your $count time here<br />";
    echo "Your last visit was on $lastVisit";
  }
?>
<html>
  <head>
    <title>Assigment 3 Question 2</title>
  </head>
  <body>
  </body>
  </html>
