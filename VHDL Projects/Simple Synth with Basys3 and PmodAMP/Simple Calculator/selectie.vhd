library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity selectie is
Port ( 
semn_plus : in std_logic;
semn_minus : in std_logic;
semn_inmultit : in std_logic;
semn_impartit : in std_logic; 
semn_final : in std_logic;
semn2_final : in std_logic;
semn3_final : in std_logic;
rezultat: in std_logic_vector(7 downto 0);
rezultat2: in std_logic_vector(7 downto 0);
rezultat3: in std_logic_vector(7 downto 0);
rezultat4: in std_logic_vector(7 downto 0);
paralelBinar: out std_logic_vector(7 downto 0);
semn_final_final: out std_logic);


end selectie ;
 
architecture Behavioral of selectie is
 
begin 
	
procedeu_selectie: process(rezultat,rezultat2,rezultat3,Rezultat4,semn_inmultit,semn_impartit,semn_plus,semn_minus,semn_final,semn2_final,semn3_final)
begin
if ((semn_impartit='0'and semn_inmultit='1')  and (semn_minus='0' and semn_plus='0')) then

	paralelBinar<=rezultat3;
	semn_final_final<=semn3_final;
end if;	

if ((semn_impartit='1'and semn_inmultit='0')  and (semn_minus='0' and semn_plus='0')) then

	paralelBinar<=rezultat4;
	semn_final_final<=semn3_final;
end if;

if ((semn_impartit='0'and semn_inmultit='0')  and (semn_minus='1' and semn_plus='0')) then

	paralelBinar<=rezultat2;
	semn_final_final<=semn2_final;
end if;	
if ((semn_impartit='0'and semn_inmultit='0')  and (semn_minus='0' and semn_plus='1')) then

	paralelBinar<=rezultat;
	semn_final_final<=semn_final;
end if;

		
end process procedeu_selectie;
	
	
	
end behavioral;