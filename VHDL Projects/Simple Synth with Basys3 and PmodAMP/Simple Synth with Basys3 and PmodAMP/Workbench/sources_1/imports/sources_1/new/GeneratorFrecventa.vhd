----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/10/2019 10:40:06 PM
-- Design Name: 
-- Module Name: GeneratorFrecventa - Behavioral
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

entity GeneratorFrecventa is
 Port ( ClkIn  : in STD_LOGIC;
 btn  : in STD_LOGIC_VECTOR(4 downto 0);
 counterFrecventa  : in integer;
 frecventaFINAL : out STD_LOGIC);
end GeneratorFrecventa;

architecture Behavioral of GeneratorFrecventa is

signal countLocal : STD_LOGIC_VECTOR(31 DOWNTO 0) := (OTHERS=>'0');

signal output : std_logic := '1';

begin

 process (clkin) begin
 if (btn(0) = '1' OR btn(1) = '1' OR btn(2) = '1' OR btn(3) = '1' OR btn(4) = '1') then 
        if rising_edge(clkin) then 
                countLocal <= countLocal + '1'; 
                if ((countLocal = conv_std_logic_vector(counterFrecventa, 32)) OR (countLocal > conv_std_logic_vector(counterFrecventa, 32))) then 
                    output <= not output; 
                    countLocal <= (others => '0'); 
                end if;
        end if;
 
    frecventaFinal <= output; 
end if;
    end process; 


end Behavioral;
