<html>
<head>
<title>4_a</title>
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
<p align=left><font type=cortana size=5 color="#00ff00">9.04  Să se exprime în SQL urmatoarele interogari folosind operatorul JOIN:</font></p> </br>
    <font size=4 color="#00ff00">a) Să se găsească sub forma (nume1, nume2) perechi de rețete astfel încât primul nume
este de tip supă sau ciorbă, iar al doilea nume este din altă categorie.</font>
	<form action="4_a.php" method=post> 
    
<table border=1 bgcolor=white>
       
    
<tr>
<td colspan="2" align="center"><input type=submit value="Afiseaza" name="b"></td>
</tr>
    
</table>  
    
<?php
include 'db_connection.php';

if(isset($_POST['b']))
{
$conn = OpenCon();
$sql = "SELECT A.nume AS nume1, B.nume AS nume2\n"

    . "FROM Reteta A, Reteta B\n"

    . "WHERE (A.categ_id=(SELECT categ_id FROM Categorie WHERE Tip='SUPA') OR A.categ_id=(SELECT categ_id FROM Categorie WHERE Tip='CIORBA'))\n"

    . "AND B.categ_id!=(SELECT categ_id FROM Categorie WHERE Tip='SUPA')\n"

    . "AND B.categ_id!=(SELECT categ_id FROM Categorie WHERE Tip='CIORBA')";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

echo"<table border='2' bgcolor=white>";
echo "<tr><td>nume1</td><td>nume2</td></tr>";
foreach($result as $item){
	echo "<tr><td>{$item['nume1']}</td><td>{$item['nume2']}</td></tr><br>";
}
CloseCon($conn);
}
?>
</body>
</html>