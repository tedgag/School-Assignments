<!DOCTYPE html>
<html>
  <head>
    <title>Assigment 3 Question 2</title>
    <script>
      <?php
      //Get and print the info from the query string in the url in a table. Prints a message if there is no query string in the url.
      function printInfos(){
        if (isset($_GET["age"]) && isset($_GET["name"])&& isset($_GET["number"])){
          $name = $_GET["name"];
          $age=$_GET["age"];
          $number=$_GET["number"];
          echo "<table>
                  <tr>
                    <th>Name</th>
                    <td>$name</td>
                  </tr>
                  <tr>
                    <th>Age</th>
                    <td>$age</td>
                  </tr>
                  <tr>
                    <th>Phone Number</th>
                    <td>$number</td>
                  </tr>
                </table>";
        } else {
          echo "No query string data found";
        }
      }

      ?>
    </script>
    <style>
    table, th, td{
      border: 1px solid black;
    }
    </style>
  </head>
  <body>

    <h1>Short Answers</h1>
    <h2>What is the query string and what purpose does it serves in server-side programming?</h2>
    <p>A query string is a group of keywords that are used to send request to the web server and pass
      data parameters from one page to another.</p>
    <h2>Is a query string characteristic of a GET or POST request method?</h2>
    <p>A GET method.</p>
    <h2>Which part of the URL is considered the query string?</h2>
    <p>The part after the "?".</p>
    <h2>How many different data parameters are being passed?</h2>
    <p>3.</p>
    <h2>Data</h2>
    <?php printInfos();?>
  </body>
  </html>
