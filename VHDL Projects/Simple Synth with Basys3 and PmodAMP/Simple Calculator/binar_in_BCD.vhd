library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity binar_in_BCD is
Port ( 

paralelBinar : in std_logic_vector(7 downto 0);
semn_final_final : in std_logic;


paralel1: out std_logic_vector(1 downto 0);
paralel2: out std_logic_vector(3 downto 0);
paralel3: out std_logic_vector(3 downto 0));


end binar_in_BCD ;
 
architecture Behavioral of binar_in_BCD is
 
begin 
process ( paralelBinar, semn_final_final )
    variable hex_src : std_logic_vector (4 downto 0) ;
    variable bcd     : std_logic_vector (11 downto 0) ;
begin
    bcd             := (others => '0') ;
    bcd(2 downto 0) := paralelBinar(7 downto 5) ;
    hex_src         := paralelBinar(4 downto 0) ;

    for i in hex_src'range loop
        if bcd(3 downto 0) > "0100" then
            bcd(3 downto 0) := bcd(3 downto 0) + "0011" ;
        end if ;
        if bcd(7 downto 4) > "0100" then
            bcd(7 downto 4) := bcd(7 downto 4) + "0011" ;
        end if ;

        bcd := bcd(10 downto 0) & hex_src(hex_src'left) ;
        hex_src := hex_src(hex_src'left - 1 downto hex_src'right) & '0' ;
    end loop ;

    paralel1(0) <= bcd(8) ;
	paralel1(1) <= semn_final_final ;
    paralel2 <= bcd(7  downto 4) ;
    paralel3 <= bcd(3  downto 0) ;
end process ;

end Behavioral;