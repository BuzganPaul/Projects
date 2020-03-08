----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/10/2019 10:13:38 PM
-- Design Name: 
-- Module Name: CounterDB - Behavioral
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

entity CounterDB is
Port (     in1 : in STD_LOGIC;
           out1 : out STD_LOGIC_VECTOR(15 downto 0));
end CounterDB;

architecture Behavioral of CounterDB is
signal temp: std_logic_vector(15 downto 0):="0000000000000000";
begin

process(in1)
begin

 if rising_edge(in1) then
 temp <= temp + 1;
 end if;
 
end process; 


out1<=temp;


end Behavioral;

