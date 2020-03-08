----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/09/2019 10:30:12 PM
-- Design Name: 
-- Module Name: MemorieOctaveNote - Behavioral
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
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity MemorieOctaveNote is
  Port ( switch : in STD_LOGIC_VECTOR (9 downto 0);
  btn : in STD_LOGIC_VECTOR (4 downto 0);
  frecventa : out INTEGER);
end MemorieOctaveNote;

architecture Behavioral of MemorieOctaveNote is

type memorieNote is array (0 to 11) of integer;

signal C0B0 : memorieNote:=(6116207, 5772339, 5448403, 5142710, 4854133, 4581481, 4324511, 4081799, 3852673, 3636363, 3432297, 3239600);
signal C1B1 : memorieNote:=(3057823, 2886169, 2724201, 2571289, 2427007, 2290740, 2162209, 2040858, 1926299, 1818181, 1716149, 1619826);
signal C2B2 : memorieNote:=(1528911, 1443084, 1362100, 1285644, 1213489, 1145383, 1081092, 1020418, 963149, 909090, 858067, 809906);
signal C3B3 : memorieNote:=(764450, 721547, 681050, 642826, 606744, 572691, 540549, 510209, 418574, 454545, 429033, 404953);
signal C4B4 : memorieNote:=(382225, 360772, 340524, 321412, 303372, 286345, 270274, 255105, 240786, 227272, 214516, 202477);
signal C5B5 : memorieNote:=(191112, 180386, 170262, 160706, 151686, 143172, 135137, 127552, 120393, 113636, 107258, 101238);
signal C6B6 : memorieNote:=(95556, 90193, 85131, 80353, 75843, 71586, 67568, 63776, 60196, 56818, 53629, 50619);
signal C7B7 : memorieNote:=(47448, 45096, 42565, 40176, 37921, 35793, 33784, 31888, 30098, 28409, 26814, 25309);
signal C8B8 : memorieNote:=(23889, 22548, 21282, 20088, 18960, 17896, 16892, 15944, 15049, 14204, 13407, 12654);
signal C9B9 : memorieNote:=(11944, 11274, 10641, 10044, 9480, 8948, 8446, 7972, 7524, 7102, 6703, 6327);

