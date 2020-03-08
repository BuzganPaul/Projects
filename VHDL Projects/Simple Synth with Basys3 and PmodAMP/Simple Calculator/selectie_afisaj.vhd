library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity afisaj_1 is
Port ( 
Clock1_Enable_B : in std_logic;
temp1: in std_logic_vector(1 downto 0);
temp2: in std_logic_vector(3 downto 0);
temp3: in std_logic_vector(3 downto 0);
temp4: in std_logic_vector(1 downto 0);
temp5: in std_logic_vector(3 downto 0);
temp6: in std_logic_vector(3 downto 0);

Output1 : out std_logic_vector(1 downto 0);
Output2 : out std_logic_vector(3 downto 0);
Output3 : out std_logic_vector(3 downto 0));

end afisaj_1 ;
 
architecture Behavioral of afisaj_1 is
 
begin 
	
selectie_afisaj: process(Clock1_Enable_B,temp1,temp2,temp3,temp4,temp5,temp6)
begin
 
if Clock1_Enable_B='0' then
Output1 <= temp1;
Output2 <= temp2;
Output3 <= temp3;
end if;

if Clock1_Enable_B='1' then
Output1 <= temp4;
Output2 <= temp5;
Output3 <= temp6;
end if;
end process selectie_afisaj; 

 
end Behavioral;