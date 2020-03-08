----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/23/2019 11:25:11 PM
-- Design Name: 
-- Module Name: InstructionFetch - Behavioral
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

entity InstructionFetch is
Port ( clk_in1 : in STD_LOGIC;
           Jump : in STD_LOGIC;
           PCSrc : in STD_LOGIC;
           PCSrc2 : in STD_LOGIC;
           btn : in STD_LOGIC;
           reset : in STD_LOGIC;
           Branch_Address : in STD_LOGIC_VECTOR(15 downto 0);
           BranchNot_Address : in STD_LOGIC_VECTOR(15 downto 0);
           Jump_Address : in STD_LOGIC_VECTOR(15 downto 0);
           PC_next : out STD_LOGIC_VECTOR(15 downto 0);
           instructiune : out STD_LOGIC_VECTOR(15 downto 0));
end InstructionFetch;

architecture Behavioral of InstructionFetch is

signal PC: std_logic_vector(15 downto 0):=(others=>'0');
signal PC2: std_logic_vector(15 downto 0);

signal temp1: std_logic_vector(15 downto 0);
signal temp2: std_logic_vector(15 downto 0);
signal temp0: std_logic_vector(15 downto 0);

type ROM is array (0 to 255) of std_logic_vector(15 downto 0);

signal ROM1: ROM := (
B"001_000_001_0001010", --addi 1 0 10(imm)
B"001_000_010_0000001", --addi 2 0 1(imm)
B"011_111_001_0000000", --sw 1 0 0(add)
B"011_111_010_0000010", --sw 2 0 1(add)
B"000_011_010_011_0_000", --add 3 3 2
B"000_010_000_010_0_001", --sub 2 0 2
B"001_010_010_0000001", --addi 2 2 1(imm)
B"100_001_010_0001001", --beg 1 2 9(add)
B"101_001_010_0000100", --bng 1 2 4(add)
B"010_000_100_0000000", --lw 4 0 0(add)
B"000_011_100_101_0_010", --sll 5 3 4
B"000_010_100_110_0_011", --slr 6 2 4
B"000_010_100_111_0_100", --and 7 2 4
B"000_010_100_101_0_101", --or 5 2 4
B"000_010_100_110_0_110", --xor 6 2 4
B"000_010_100_111_0_111",  --nand 7 2 4
B"111_0000000010011", --jump 19
B"100_101_110_0001100", --beg 12 6 7
others=>("000000000000000")
);

begin

PC2<=PC+1;


process(PC, clk_in1, temp2)
begin
if reset='1' then
 PC <= (others=>'0');
 end if;
 
 if rising_edge(clk_in1) then
 if btn='1' then
 PC <= temp2;
 end if;
 end if;
end process;

process(PCSrc, Branch_address, PC2)
begin
 case PCSrc is
 when '0' =>temp0<=PC2;
 when '1' =>temp0<=Branch_address;
 end case;
end process;

process(PCSrc2, BranchNot_address, temp1)
begin
 case PCSrc2 is
 when '0' =>temp1<=temp0;
 when '1' =>temp1<=BranchNot_address;
 end case;
end process;

process(Jump, Jump_address, temp0)
begin
 case Jump is
 when '0' =>temp2<=temp1;
 when '1' =>temp2<=Jump_address;
 end case;
end process;

instructiune <= ROM1(conv_integer(PC));
PC_next <= PC2;



 

end Behavioral;
