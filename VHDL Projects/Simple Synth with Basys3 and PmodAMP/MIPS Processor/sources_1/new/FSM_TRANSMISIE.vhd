----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 05/20/2019 06:16:07 PM
-- Design Name: 
-- Module Name: FSM - Behavioral
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

entity FSM_TRASNMISIE is
 Port ( 
 clock : in STD_LOGIC;
 TX_data : in STD_LOGIC_VECTOR(7 downto 0);
 TX_EN : in STD_LOGIC;
 reset : in STD_LOGIC;
 BAUD_EN : in STD_LOGIC;
 TX : out STD_LOGIC;
 TX_ready : out STD_LOGIC);
end FSM;

architecture Behavioral of FSM_TRANSMISIE is

type state_type is (start_state, bit_state, stop_state, idle_state);
signal state : state_type; 
signal bit_count : std_logic_vector(2 downto 0):="000"; 

begin


process1: process (clock, reset, TX_EN, BAUD_EN)
begin
if (reset ='1') then
state <=idle_state;
elsif (clock='1' and clock'event and BAUD_EN='1') then
case state is
    when start_state => state <= bit_state;
    when bit_state =>   
        if (bit_count = "111") then
             state <= stop_state;
             bit_count<="000";
        else
             state <= bit_state;
             bit_count<=bit_count+1;
        end if;
    when stop_state => state <= idle_state;
    when idle_state => 
        if TX_EN = '1' then
            state <= start_state;
        end if;
end case;
end if;
end process process1; 

process2: process (state)
begin
case state is
when start_state => TX<='0';
    TX_ready<='0';
when bit_state => TX<=TX_data(conv_integer(bit_count));
                   TX_ready<='0';
when stop_state => TX<='1';
                    TX_ready<='1';
when idle_state => TX<='1';
            TX_ready<='1';
end case;
end process process2; 

end Behavioral;
