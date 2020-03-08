----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/03/2019 08:07:37 PM
-- Design Name: 
-- Module Name: Afisor7Seg - Behavioral
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

entity Afisor7Seg is
Port ( clk_in : in STD_LOGIC;
           temp_in : in STD_LOGIC_VECTOR(15 downto 0);
           an_out : out STD_LOGIC_VECTOR(3 downto 0);
           cat_out : out STD_LOGIC_VECTOR(6 downto 0));
end Afisor7Seg;

architecture Behavioral of Afisor7Seg is

signal temp1_2: std_logic_vector(15 downto 0):="0000000000000000";
signal S: std_logic_vector(1 downto 0);
signal A: std_logic_vector(3 downto 0);

component Counter is
	port(in1 : in std_logic;
	     out1 : out std_logic_vector(15 downto 0));
end component;

begin

C1: Counter port map (clk_in, temp1_2);

S(0)<=temp1_2(14);
S(1)<=temp1_2(15);

process(S)
begin
 case S is
 when "00" => an_out <= "1110";
 when "01" => an_out <= "1101";
 when "10" => an_out <= "1011";
 when others => an_out <= "0111";
 end case;
end process; 


process(S, temp_in)
begin
 case S is
 when "00" => A <= temp_in(3 downto 0);
 when "01" => A <= temp_in(7 downto 4);
 when "10" => A <= temp_in(11 downto 8);
 when others => A <= temp_in(15 downto 12);
 end case;
end process; 

process (A)
BEGIN
    case A is
        when "0000"=> cat_out <="1000000";  -- '0'  format GFEDCBA invers decat esti obisnuit
        when "0001"=> cat_out <="1111001";  -- '1'
        when "0010"=> cat_out <="0100100";  -- '2'
        when "0011"=> cat_out <="0110000";  -- '3'
        when "0100"=> cat_out <="0011001";  -- '4' 
        when "0101"=> cat_out <="0010010";  -- '5'
        when "0110"=> cat_out <="0000010";  -- '6'
        when "0111"=> cat_out <="1111000";  -- '7'
        when "1000"=> cat_out <="0000000";  -- '8'
        when "1001"=> cat_out <="0010000";  -- '9'
        when "1010"=> cat_out <="0001000";  -- 'A'
        when "1011"=> cat_out <="0000011";  -- 'b'
        when "1100"=> cat_out <="1000110";  -- 'C'
        when "1101"=> cat_out <="0100001";  -- 'd'
        when "1110"=> cat_out <="0000110";  -- 'E'
        when "1111"=> cat_out <="0001110";  -- 'F'
        when others =>  NULL;
    end case;
end process;


end Behavioral;
