library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity eroare1 is
Port ( 
semn_plus : in std_logic;
semn_minus : in std_logic;
semn_inmultit : in std_logic;
semn_impartit : in std_logic;
prim_binar: in std_logic_vector(7 downto 0);
secund_binar: in std_logic_vector(7 downto 0);
rezultat: in std_logic_vector(7 downto 0);
rezultat2: in std_logic_vector(7 downto 0);
rezultat3: in std_logic_vector(7 downto 0);
rezultat4: in std_logic_vector(7 downto 0);

eroare1 : out std_logic;
eroare2 : out std_logic;
eroare3 : out std_logic;
eroare4 : out std_logic;
eroare5 : out std_logic);


end eroare1 ;
 
architecture Behavioral of eroare1 is
 
begin 
	
procedeu_eroare: process(prim_binar,secund_binar,semn_plus,semn_minus,semn_inmultit,semn_impartit,Rezultat,Rezultat2,Rezultat3,Rezultat4)
begin
if (semn_impartit='1' and (secund_binar="00000000")) then

		eroare5<='1';
else
	eroare5<='0';
end if;	

if ((semn_plus='1' and semn_minus='1')or(semn_plus='1' and  semn_inmultit='1' ) or (semn_plus='1' and semn_impartit='1') or (semn_minus='1' and  semn_inmultit='1' ) or (semn_minus='1' and semn_impartit='1') or (semn_inmultit='1' and semn_impartit='1')) then

		eroare1<='1';
else
	eroare1<='0';
end if;

if (rezultat>"01111111" ) then

		eroare2<='1';
else
	eroare2<='0';
end if;


if (rezultat2>"01111111" ) then

		eroare3<='1';
else
	eroare3<='0';
end if;


if (rezultat3>"01111111" ) then

		eroare4<='1';
else
	eroare4<='0';
end if;
	
		
end process procedeu_eroare;	
	
	
end behavioral;