begin

    process (switch, btn) begin
        
        case (switch) is 
        when "1000000000" => 
         case (btn) is 
            when "00001" => frecventa <=C0B0(0);
            when "00010" => frecventa <=C0B0(1);
            when "00100" => frecventa <=C0B0(2);
            when "01000" => frecventa <=C0B0(3);
            when "10000" => frecventa <=C0B0(4);
            when "00101" => frecventa <=C0B0(5);
            when "00110" => frecventa <=C0B0(6);
            when "01100" => frecventa <=C0B0(7);
            when "10100" => frecventa <=C0B0(8);
            when "01001" => frecventa <=C0B0(9);
            when "01010" => frecventa <=C0B0(10);
            when "11000" => frecventa <=C0B0(11);
            when others => frecventa <=C0B0(0);
         end case;
            
        when "0100000000" => 
            case (btn) is 
            when "00001" => frecventa <=C1B1(0);
            when "00010" => frecventa <=C1B1(1);
            when "00100" => frecventa <=C1B1(2);
            when "01000" => frecventa <=C1B1(3);
            when "10000" => frecventa <=C1B1(4);
            when "00101" => frecventa <=C1B1(5);
            when "00110" => frecventa <=C1B1(6);
            when "01100" => frecventa <=C1B1(7);
            when "10100" => frecventa <=C1B1(8);
            when "01001" => frecventa <=C1B1(9);
            when "01010" => frecventa <=C1B1(10);
            when "11000" => frecventa <=C1B1(11);
            when others => frecventa <=C1B1(0);
         end case;
                

        when "0010000000" =>
            case (btn) is 
            when "00001" => frecventa <=C2B2(0);
            when "00010" => frecventa <=C2B2(1);
            when "00100" => frecventa <=C2B2(2);
            when "01000" => frecventa <=C2B2(3);
            when "10000" => frecventa <=C2B2(4);
            when "00101" => frecventa <=C2B2(5);
            when "00110" => frecventa <=C2B2(6);
            when "01100" => frecventa <=C2B2(7);
            when "10100" => frecventa <=C2B2(8);
            when "01001" => frecventa <=C2B2(9);
            when "01010" => frecventa <=C2B2(10);
            when "11000" => frecventa <=C2B2(11);
            when others => frecventa <=C2B2(0);
            end case;
                

        when "0001000000" =>
           case (btn) is 
            when "00001" => frecventa <=C3B3(0);
            when "00010" => frecventa <=C3B3(1);
            when "00100" => frecventa <=C3B3(2);
            when "01000" => frecventa <=C3B3(3);
            when "10000" => frecventa <=C3B3(4);
            when "00101" => frecventa <=C3B3(5);
            when "00110" => frecventa <=C3B3(6);
            when "01100" => frecventa <=C3B3(7);
            when "10100" => frecventa <=C3B3(8);
            when "01001" => frecventa <=C3B3(9);
            when "01010" => frecventa <=C3B3(10);
            when "11000" => frecventa <=C3B3(11);
            when others => frecventa <=C3B3(0);
         end case;
           
        
        when "0000100000" =>
           case (btn) is 
            when "00001" => frecventa <=C4B4(0);
            when "00010" => frecventa <=C4B4(1);
            when "00100" => frecventa <=C4B4(2);
            when "01000" => frecventa <=C4B4(3);
            when "10000" => frecventa <=C4B4(4);
            when "00101" => frecventa <=C4B4(5);
            when "00110" => frecventa <=C4B4(6);
            when "01100" => frecventa <=C4B4(7);
            when "10100" => frecventa <=C4B4(8);
            when "01001" => frecventa <=C4B4(9);
            when "01010" => frecventa <=C4B4(10);
            when "11000" => frecventa <=C4B4(11);
            when others => frecventa <=C4B4(0);
         end case;
        
        when "0000010000" =>
           case (btn) is 
            when "00001" => frecventa <=C5B5(0);
            when "00010" => frecventa <=C5B5(1);
            when "00100" => frecventa <=C5B5(2);
            when "01000" => frecventa <=C5B5(3);
            when "10000" => frecventa <=C5B5(4);
            when "00101" => frecventa <=C5B5(5);
            when "00110" => frecventa <=C5B5(6);
            when "01100" => frecventa <=C5B5(7);
            when "10100" => frecventa <=C5B5(8);
            when "01001" => frecventa <=C5B5(9);
            when "01010" => frecventa <=C5B5(10);
            when "11000" => frecventa <=C5B5(11);
            when others => frecventa <=C5B5(0);
         end case;
        
        
        when "0000001000" =>
            case (btn) is 
            when "00001" => frecventa <=C6B6(0);
            when "00010" => frecventa <=C6B6(1);
            when "00100" => frecventa <=C6B6(2);
            when "01000" => frecventa <=C6B6(3);
            when "10000" => frecventa <=C6B6(4);
            when "00101" => frecventa <=C6B6(5);
            when "00110" => frecventa <=C6B6(6);
            when "01100" => frecventa <=C6B6(7);
            when "10100" => frecventa <=C6B6(8);
            when "01001" => frecventa <=C6B6(9);
            when "01010" => frecventa <=C6B6(10);
            when "11000" => frecventa <=C6B6(11);
            when others => frecventa <=C6B6(0);
         end case;


        when "0000000100" =>
           case (btn) is 
            when "00001" => frecventa <=C7B7(0);
            when "00010" => frecventa <=C7B7(1);
            when "00100" => frecventa <=C7B7(2);
            when "01000" => frecventa <=C7B7(3);
            when "10000" => frecventa <=C7B7(4);
            when "00101" => frecventa <=C7B7(5);
            when "00110" => frecventa <=C7B7(6);
            when "01100" => frecventa <=C7B7(7);
            when "10100" => frecventa <=C7B7(8);
            when "01001" => frecventa <=C7B7(9);
            when "01010" => frecventa <=C7B7(10);
            when "11000" => frecventa <=C7B7(11);
            when others => frecventa <=C7B7(0);
         end case;
        
        when "0000000010" =>
               case (btn) is 
            when "00001" => frecventa <=C8B8(0);
            when "00010" => frecventa <=C8B8(1);
            when "00100" => frecventa <=C8B8(2);
            when "01000" => frecventa <=C8B8(3);
            when "10000" => frecventa <=C8B8(4);
            when "00101" => frecventa <=C8B8(5);
            when "00110" => frecventa <=C8B8(6);
            when "01100" => frecventa <=C8B8(7);
            when "10100" => frecventa <=C8B8(8);
            when "01001" => frecventa <=C8B8(9);
            when "01010" => frecventa <=C8B8(10);
            when "11000" => frecventa <=C8B8(11);
            when others => frecventa <=C8B8(0);
         end case;

        when "0000000001" =>
               case (btn) is 
            when "00001" => frecventa <=C9B9(0);
            when "00010" => frecventa <=C9B9(1);
            when "00100" => frecventa <=C9B9(2);
            when "01000" => frecventa <=C9B9(3);
            when "10000" => frecventa <=C9B9(4);
            when "00101" => frecventa <=C9B9(5);
            when "00110" => frecventa <=C9B9(6);
            when "01100" => frecventa <=C9B9(7);
            when "10100" => frecventa <=C9B9(8);
            when "01001" => frecventa <=C9B9(9);
            when "01010" => frecventa <=C9B9(10);
            when "11000" => frecventa <=C9B9(11);
            when others => frecventa <=C9B9(0);
         end case;

        when others =>
            case (btn) is 
            when "00001" => frecventa <=C6B6(0);
            when "00010" => frecventa <=C6B6(1);
            when "00100" => frecventa <=C6B6(2);
            when "01000" => frecventa <=C6B6(3);
            when "10000" => frecventa <=C6B6(4);
            when "00101" => frecventa <=C6B6(5);
            when "00110" => frecventa <=C6B6(6);
            when "01100" => frecventa <=C6B6(7);
            when "10100" => frecventa <=C6B6(8);
            when "01001" => frecventa <=C6B6(9);
            when "01010" => frecventa <=C6B6(10);
            when "11000" => frecventa <=C6B6(11);
            when others => frecventa <=C6B6(0);
         end case;
        end case;
      
    end process; 

end Behavioral;
