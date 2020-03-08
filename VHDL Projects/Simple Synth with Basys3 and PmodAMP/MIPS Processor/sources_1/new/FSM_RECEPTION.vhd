----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 05/28/2019 07:30:00 PM
-- Design Name: 
-- Module Name: FSM_RECEPTION - Behavioral
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

entity FSM_RECEPTION is
 Port ( 
 clock : in STD_LOGIC;
 BAUD_EN : in STD_LOGIC;
 reset : in STD_LOGIC;
 RX : in STD_LOGIC;
 
 RX_data : out STD_LOGIC_VECTOR(7 downto 0);
 RX_ready : out STD_LOGIC);
end FSM_RECEPTION;

architecture Behavioral of FSM_RECEPTION is

type state_type is (start_state, bit_state, stop_state, wait_state, idle_state);
signal state : state_type; 
signal bit_count : std_logic_vector(2 downto 0):="000"; 
signal baud_count : std_logic_vector(3 downto 0):="0000"; 

begin


process1: process (clock, reset)
begin
if (reset ='1') then
state <=idle_state;
baud_count<="0000";
elsif (clock='1' and clock'event and BAUD_EN='1') then
case state is
    when start_state =>   
        
        if (baud_count = "0111") then
        if(RX = '0') then 
             state <= bit_state;
             baud_count<="0000";
        end if;
        end if;
        if(baud_count < "0111") then 
             state <= start_state;
             baud_count<=baud_count+1;
        end if;
        if(RX = '1') then 
        state <= idle_state;
        baud_count<="0000";
        end if;
    when bit_state => if (baud_count="1111") then
                      RX_data(conv_integer(bit_count))<=RX;
                      bit_count<=bit_count+1;       
                      end if;
                      
                      if (bit_count = "111" AND baud_count="1111") then
                      state <= stop_state;        
                      else
                      state <= bit_state;
                      baud_count <= baud_count+'1';
                      end if;
    when stop_state => if(baud_count="1111") then 
                       state <= wait_state; 
                       else
                       state <= stop_state;
                       baud_count<= baud_count + '1'; 
                       end if;
    when wait_state => if (baud_count="0111") then
            state <= idle_state;
            bit_count<="000";
            baud_count<="0000";
            else
            if(baud_count<"0111") then
            state <= wait_state;
            baud_count<= baud_count + '1';
            end if;
            end if;
    when idle_state => if RX = '0' then
            state <= start_state;
        end if;
end case;
end if;
end process process1; 

process2: process (state, baud_count)
begin
case state is
when start_state => RX_ready<='0';
   
when bit_state => RX_ready<='0';
 
when stop_state => RX_ready<='0';

when wait_state => RX_ready<='1';
                   
when idle_state => RX_ready<='0';
         
end case;
end process process2; 

end Behavioral;