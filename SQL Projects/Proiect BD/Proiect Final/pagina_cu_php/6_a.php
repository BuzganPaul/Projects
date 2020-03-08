<html>
<head>
<title>6_a</title>
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

<p align=left><font type=cortana size=5 color="#00ff00">9.06  Să se exprime în SQL următoarele interogări folosind funcţii de agregare:</font></p> </br>
    <font size=4 color="#00ff00">a) Să se găsească timpul de preparare mediu pentru fiecare tip de rețetă.</font>
 
<form action="6_a.php" method=post>   
      
<input type=submit value="Afiseaza" name="a"> 
    
</form></br></br></br>

<?php
include 'db_connection.php';

$conn = OpenCon();
if(isset($_POST['a']))
{
$sql = "SELECT c.tip, AVG(r.timp_preparare) as timp_mediu\n"

    . "FROM Reteta r, Categorie c\n"

    . "WHERE r.categ_id=c.categ_id\n"

    . "GROUP BY c.tip";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

	echo"<table border='2' bgcolor=white>";
	echo "<tr><td>tip</td><td>timp_mediu</td></tr>";
	foreach($result as $item){
		echo "<tr><td>{$item['tip']}</td><td>{$item['timp_mediu']}</td></tr>";
	}
CloseCon($conn);
}
?>
</body>
</html>