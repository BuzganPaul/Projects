library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity scazator is
Port ( 
semn_minus : in std_logic;
prim_binar: in std_logic_vector(7 downto 0);
secund_binar: in std_logic_vector(7 downto 0);
semn1 : in std_logic;
semn2 : in std_logic;

rezultat2 : out std_logic_vector(7 downto 0);
semn2_final : out std_logic);


end scazator ;
 
architecture Behavioral of scazator is
 
begin 
	
scazator: process(secund_binar,prim_binar,semn_minus,semn1,semn2)
begin
if semn_minus='1' then	
if (semn1='0' and semn2='1') then 
	rezultat2<=prim_binar+secund_binar;
	semn2_final<='0';
end if;
	
if (semn1='0' and semn2='0') then 
	if (prim_binar > secund_binar) then
	rezultat2<=prim_binar-secund_binar;
	semn2_final<='0';
	else
	rezultat2<=secund_binar-prim_binar;
	semn2_final<='1';
end if;
end if;

if (semn1='1' and semn2='1') then 
	if (prim_binar > secund_binar) then
	rezultat2<=prim_binar-secund_binar;
	semn2_final<='1';
	else
	rezultat2<=secund_binar-prim_binar;
	semn2_final<='0';
end if;
end if;

end if;
end process scazator;	
	
	
	
end Behavioral;