<html>
<head>
<title>4_b</title>
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
    <font size=4 color="#00ff00">b) Să se găsească numele și descrierea rețetelor ce nu conțin ingredientul ’ciuperci’.</font>
 
<form action="4_b.php" method=post>   
      
<input type=submit value="Afiseaza" name="b"> 
    
</form>

<?php
include 'db_connection.php'; 

if(isset($_POST['b']))
{
$conn = OpenCon();
$sql = "SELECT DISTINCT Reteta.nume, Reteta.descriere\n"

    . "FROM Reteta \n"

    . "JOIN Set_ingrediente ON Reteta.reteta_id = Set_ingrediente.reteta_id\n"

    . "WHERE Reteta.nume!=ALL(SELECT Reteta.nume FROM Reteta \n"

    . "JOIN Set_ingrediente ON Reteta.reteta_id = Set_ingrediente.reteta_id\n"

    . "WHERE Set_ingrediente.ingred_id=(SELECT ingred_id FROM Ingrediente WHERE ingredient='ciuperci'))";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

echo"<table border='2' bgcolor=white>";
echo "<tr><td>Nume</td><td>Descriere</td></tr>";
foreach($result as $item){
	echo "<tr><td>{$item['nume']}</td><td>{$item['descriere']}</td></tr><br>";
}
CloseCon($conn);
}
?>
</body>
</html>