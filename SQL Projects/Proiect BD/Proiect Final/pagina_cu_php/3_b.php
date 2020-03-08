<html>
<head>
<title>3_b</title>
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

<p align=left><font type=cortana size=5 color="#00ff00">9.03  Să se exprime în SQL urmatoarele interogari:</font></p> </br>
    <font size=4 color="#00ff00">b) Să se găsească detaliile din Set_ingrediente unde nu există comentarii ordonat 
după reteta_id și ingred_id.</font>
 
<form action="3_b.php" method=post>   
      
<input type=submit value="Afiseaza" name="b"> 
    
</form>

<?php
include 'db_connection.php';

if(isset($_POST['b']))
{	
$conn = OpenCon();
$sql = "SELECT *\n"

    . "FROM Set_ingrediente\n"

    . "WHERE comentarii = ''\n"

    . "ORDER BY reteta_id, ingred_id";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

echo"<table border='2' bgcolor=white>";
echo "<tr><td>reteta_id</td><td>ingred_id</td><td>cantitate</td><td>um</td><td>comentarii</td><br>";
foreach($result as $item){
	echo "<tr><td>{$item['reteta_id']}</td><td>{$item['ingred_id']}</td><td>{$item['cantitate']}</td>
	<td>{$item['um']}</td><td>{$item['comentarii']}</td></tr><br>";
}
CloseCon($conn);
}
?>
</body>
</html>