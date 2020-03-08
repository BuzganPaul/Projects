----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/07/2019 11:04:24 PM
-- Design Name: 
-- Module Name: ExecUnit - Behavioral
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

entity ExecUnit is
port(

rd1 : in std_logic_vector (15 downto 0);
rd2 : in std_logic_vector (15 downto 0);
Ext_Imm : in std_logic_vector (15 downto 0);
ALUSrc : in std_logic;
sa : in std_logic;
func : in std_logic_vector (2 downto 0);
ALUOp : in std_logic_vector (1 downto 0);
Zero : out std_logic;
ALURes : out std_logic_vector (15 downto 0));
end ExecUnit;

architecture Behavioral of ExecUnit is
signal temp : std_logic;
signal nr2 : std_logic_vector (15 downto 0);
signal suma : std_logic_vector (15 downto 0);
signal scadere : std_logic_vector (15 downto 0);
signal shiftstanga : std_logic_vector (15 downto 0);
signal shiftdreapta : std_logic_vector (15 downto 0);
signal rezAND : std_logic_vector (15 downto 0);
signal rezOR : std_logic_vector (15 downto 0);
signal rezXOR : std_logic_vector (15 downto 0);
signal rezNAND : std_logic_vector (15 downto 0);

begin

nr2<=rd2 when ALUSrc='0' else Ext_Imm;

suma <= rd1 + nr2; 
scadere <= rd1 - nr2;
rezAND <= rd1 AND nr2; 
rezOR <= rd1 OR nr2;
rezXOR <= rd1 XOR nr2; 
rezNAND <= NOT(rd1 AND nr2); 

shiftstanga(15 downto 0) <= rd1(13 downto 0) & "00"; 
shiftdreapta(15 downto 0) <= "00" & rd1(15 downto 2);

temp<='1' when rd1=rd2 else '0';



Process(suma, scadere, shiftstanga, shiftdreapta, rezAND, rezOR, rezXOR, rezNAND, temp)

Begin

if ALUSrc='0' then 

case func is

when "000" => ALURes<=suma;
when "001" => ALURes<=scadere;
when "010" => ALURes<=shiftstanga;
when "011" => ALURes<=shiftdreapta;
when "100" => ALURes<=rezAND;
when "101" => ALURes<=rezOR;
when "110" => ALURes<=rezXOR;
when "111" => ALURes<=rezNAND;

end case;

end if;

if ALUSrc='1' then 

case ALUOp is

when "00" => ALURes<=suma;
when "01" => ALURes<=scadere;
when "10" => Zero<=temp;
when "11" => Zero<=temp;

end case;

end if;

end process;



end Behavioral;
