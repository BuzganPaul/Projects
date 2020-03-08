library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity semn is
Port ( 
semn_inmultit : in std_logic;
semn_impartit : in std_logic;
semn1 : in std_logic;
semn2 : in std_logic;

semn3_final : out std_logic);


end semn ;
 
architecture Behavioral of semn is
 
begin
	
semn: process(semn_inmultit,semn_impartit,semn1,semn2)

begin  


if (semn_inmultit='1' or semn_impartit='1') then
	
if (semn1='1' and semn2='1') then 
	semn3_final<='0';
end if;
if (semn1='0' and semn2='0') then 
	semn3_final<='0';
end if;
if (semn1='1' and semn2='0') then 
	semn3_final<='1';
end if;
if (semn1='0' and semn2='1') then 
	semn3_final<='1';

end if;
end if;


end process semn;	
	
	
end Behavioral;