----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/31/2019 04:43:21 PM
-- Design Name: 
-- Module Name: UnityControl - Behavioral
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

entity UnityControl is
port (
instr : in std_logic_vector (2 downto 0);

RegDst : out std_logic;
ExtOp : out std_logic;
ALUSrc : out std_logic;
Branch : out std_logic;
BranchNot : out std_logic;
Jump : out std_logic;
ALUOp : out std_logic_vector (1 downto 0);
MemWrite : out std_logic;
MemtoReg : out std_logic;
RegWrite : out std_logic);
end UnityControl;

architecture Behavioral of UnityControl is

begin


process(instr)
begin

RegDst <='0';
ExtOp <='0';
ALUSrc <='0';
Branch <='0';
BranchNot <='0';
Jump <='0';
ALUOp <=(others=>'0');
MemWrite <='0';
MemtoReg <='0';
RegWrite <='0';

case instr is
when "000" =>RegWrite<='1';
             RegDst<='1';
when "001" =>RegWrite<='1';
             ExtOp<='1';
             AluSrc<='1';
             AluOp<="00";
when "010" =>RegWrite<='1';
             MemtoReg<='1';
             AluSrc<='1';
             AluOp<="00";
when "011" =>MemWrite<='1';
             AluSrc<='1';
             AluOp<="00";
when "100" =>Branch<='1';
             AluOp<="10";
when "101" =>BranchNot<='1';
             AluOp<="11";
when "110" =>RegWrite<='1';
             ExtOp<='1';
             AluSrc<='1';
             AluOp<="01";
when "111" =>Jump<='1';

when others =>
RegDst <='0';
ExtOp <='0';
ALUSrc <='0';
Branch <='0';
Jump <='0';
ALUOp <=(others=>'0');
MemWrite <='0';
MemtoReg <='0';
RegWrite <='0';
end case;

end process;

end Behavioral;
