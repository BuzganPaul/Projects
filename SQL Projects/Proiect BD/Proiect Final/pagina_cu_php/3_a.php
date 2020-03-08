<html>
<head>
<title>3_a</title>
</head>
<body background="backgrd.gif">
<p ><font size=6 color="#00ff00">Subiectul 9</p> </font>
<table bgcolor="#FFFFFF" border =1>
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

<p align=left><font type=cortana size=5 color="#00ff00">9.03  Să se exprime în SQL urmatoarele interogari:</font></p> </br>
    <font size=4 color="#00ff00">a) Să se găsească numele, descrierea și timpul de preparare a rețetelor
vegetariene în ordine descrescătoare a timpului de preparare.</font>
 
<form action="3_a.php" method=post>   
      
<input type=submit value="Afiseaza" name="a"> 
    
</form></br></br></br>

<?php
include 'db_connection.php';
$conn = OpenCon();

if(isset($_POST['a']))
{	
$sql = "SELECT nume, descriere, timp_preparare\n"

    . "FROM Reteta\n"

    . "WHERE vegetariana='D'\n"

    . "ORDER BY timp_preparare DESC";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 
echo"<table  border='1' bgcolor=white>";
	echo"<tr><td>nume</td><td>descriere</td><td>timp_preparare</td></tr>";
foreach($result as $item){
	echo "<tr><td>{$item['nume']}</td><td>{$item['descriere']}</td><td>{$item['timp_preparare']}</td></tr><br>";
	
}

CloseCon($conn);


}



?>
</body>
</html>