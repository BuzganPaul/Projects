----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/13/2019 08:56:25 PM
-- Design Name: 
-- Module Name: Controller - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Controller is
  Port ( sw : in STD_LOGIC_VECTOR(15 downto 0);
   led : out STD_LOGIC_VECTOR(15 downto 0);
   outAfisor : out STD_LOGIC_VECTOR(15 downto 0));
end Controller;

architecture Behavioral of Controller is

begin

process(sw)
begin

if ((sw(15) or sw(14) or sw(13) or sw(12) or sw(11) or sw(10) or sw(9) or sw(8) or sw(7) or sw(6)) = '1') then

if((sw(15) xor sw(14) xor sw(13) xor sw(12) xor sw(11) xor sw(10) xor sw(9) xor sw(8) xor sw(7) xor sw(6) ) = '1') then

case sw(15 downto 6) is
  when "1000000000" =>   outAfisor <= "1100000010110000"; --C0b0
                               led(15 downto 6)<="1000000000";
  when "0100000000" =>   outAfisor <= "1100000110110001"; --C1b1
                               led(15 downto 6)<="0100000000";
  when "0010000000" =>   outAfisor <= "1100001010110010"; --C2b2
                               led(15 downto 6)<="0010000000";
  when "0001000000" =>   outAfisor <= "1100001110110011"; --C3b3
                               led(15 downto 6)<="0001000000";
  when "0000100000" =>   outAfisor <= "1100010000110100"; --C4b4
                                led(15 downto 6)<="0000100000";
  when "0000010000" =>   outAfisor <= "1100010110110101"; --C5b5
                                led(15 downto 6)<="0000010000";
  when "0000001000" =>   outAfisor <= "1100011010110110"; --C6b6
                                led(15 downto 6)<="0000001000";
  when "0000000100" =>   outAfisor <= "1100011110110111"; --C7b7
                                led(15 downto 6)<="0000000100";
  when "0000000010" =>   outAfisor <= "1100100010111000"; --C8b8
                               led(15 downto 6)<="0000000010";
  when "0000000001" =>   led(15 downto 6)<="0000000001";
                          outAfisor <= "1100100110111001"; --C9b9

  
  when others => outAfisor <= "1111111111111111"; --FFFF
end case;

else

outAfisor<="1110101010100000"; --ERRO

end if;

else

outAfisor<="0101111011101100"; --SEEC

end if;

case sw(5 downto 0) is

  when "100000" =>   led(5 downto 0)<="100000";
  when "010000" =>   led(5 downto 0)<="010000";
  when "001000" =>   led(5 downto 0)<="001000";
  when "000100" =>   led(5 downto 0)<="000100";
  when "000010" =>   led(5 downto 0)<="000010";
  when "000001" =>   led(5 downto 0)<="000001";
  when others =>   led(5 downto 0)<="111111";

end case;


end process;


end Behavioral;
