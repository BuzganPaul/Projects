library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity BCD_in_binar is
Port ( 

temp1: in std_logic_vector(1 downto 0);
temp2: in std_logic_vector(3 downto 0);
temp3: in std_logic_vector(3 downto 0);


prim_binar : out std_logic_vector(7 downto 0);
semn1 : out std_logic);


end BCD_in_binar ;
 
architecture Behavioral of BCD_in_binar is
 
begin 

BCD_in_binar: process(temp1,temp2,temp3)
begin 	
	
if temp1="01"	then
prim_binar <= (temp3 * "01")+(temp2 * "1010")+("1100100");
semn1 <= '0';
end if;

if temp1="00"	then
prim_binar <= (temp3 * "01")+(temp2 * "1010")+("0000000");
semn1 <= '0';
end if;

if temp1="10"	then
prim_binar <= (temp3 * "01")+(temp2 * "1010")+("0000000");
semn1 <= '1';
end if;

if temp1="11"	then
prim_binar <= (temp3 * "01")+(temp2 * "1010")+("1100100");
semn1 <= '1';
end if;

end process BCD_in_binar;
	
end Behavioral;