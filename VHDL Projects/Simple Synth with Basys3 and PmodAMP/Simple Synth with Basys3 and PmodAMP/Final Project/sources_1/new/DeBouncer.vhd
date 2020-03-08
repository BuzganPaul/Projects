----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/10/2019 10:00:23 PM
-- Design Name: 
-- Module Name: DeBouncer - Behavioral
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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity DeBouncer is
Port ( clk_in1 : in STD_LOGIC;
           btn : in STD_LOGIC;
           enable : out STD_LOGIC);

end DeBouncer;

architecture Behavioral of DeBouncer is

component CounterDB is
	port(in1 : in std_logic;
	     out1 : out std_logic_vector(15 downto 0));
end component;

signal temp1: std_logic_vector(15 downto 0);
signal temp2: std_logic;
signal D1: std_logic;
signal Q1: std_logic;
signal D2: std_logic;
signal Q2: std_logic;
signal D3: std_logic;
signal Q3: std_logic;


begin

D1<=btn;

C1: CounterDB port map (clk_in1, temp1);

temp2<=temp1(0) AND temp1(1) AND temp1(2) AND temp1(3) AND temp1(4) AND temp1(5) AND temp1(6) AND temp1(7) AND temp1(8) AND temp1(9) AND temp1(10) AND temp1(11) AND temp1(12) AND  temp1(13) AND temp1(14) AND temp1(15);

process(clk_in1, D1)
begin
 if rising_edge(clk_in1) then
 if temp2='1' then
 Q1 <= D1;
 end if;
 end if;
end process; 

D2<=Q1;


process(clk_in1, D2)
begin
 if rising_edge(clk_in1) then
 Q2 <= D2;
 end if;
end process; 

D3<=Q2;

process(clk_in1, D3)
begin
 if rising_edge(clk_in1) then
 Q3 <= D3;
 end if;
end process; 

enable<=Q2 AND NOT(Q3);

end Behavioral;
