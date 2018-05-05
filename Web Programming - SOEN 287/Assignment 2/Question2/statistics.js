// ------------------------------------------------------------------------------
// Assignment 4 Question 2
// Written by: Édouard Gagné 40061204
// For SOEN 287 Section U – Winter 2018
// This script has a function printNumbers that prompt the user to enter numbers
// until he enters -1. The script will then display all the entered numbers and
// display the number of odd and even numbers entered
// -----------------------------------------------------------------------------
function printNumbers(){
  var x,odd=0,even=0;
  // Initializing the text that will be printed
  var text="You have entered the bellow metionned numbers" +"<br>";
  // Initializing an empty aray that will store the numbers
  var array=[];
  // Do-while loop that will prompt the user to enter a number until -1 is entered.
  // It will add the number t an array if it is really a number, the field is not
  // empty and the number isn't -1.
  do {
    x = prompt("Enter a number or enter -1 to quit");
    if (x!=-1 && x!="" && !isNaN(x)) {
      array.push(x);
    }
  } while (x!=-1)
  //This for loop will check for all the numbers in the array if they are odd or even,
  // and increment the odd or even variable in response. It add the numebers to the text.
  for (i=0; i<array.length;i++){
    text+= array[i] + "<br>";
    if (array[i]%2 == 0){
      even+=1;
    } else {
      odd+=1;
    }
  }
  //Adding the number of odd and even numbers to the text
  text+= "You have entered " + even + " even numbers and " + odd + " odd numbers"
  // Printing the text in the div of the html document.
  document.getElementById("statsOut").innerHTML = text;
}
