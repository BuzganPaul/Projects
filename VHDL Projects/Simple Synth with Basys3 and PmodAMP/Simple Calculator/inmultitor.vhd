library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity inmultitor is
Port ( 
semn_inmultit : in std_logic;
prim_binar: in std_logic_vector(7 downto 0);
secund_binar: in std_logic_vector(7 downto 0);

Rezultat3 : out std_logic_vector(7 downto 0));


end inmultitor ;
 
architecture Behavioral of inmultitor is
 
begin  
	
	
inmultire: Process (prim_binar,secund_binar,semn_inmultit)
variable cnt1 : STD_LOGIC_Vector(7 downto 0);
variable temporar1 : std_logic_vector(7 downto 0);
begin  
if (semn_inmultit='1') then	
cnt1:="00000001";
temporar1:= "00000000";
while (cnt1 <= secund_binar) loop
                temporar1:= (temporar1 + prim_binar);
                cnt1 := cnt1 + "00000001";
end loop;
Rezultat3 <= temporar1;
end if;
end process inmultire;

	
	
	
	
	
	
end Behavioral;