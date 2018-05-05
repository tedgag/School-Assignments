<!DOCTYPE html>
<html>
  <head>
    <title>Assigment 3 Exercise 1</title>
    <script>
      <?php
      //Prints n!, where n is an integer.
      function computefactorial($n)  {
      $f=1;
      echo "Input number is: $n.<br/>";
      for($i=1;$i<=$n;$i++){
        $f=$f*$i;
      }
      echo "The factorial of $n is $f.";
      }
      function findMostFrequent($strings){
        echo "The input array is: ||";
        foreach ($strings as $string){
          echo" $string ||";
        }
        echo"<br />";
        $a=array_count_values($strings);
        $firstvalue=reset($a);
        foreach($a as $word => $count){
          if ($count==$firstvalue){
            echo "The string $word is the most frequent and appears $count times. ";
          }
        }
      }
      //Takes a word as input and upercase the first letter of the word and lowercase the rest.
      function toUppercaseFirst($word){
        echo "Input name: \"$word\".<br />";
        $word = strtolower($word);
        $word= ucfirst($word);
        echo $word ;
      }
      //Split an string into words and then upercase the first letter of those words and lowercase the rest. Then, the words
      // are sorted out by alphabetical order and printed.
      function splitCapitalizeSort($string){
        echo "Input string: \"$string\".<br />";
        $array=explode(" ",$string);
        foreach($array as &$word){
          $word = strtolower($word);
          $word= ucfirst($word);
        }
        sort($array);
        foreach($array as &$word) {
          echo "$word ";
        }
      }
      //Return an print the date of the next friday from now.
      function dayofNextFriday(){
        $nextfriday=strtotime("next Friday");
        echo date("d/m/Y",$nextfriday);
        return date("d/m/Y",$nextfriday);
      }
      //Remove any duplicate item from an array and then sort the array.
      function findUniqueandSort($array){
        echo "The input array is: ||";
        foreach ($array as $number){
          echo" $number ||";
        }
        echo "<br />";
        $array=array_unique($array);
        sort($array);
        foreach($array as &$number) {
          echo "$number ";
        }
      }
      //Takes an associative array then print a table with the keys in the left column and the values in the right column.
      function sortHash1($array){
        echo "The input array is: ||";
        foreach($array as $key => $value){
          echo " $key => $value ||";
        }
        echo"<br/>";
        asort($array);
        echo"<table><tr><th>Name</th><th>Salary</th></tr>";
        foreach($array as $name => $salary){
          echo "<tr><td>$name</td><td>$salary</td></tr>";
        }
        echo"</table>";
      }
      //Accept an associative array and sort it based on an input code. 1: ascending by value. 2: ascending by key.
      //3: descending by value. 4: descending by key. For any other code, it prints "Wrong code".
      function sortHash2($array,$code){
        echo "The code is $code and the input array is: ||";
        foreach($array as $key => $value){
          echo " $key => $value ||";
        }
        echo"<br/>";
        switch($code){
          case 1: asort($array);
            echo "||";
            foreach($array as $key => $value){
              echo " $key => $value ||";
            }
            break;
          case 2: ksort($array);
            echo "||";
              foreach($array as $key => $value){
                echo " $key => $value ||";
              }
              break;
          case 3: arsort($array);
            echo "||";
              foreach($array as $key => $value){
                echo " $key => $value ||";
              }
              break;
          case 4: krsort($array);
            echo "||";
              foreach($array as $key => $value){
                echo " $key => $value ||";
              }
              break;
          default: echo "Wrong Code";
          break;
        }
        echo "<br /><br />";
      }
      //Takes an array of temperature and compute the average temperature and prints the four lowest and highest temperatures.
      function averageTemp($array){
        echo "The input temperatures are: ||";
        foreach($array as $value){
          echo " $value ||";
        }
        echo"<br />";
        $average=0;
        foreach($array as $value){
          $average+=$value;
        }
        $average=(int)($average/count($array));
        echo "Average temperature is: $average.<br />";
        sort($array);
        $lowest=array_slice($array, 0,4);
        echo"List of four lowest temperatures: ";
        foreach($lowest as $value){
          echo " $value ";
        }
        echo "<br />";
        $highest=array_slice($array,(count($array)-4),count($array)) ;
        echo "List of four highest temperatures:";
        foreach($highest as $value){
          echo " $value ";
        }
      }
      //Check if a word is found at the start or end of a sentence. Returns true if it is found, otherwise returns false.
      function findatStartorEnd($string,$word){
        echo "The string is \"$string\" and the word is \"$word\".<br />";
        $array=explode(" ",$string);
        $firstword= array_shift($array);
        $lastword= array_pop($array);
        if (strcmp($firstword,$word)==0 || strcmp($lastword,$word)==0){
          return true;
        }
        else
          return false;
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
    <h1>Exercise 1</h1>
    <h2>computeFactorial function</h2>
    <?php computefactorial(4)?>
    <h2>computeFactorial function</h2>
    <?php findMostFrequent(array("Cat","Dog","Cat","Turtle", "Bird", "Fish")); ?>
    <h2>toUppercaseFirst function</h2>
    <?php toUppercaseFirst("jaMEs")?>
    <h2>splitCapitalizeSort function</h2>
    <?php splitCapitalizeSort("To be or not to be")?>
    <h2>dayofNextFriday function</h2>
    <?php dayofNextFriday()?>
    <h2>findUniqueandSort function</h2>
    <?php findUniqueandSort(array(3,2,1,2,3))?>
    <h2>sortHash1 function</h2>
    <?php sortHash1(array("John"=>"35000","Pete"=>"45000","Janet"=>"40000"))?>
    <h2>sortHash2 function</h2>
    <?php sortHash2(array("John"=>"35","Pete"=>"45","Janet"=>"40","Johnny"=>"40","Albert"=>"60"),1)?>
    <?php sortHash2(array("John"=>"35","Pete"=>"45","Janet"=>"40","Johnny"=>"40","Albert"=>"60"),2)?>
    <?php sortHash2(array("John"=>"35","Pete"=>"45","Janet"=>"40","Johnny"=>"40","Albert"=>"60"),3)?>
    <?php sortHash2(array("John"=>"35","Pete"=>"45","Janet"=>"40","Johnny"=>"40","Albert"=>"60"),4)?>
    <?php sortHash2(array("John"=>"35","Pete"=>"45","Janet"=>"40","Johnny"=>"40","Albert"=>"60"),5)?>
    <h2>averageTemp function</h2>
    <?php averageTemp(array(78, 60, 62, 68, 71, -17, 52, 68, 73, 85, 66, 64, 76, 63, 75, 76, 73, 68, 62, 73, -10, 72, 65, 80,74, 62, 62, 65, 64));?>
    <h2>findatStartorEnd function</h2>
    <?php echo findatStartorEnd("Did you ever hear of the tragedy of Darth Plagueis the Wise?", "Did")?>
  </body>
</html>
