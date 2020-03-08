library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity sumator is
Port ( 
semn_plus : in std_logic;
prim_binar: in std_logic_vector(7 downto 0);
secund_binar: in std_logic_vector(7 downto 0);
semn1 : in std_logic;
semn2 : in std_logic;

rezultat : out std_logic_vector(7 downto 0);
semn_final : out std_logic);


end sumator ;
 
architecture Behavioral of sumator is
 
begin 
	 

sumator: process(semn_plus,prim_binar,secund_binar,semn1,semn2)
begin
if semn_plus='1' then	
if (semn1='0' and semn2='0') then 
	rezultat<=prim_binar+secund_binar;
	semn_final<='0';
end if;
	
if (semn1='0' and semn2='1') then 
	if (prim_binar > secund_binar) then
	rezultat<=prim_binar-secund_binar;
	semn_final<='0';
	else
	rezultat<=secund_binar-prim_binar;
	semn_final<='1';
end if;
end if;

if (semn1='1' and semn2='0') then 
	if (prim_binar > secund_binar) then
	rezultat<=prim_binar-secund_binar;
	semn_final<='1';
	else
	rezultat<=secund_binar-prim_binar;
	semn_final<='0';
end if;
end if;



if (semn1='1' and semn2='1') then 
	rezultat<=prim_binar+secund_binar;
	semn_final<='1';
end if;




end if;
end process sumator;

end Behavioral;