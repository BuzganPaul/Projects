----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/24/2019 01:01:51 AM
-- Design Name: 
-- Module Name: test_env - Behavioral
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

entity test_env is
Port(clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR(4 downto 0);
           sw : in STD_LOGIC_VECTOR(15 downto 0);
           RX : in STD_LOGIC;
           led : out STD_LOGIC_VECTOR(15 downto 0);
           an : out STD_LOGIC_VECTOR(3 downto 0);
           TX : out STD_LOGIC;
           cat : out STD_LOGIC_VECTOR(6 downto 0));
end test_env;

architecture Behavioral of test_env is

component GeneratorMonoimpuls is
Port ( clk_in1 : in STD_LOGIC;
           btn : in STD_LOGIC;
           enable : out STD_LOGIC);
end component;

component Afisor7Seg is
Port ( clk_in : in STD_LOGIC;
           temp_in : in STD_LOGIC_VECTOR(15 downto 0);
           an_out : out STD_LOGIC_VECTOR(3 downto 0);
           cat_out : out STD_LOGIC_VECTOR(6 downto 0));
end component;

component InstructionFetch is
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
end component;

component InstructionDecodifier is
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

end component;

component UnityControl is
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
end component;

component MemUnit is
Port ( 
clk : in std_logic;
MemWrite : in std_logic;
Address : in std_logic_vector(15 downto 0);
WriteData : in std_logic_vector(15 downto 0);
MemData : out std_logic_vector(15 downto 0));

end component;

component ExecUnit is
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
end component;

component FSM_TRANSMISION is
 Port ( 
 clock : in STD_LOGIC;
 TX_data : in STD_LOGIC_VECTOR(7 downto 0);
 TX_EN : in STD_LOGIC;
 reset : in STD_LOGIC;
 BAUD_EN : in STD_LOGIC;
 TX : out STD_LOGIC;
 TX_ready : out STD_LOGIC);
end component;

component FSM_RECEPTION is
 Port ( 
 clock : in STD_LOGIC;
 BAUD_EN : in STD_LOGIC;
 reset : in STD_LOGIC;
 RX : in STD_LOGIC;
 
 RX_data : out STD_LOGIC_VECTOR(7 downto 0);
 RX_ready : out STD_LOGIC);
end component;


signal CE: std_logic;
signal iesire: std_logic_vector(15 downto 0);

signal Branch : STD_LOGIC_VECTOR(15 downto 0);
signal BranchNot : STD_LOGIC_VECTOR(15 downto 0);
signal Jump : STD_LOGIC_VECTOR(15 downto 0);



signal instr: std_logic_vector(15 downto 0);
signal PC_next: std_logic_vector(15 downto 0);

signal Read_Adress1 : STD_LOGIC_VECTOR(15 downto 0):=(others=>'0');
signal Read_Adress2 : STD_LOGIC_VECTOR(15 downto 0):=(others=>'0');
signal WD : STD_LOGIC_VECTOR(15 downto 0):=(others=>'0');
signal Exit_imm2 : STD_LOGIC_VECTOR(15 downto 0):=(others=>'0');



signal func2 :  std_logic_vector (2 downto 0);
signal sa2 : std_logic;


signal RegDst2 : std_logic;
signal ExtOp2 : std_logic;
signal ALUSrc2 : std_logic;
signal Branch2 : std_logic;
signal BranchNot2 : std_logic;
signal Jump2 : std_logic;
signal ALUOp2 : std_logic_vector (1 downto 0);
signal MemWrite2 : std_logic;
signal MemtoReg2 : std_logic;
signal RegWrite2 : std_logic;


signal ALURez2 : std_logic_vector(15 downto 0);
signal MemData2 : std_logic_vector(15 downto 0);
signal Zero : std_logic;
signal Zero2 : std_logic;

signal PCSrc : std_logic;
signal PCSrc2 : std_logic;

signal nr_transmitere : std_logic_vector(15 downto 0);
signal TX_2 : std_logic;
signal b_en : std_logic;
signal temp : std_logic_vector(13 downto 0):=(others=>'0');
signal TX_EN : std_logic;
signal TX_EN_FINAL : std_logic:='1';

