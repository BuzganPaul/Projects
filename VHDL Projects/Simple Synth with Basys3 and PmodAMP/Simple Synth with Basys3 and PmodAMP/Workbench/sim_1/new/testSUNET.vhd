----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 01/08/2020 11:50:01 PM
-- Design Name: 
-- Module Name: testSUNET - Behavioral
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


-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity testSUNET is
end testSUNET;

architecture Behavioral of testSUNET is

component Main is
    Port ( ClkIn : in STD_LOGIC;
           switch : in STD_LOGIC_VECTOR (15 downto 0);
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           freq : out STD_LOGIC;
           lit : out STD_LOGIC_VECTOR (15 downto 0);
           gain : out STD_LOGIC;
           shutdown : out STD_LOGIC;
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end component;

           signal ClkIn : STD_LOGIC := '0';
           signal switch : STD_LOGIC_VECTOR (15 downto 0):="0000000000000000";
           signal btn : STD_LOGIC_VECTOR (4 downto 0):="00000";
           signal freq : STD_LOGIC;
           signal lit :  STD_LOGIC_VECTOR (15 downto 0);
           signal gain :  STD_LOGIC;
           signal shutdown :  STD_LOGIC;
           signal an : STD_LOGIC_VECTOR (3 downto 0);
           signal cat : STD_LOGIC_VECTOR (6 downto 0);
           
           constant clk_period : time:= 10 ns;

begin

process
begin

ClkIn<='1';
wait for clk_period/2;
ClkIn<='0';
wait for clk_period/2;

end process;

testing: Main port map(ClkIn, switch, btn, freq, lit, gain, shutdown, an, cat);

test1:process
begin

switch <= "1000000000000000";
btn <="10000";

wait for 10000 ns;

btn <="00001";

wait for 10000 ns;

switch <= "0000001000000000";

btn <="10000";

wait for 10000 ns;

btn <="00001";

wait for 10000 ns;

end process;



end Behavioral;
