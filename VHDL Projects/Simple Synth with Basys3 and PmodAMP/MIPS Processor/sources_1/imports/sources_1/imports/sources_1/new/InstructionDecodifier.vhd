----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/31/2019 02:20:27 PM
-- Design Name: 
-- Module Name: InstructionDecodifier - Behavioral
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

entity InstructionDecodifier is
port (
clk : in std_logic;
instruction : in std_logic_vector (15 downto 0);


wd : in std_logic_vector (15 downto 0);
wen : in std_logic;

RegWrite : in std_logic;
RegDst : in std_logic;
ExtOp : in std_logic;


func : out std_logic_vector (2 downto 0);
sa : out std_logic;
ext_imm : out std_logic_vector (15 downto 0);

rd1 : out std_logic_vector (15 downto 0);
rd2 : out std_logic_vector (15 downto 0);

nr_tr : out std_logic_vector (15 downto 0));

end InstructionDecodifier;

architecture Behavioral of InstructionDecodifier is

type BlocRegistru is array (0 to 7) of std_logic_vector(15 downto 0);
signal Registru1 : BlocRegistru := (
"0000000000000000",
"0000000000000000",
"0000000000000000",
others => "0000000000000000" 
);

signal waaux : std_logic_vector(2 downto 0);

begin

waaux<=instruction(9 downto 7) when RegDst='0' else instruction(6 downto 4);


process(clk)
begin
if rising_edge(clk) then

if wen = '1' then 
if RegWrite = '1' then 
Registru1(conv_integer(waaux)) <= wd;
end if;
end if;

end if;
end process;


rd1 <= Registru1(conv_integer(instruction(12 downto 10)));
rd2 <= Registru1(conv_integer(instruction(9 downto 7)));
nr_tr <= Registru1(3);

func<=instruction(2 downto 0);
sa<=instruction(3);

process(ExtOp, instruction(6 downto 0))
begin

if ExtOp='0' then
Ext_Imm(6 downto 0)<=instruction(6 downto 0);
Ext_Imm(15 downto 7)<=(others=>'0');
elsif ExtOp='1' then

if instruction(6)='0' then
Ext_Imm(6 downto 0)<=instruction(6 downto 0);
Ext_Imm(15 downto 7)<=(others=>'0');
else
Ext_Imm(6 downto 0)<=instruction(6 downto 0);
Ext_Imm(15 downto 7)<=(others=>'1');
end if;
end if;

end process;

end Behavioral;
