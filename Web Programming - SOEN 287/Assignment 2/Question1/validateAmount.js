// ------------------------------------------------------------------------------
// Assignment 4 Question 1
// Written by: Édouard Gagné 40061204
// For SOEN 287 Section U – Winter 2018
// This script has a function validateAmount that takes an amount from the document
// and check if the value is numeric, alerting the user if it isn't.
// -----------------------------------------------------------------------------
function validateAmount(){
  // Getting the amount from the html document
  var x = document.getElementById("amount").value;
  // Checking if the value entered is numeric
  if (isNaN(x)) {
    // Alerting the user that the value entered isn't numeric
    alert("Please enter a numeric value");
    return false;
  } else {
    // Returning the withdrawAmount function if the value entered is numeric.
    return withdrawAmount();
  }
}
