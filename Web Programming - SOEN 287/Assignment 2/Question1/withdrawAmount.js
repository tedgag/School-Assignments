// ------------------------------------------------------------------------------
// Assignment 4 Question 1
// Written by: Édouard Gagné 40061204
// For SOEN 287 Section U – Winter 2018
// This script has a function withdrawAmount that withdraw the amount entered by
// the user from a bank account that is originaly at 500$ if the amount entered is
// divisible by 20 and if there is enough funds, it will remove the amount from the
// account with a fee of 0.50$.
// -----------------------------------------------------------------------------
var balance=500;
function withdrawAmount(){
  var x = document.getElementById("amount").value;
  if (x%20!=0){
    alert("Incorrect withdrawal amount");
    return false;
  } else if (x>500){
    alert("Insufficent funds");
    return false;
  } else {
    balance = balance - x - 0.50;
    alert("Successful transaction! \nCurrent Balance is:  " + balance);
    return false;
  }
}
