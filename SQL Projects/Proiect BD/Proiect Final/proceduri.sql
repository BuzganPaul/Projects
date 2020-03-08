DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `min_timp`()
BEGIN
SELECT nume,descriere,timp_preparare
	FROM Reteta 
	WHERE vegetariana='D' AND timp_preparare <= ALL(SELECT timp_preparare FROM Reteta);
END$$
DELIMITER ;