signal b_en2 : std_logic;
signal temp2 : std_logic_vector(9 downto 0):=(others=>'0');
signal iesire_receptie : std_logic_vector(15 downto 0):=(others=>'0');

begin

GM: GeneratorMonoimpuls port map (clk, btn(0), CE);
GM2: GeneratorMonoimpuls port map (clk, btn(1), TX_EN);

INF: InstructionFetch port map (clk, Jump2, PCSrc, PCSrc2, CE, sw(2), Branch, BranchNot, Jump, PC_next, instr);

UC:UnityControl port map (instr(15 downto 13), RegDst2, ExtOp2, ALUSrc2, Branch2, BranchNot2, Jump2, ALUOp2, MemWrite2, MemtoReg2, RegWrite2);

IND:InstructionDecodifier port map (clk, instr, WD, CE, RegWrite2, RegDst2, ExtOp2, func2, sa2, Exit_imm2, Read_Adress1, Read_Adress2, nr_transmitere);

ExU: ExecUnit port map(Read_Adress1, Read_Adress2, Exit_imm2, ALUSrc2, sa2, func2, ALUOp2, Zero2, ALURez2);


MemUn:MemUnit  port map (clk, MemWrite2, ALURez2, Read_Adress2, MemData2);

FSM_TRANSMISION1:FSM_TRANSMISION  port map (clk, sw(15 downto 8), TX_EN_FINAL, sw(7), b_en, TX, TX_2);

FSM_RECEPTION1:FSM_RECEPTION  port map (clk, b_en2, sw(7), RX, iesire_receptie(7 downto 0), iesire_receptie(12));

process(clk)
begin

 if rising_edge(clk) then
 
 if temp="10100010110000" then
 b_en <= '1';
 temp<=(others => '0');
 else
 b_en <='0';
 temp <= temp + 1;
 end if;
 

 
 end if;
 
end process; 

process(clk)
begin

 if rising_edge(clk) then
 
 if temp2="1010001011" then
 b_en2 <= '1';
 temp2<=(others => '0');
 else
 b_en2 <='0';
 temp2 <= temp2 + 1;
 end if;
 

 
 end if;
 
end process; 


process(TX_EN, b_en, clk)
begin
if rising_edge(clk) then

if(TX_EN = '1') then 
TX_EN_FINAL <= '1';
end if;

if(b_en = '1') then 
TX_EN_FINAL <= '0';
end if;
end if;

end process; 


WD<=MemData2 when MemtoReg2='1' else ALURez2;
Zero<='1' when Read_Adress1=Read_Adress2 else '0';

PCSrc<= Zero AND Branch2;
PCSrc2<= NOT(Zero2) AND BranchNOT2;

Branch<= Exit_imm2;
BranchNot<= Exit_imm2;
Jump<= Exit_imm2;





process(PC_next, instr, sw(7 downto 5))
begin
 case sw(7 downto 5) is
 when "000" =>iesire<=instr;
 when "001" =>iesire<=PC_next;
 when "010" =>iesire<=Read_Adress1;
 when "011" =>iesire<=Read_Adress2;
 when "100" =>iesire<=Exit_imm2;
 when "101" =>iesire<=ALURez2;
 when "110" =>iesire<=MemData2;
 when "111" =>iesire<=WD;
 when others =>iesire<=(others=>'1');
 end case;
end process;


Afisor: Afisor7Seg port map (clk, iesire_receptie, an, cat);

led(0)<=RegDst2;
led(1)<=ExtOp2;
led(2)<=ALUSrc2;
led(3)<=Branch2;
led(4)<=BranchNot2;
led(5)<=Jump2;
led(6)<=ALUOp2(0);
led(7)<=ALUOp2(1);
led(8)<=MemWrite2;
led(9)<=MemtoReg2;
led(10)<=RegWrite2;
led(11)<=Zero;
led(12)<=PCSrc;
--led(13)<=PCSrc2;

--pentru transmisie
led(13)<=TX_EN_FINAL;
led(14)<=TX_EN;
led(15)<=b_en;



end Behavioral;
