library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity eroare_final is
Port ( 

eroare1 : in std_logic;
eroare2 : in std_logic;
eroare3 : in std_logic;
eroare4 : in std_logic;
eroare5 : in std_logic;
eroare : out  std_logic);


end eroare_final ;
 
architecture Behavioral of eroare_final is
 
begin 

eroare_process : process(eroare1,eroare2,eroare3,eroare4,eroare5)
begin
eroare<=eroare1 or eroare2 or eroare3 or eroare4 or eroare5;
end process eroare_process;

	
end Behavioral;