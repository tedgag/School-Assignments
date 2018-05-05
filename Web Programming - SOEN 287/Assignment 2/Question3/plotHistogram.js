// ------------------------------------------------------------------------------
// Assignment 4 Question 3
// Written by: Édouard Gagné 40061204
// For SOEN 287 Section U – Winter 2018
// This script has a function plotHistorgram that takes a string and a word from
// the html document and concatenate them in one string. It will then print a graph
// illustrating the number of times all letters are present in this string.
// -----------------------------------------------------------------------------
function plotHistogram(){
  // Getting the string and word from the document
  var string= document.getElementById('string').value;
  var word =document.getElementById('word').value;
  // Concatenating the string and word into a ew string
  var str=string.concat(word);
  // Creating an array that contains all the letters in the string
  var x = str.split("");
  // Creating the histogram using the string of letters
  var histElements = {
  x: x,
  type: 'histogram',
  marker: {color: '#cc0000',},
  };
  var layout = {
  bargap: 0.05,
  bargroupgap: 0.5,
  barmode: "overlay",
  title: "Histogram",
  xaxis: {title: "Values",},
  yaxis: {title: "Count"},
  };
  var data = [histElements];
  // Printing the histogram in the div of the html document
  Plotly.newPlot('printhistogram', data, layout);
  return false;
}
