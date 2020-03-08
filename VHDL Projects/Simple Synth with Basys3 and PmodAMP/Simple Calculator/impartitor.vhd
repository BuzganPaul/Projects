library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity impartitor is
Port ( 
semn_impartit : in std_logic;
prim_binar: in std_logic_vector(7 downto 0);
secund_binar: in std_logic_vector(7 downto 0);

Rezultat4 : out std_logic_vector(7 downto 0);
Rezultat4_1 : out std_logic_vector(7 downto 0));


end impartitor ;
 
architecture Behavioral of impartitor is
 
begin  
	
impartire: Process (prim_binar,secund_binar,semn_impartit)
variable cnt : STD_LOGIC_Vector(7 downto 0);
variable temporar : std_logic_vector(7 downto 0);
begin
if(secund_binar >"00000000")  then
 if (semn_impartit='1') then	
if (prim_binar < secund_binar) then
        cnt := "00000000";
        temporar := prim_binar;
elsif (prim_binar = secund_binar) then
	cnt := "00000001";
    temporar := (others => '0');
elsif (prim_binar > secund_binar) then
        cnt := "00000001";
        temporar := (prim_binar - secund_binar);
        while (temporar >= secund_binar) loop
                temporar:= (temporar - secund_binar);
                cnt := cnt + "00000001";
        end loop;
end if;
Rezultat4 <= cnt;
Rezultat4_1 <= temporar;
end if;
end if;

end process impartire;	
	
	
	
	
	
	
end Behavioral;