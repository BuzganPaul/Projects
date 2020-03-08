library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
 
entity bcd_7segment_0 is
Port ( BCDin : in STD_LOGIC_VECTOR (1 downto 0);
eroare : in  STD_LOGIC;
Seven_Segment : out STD_LOGIC_VECTOR (6 downto 0));
end bcd_7segment_0;
 
architecture Behavioral of bcd_7segment_0 is
 
begin
 
process(BCDin,eroare)
begin
if(eroare='1') then 
	Seven_Segment<="0110000";
else
	
case BCDin is
when "00" =>
Seven_Segment <= "1111111"; --- +0
when "01" =>
Seven_Segment <= "1111111"; --- +1
when "10" =>
Seven_Segment <= "1111110"; --- -0
when "11" =>
Seven_Segment <= "1111110"; --- -1

when others =>
Seven_Segment <= "1111111"; ---null
end case;

end if;
 
end process;
 
end Behavioral;