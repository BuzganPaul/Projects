----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/09/2019 10:04:27 PM
-- Design Name: 
-- Module Name: Main - Behavioral
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Main is
    Port ( ClkIn : in STD_LOGIC;
           switch : in STD_LOGIC_VECTOR (15 downto 0);
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           freq : out STD_LOGIC;
           lit : out STD_LOGIC_VECTOR (15 downto 0);
           gain : out STD_LOGIC;
           shutdown : out STD_LOGIC;
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end main;

architecture Behavioral of main is
    signal output : std_logic := '1';
    signal shutdownAUX : std_logic := '0';
    signal counter : integer := 0;
    signal countFinal : integer;
    
  component MemorieOctaveNote is
  Port ( switch : in STD_LOGIC_VECTOR (9 downto 0);
  btn : in STD_LOGIC_VECTOR (4 downto 0);
  frecventa : out INTEGER);
end component;

component Afisor7Seg is
Port ( clk_in : in STD_LOGIC;
           temp_in : in STD_LOGIC_VECTOR(15 downto 0);
           an_out : out STD_LOGIC_VECTOR(3 downto 0);
           cat_out : out STD_LOGIC_VECTOR(6 downto 0));
end component;

component DeBouncer is
Port ( clk_in1 : in STD_LOGIC;
           btn : in STD_LOGIC;
           enable : out STD_LOGIC);

end component;

component GeneratorFrecventa is
 Port ( ClkIn  : in STD_LOGIC;
 btn  : in STD_LOGIC_VECTOR(4 downto 0);
 counterFrecventa  : in integer;
 frecventaFINAL : out STD_LOGIC);
end component;

component Controller is
  Port ( sw : in STD_LOGIC_VECTOR(15 downto 0);
   led : out STD_LOGIC_VECTOR(15 downto 0);
   outAfisor : out STD_LOGIC_VECTOR(15 downto 0));
end component;

signal btnDB : STD_LOGIC_VECTOR(4 downto 0);
signal deAfisat : STD_LOGIC_VECTOR(15 downto 0);

begin

Memorie: MemorieOctaveNote port map (switch(15 downto 6), btn, countFinal);

DeBounce1: DeBouncer port map (ClkIn, btn(0), btnDB(0));
DeBounce2: DeBouncer port map (ClkIn, btn(1), btnDB(1));
DeBounce3: DeBouncer port map (ClkIn, btn(2), btnDB(2));
DeBounce4: DeBouncer port map (ClkIn, btn(3), btnDB(3));
DeBounce5: DeBouncer port map (ClkIn, btn(4), btnDB(4));

GeneratorFrecventaComp : GeneratorFrecventa port map (ClkIn, btn, countFinal, output);

Control: Controller port map(switch, lit, deAfisat);

AfisorNote: Afisor7Seg port map(ClkIn, deAfisat, an, cat);



    gain <= '0' when switch(0) = '0' else '1'; 
    freq <= output; 
    
    
    
shutdown <= '1';
    
end Behavioral;
