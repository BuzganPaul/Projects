 library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
 
entity deBounce is
Port (
DATA: in std_logic;
CLK : in std_logic;
OP_DATA : out std_logic);
end deBounce ;
 
architecture Behavioral of deBounce is
 
Signal r1, q1: std_logic;
Signal r2, q2: std_logic;
Signal r3, q3: std_logic;
 
begin
 
 
bs1: process( CLK )
 begin
  q1<=DATA;
 end process bs1;
 
bs2: process( q1, CLK )
 begin
  q2<=q1;
 end process bs2;
 
 bs3: process( q2, CLK )
 begin
  q3<=q2;
 end process bs3;
 
 OP_DATA<=q1 and q2 and q3;
 
end Behavioral;