<html>
<head>
<title>5_a</title>
</head>
<body background="backgrd.gif">
<p ><font size=6 color="#00ff00">Subiectul 9</p> </font>
<table border =1 bgcolor=white>
<tr>
  <th><a href="index.php">Index Page</a></th>
  <th><a href="3_a.php">Exercitiul 3 A</a></th>
  <th><a href="3_b.php">Exercitiul 3 B</a></th>
  <th><a href="4_a.php">Exercitiul 4 A</a></th>
  <th><a href="4_b.php">Exercitiul 4 B</a></th>
  <th><a href="5_a.php">Exercitiul 5 A</a></th>
  <th><a href="5_b.php">Exercitiul 5 B</a></th>
  <th><a href="6_a.php">Exercitiul 6 A</a></th>
  <th><a href="6_b.php">Exercitiul 6 B</a></th>
</tr>
</table>	

<div class=container align=center>

<p align=left><font type=cortana size=5 color="#00ff00">9.05  Să se exprime în SQL fara functii de agregare urmatoarele interogari folosind cel putin o interogare imbricata si operatori de genul EXISTS,IN,ALL,ANY:</font></p> </br>
    <font size=4 color="#00ff00">a) Să se găsească numele, descrierea și timpul de preparare pentru rețetele
vegetariene, care au timp de preparare cel mai mic.</font>
 
<form action="5_a.php" method=post>   
      
<input type=submit value="Afiseaza" name="b"> 
    
</form></br></br></br>

<?php
include 'db_connection.php';

$conn = OpenCon();
if(isset($_POST['b']))
{
$sql = "CALL min_timp()";

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

echo"<table border='2' bgcolor=white>";
echo"<tr><td>nume</td><td>descriere</td><td>timp_preparare</td></tr>";
foreach($result as $item){
	echo "<tr><td>{$item['nume']}</td><td>{$item['descriere']}</td><td>{$item['timp_preparare']}</td></tr>";
}
CloseCon($conn);
}
?>
</body>
</html>