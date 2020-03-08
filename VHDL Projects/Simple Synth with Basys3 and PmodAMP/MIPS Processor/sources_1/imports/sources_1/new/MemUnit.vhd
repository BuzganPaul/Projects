----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/08/2019 01:22:55 AM
-- Design Name: 
-- Module Name: MemUnit - Behavioral
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

entity MemUnit is
Port ( 
clk : in std_logic;
MemWrite : in std_logic;
Address : in std_logic_vector(15 downto 0);
WriteData : in std_logic_vector(15 downto 0);
MemData : out std_logic_vector(15 downto 0));

end MemUnit;

architecture Behavioral of MemUnit is

type RAM is array (0 to 255) of std_logic_vector (15 downto 0);
signal RAM1: RAM:= (
others => "0000000000000000" 
);

begin

process (clk)
begin
if clk'event and clk = '1' then
if MemWrite = '1' then
RAM1(conv_integer(Address)) <= WriteData;
MemData <= WriteData;
else
MemData <= RAM1( conv_integer(Address));
end if;
end if;
MemData <= RAM1( conv_integer(Address));
end process;

end Behavioral;
