<html>
<head>
<title>6_b</title>
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
    <font size=4 color="#00ff00">b) Să se găsească cantitatea maximă folosită pentru ingredientul ’usturoi’ în rețete din categoria ciorbă și numele rețetei.</font>
 
<form action="6_b.php" method=post>   
      
<input type=submit value="Afiseaza" name="b"> 
    
</form></br></br></br>


<?php
include 'db_connection.php';

$conn = OpenCon();
if(isset($_POST['b']))
{
$sql = "SELECT Reteta.Nume as numele, Set_ingrediente.Cantitate as cantitatea\n"

    . "FROM Reteta \n"

    . "JOIN Set_ingrediente ON Reteta.reteta_id = Set_ingrediente.reteta_id\n"

    . "WHERE Set_ingrediente.cantitate=(SELECT MAX(cantitate) FROM Set_ingrediente WHERE ingred_id=(SELECT ingred_id FROM Ingrediente WHERE Ingredient='usturoi')) AND Reteta.reteta_id=ANY(SELECT reteta_id FROM Reteta WHERE categ_id=(SELECT categ_id FROM Categorie WHERE tip='CIORBA'))";

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

	echo"<table border='2' bgcolor=white>";
	echo "<tr><td>numele</td><td>cantitatea</td></tr>";
	foreach($result as $item){
		echo "<tr><td>{$item['numele']}</td><td>{$item['cantitatea']}</td></tr>";
	}
CloseCon($conn);
}
?>
</body>
</html>