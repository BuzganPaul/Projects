--librarii

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

--entitate
entity calculator_main is
   port( 
   
   
   --butoanele pt clock-uri
   Clock_1: in std_logic;   
   Clock_2: in std_logic;      
   Clock_3: in std_logic; 
   
   --switchuri pt semne
   semn_plus: in std_logic;     
   semn_minus: in std_logic;      
   semn_inmultit: in std_logic;
   semn_impartit: in std_logic;
   
   
   --active pe 0 pt primul nr, respectiv pe 1 pt al doilea 
   Clock1_enable_B: in std_logic;
   
   --semnal pt incarcare paralela (egalul)

   par_1: in std_logic;
   
   --switcherul pt valori
   overlord: in std_logic;
   
   --anodul selectat (afisaj)
   anod: out STD_LOGIC_VECTOR (3 downto 0);
   
   --catodul cu forma nr sau semnului (afisaj)
   
   catod : out STD_LOGIC_VECTOR (6 downto 0);
   
   --reset
   Reset: in std_logic);
   

    
 	 
end calculator_main;

--arhitectura

architecture ARH_calculator of calculator_main is

--semnalele ce tin nr

signal temp1: std_logic_vector(1 downto 0);
signal temp2: std_logic_vector(3 downto 0);
signal temp3: std_logic_vector(3 downto 0);
signal temp4: std_logic_vector(1 downto 0);
signal temp5: std_logic_vector(3 downto 0);
signal temp6: std_logic_vector(3 downto 0);

--semnale pt incarcari paralele

signal paralel1: std_logic_vector(1 downto 0);
signal paralel2: std_logic_vector(3 downto 0);
signal paralel3: std_logic_vector(3 downto 0);
signal paralelBinar: std_logic_vector(7 downto 0);

--semnale pt selectare afisajului
signal Output1: std_logic_vector(1 downto 0);
signal Output2: std_logic_vector(3 downto 0);
signal Output3: std_logic_vector(3 downto 0);

--semnale pt nr transformate in binar 
signal prim_binar: std_logic_vector(7 downto 0);
signal secund_binar: std_logic_vector(7 downto 0);


--semnale pt semn
signal semn1: std_logic;
signal semn2: std_logic;
signal semn_final: std_logic;
signal semn2_final: std_logic;
signal semn3_final: std_logic;
signal semn_final_final: std_logic;


--rezultat 
signal rezultat:std_logic_vector(7 downto 0);
signal rezultat2:std_logic_vector(7 downto 0);
signal rezultat3:std_logic_vector(7 downto 0);
signal rezultat4:std_logic_vector(7 downto 0);
signal rezultat4_1:std_logic_vector(7 downto 0);



--erori
signal eroare: std_logic; 
signal eroare1: std_logic;
signal eroare2: std_logic;
signal eroare3: std_logic;
signal eroare4: std_logic;
signal eroare5: std_logic;



--semnalele de clock dupadebounce
signal   Clock1: std_logic;   
signal   Clock2: std_logic;      
signal   Clock3: std_logic;
signal   par: std_logic;

--semnale ce tin 7segmente
signal ss1 : STD_LOGIC_VECTOR (6 downto 0);
signal ss2 : STD_LOGIC_VECTOR (6 downto 0);
signal ss3 : STD_LOGIC_VECTOR (6 downto 0);
signal ss4 : STD_LOGIC_VECTOR (6 downto 0);	

--pt afisare

signal y : INTEGER;


begin
	
--debounce pt butoane

prim_clock: entity deBounce port map (overlord, Clock_1, Clock1);  
secund_clock: entity deBounce port map (overlord, Clock_2, Clock2);
treilea_clock: entity deBounce port map (overlord, Clock_3, Clock3);
paralel_de: entity deBounce port map (overlord, par_1, par);  
	

--procesele ce desemneaza fiecare counter in parte

--pt primul nr	
counter_1: process(Clock1,Reset,par)
	begin  
	
	  if (rising_edge(par)) then
         temp1 <= paralel1;
	  end if;
      if Reset='1' then
         temp1 <= "00";
      elsif(rising_edge(Clock1)) then
         if Clock1_enable_B='0' then
            if temp1="11" then
               temp1<="00";
            else
               temp1 <= temp1 + '1';
            end if;
         end if;
      end if;
end process counter_1; 	


counter_2: process(Clock2,Reset,par)
	begin  
	
	  if (rising_edge(par)) then
         temp2 <= paralel2;
	  end if;
      if Reset='1' then
         temp2 <= "0000";
      elsif(rising_edge(Clock2)) then
         if Clock1_enable_B='0' then
            if temp2="1001" then
               temp2<="0000";
            else
               temp2 <= temp2 + '1';
            end if;
         end if;
      end if;
end process counter_2;


counter_3: process(Clock3,Reset,par)
	begin  
	
	  if (rising_edge(par)) then
         temp3 <= paralel3;
	  end if;
      if Reset='1' then
         temp3 <= "0000";
      elsif(rising_edge(Clock3)) then
         if Clock1_enable_B='0' then
            if temp3="1001" then
               temp3<="0000";
            else
               temp3 <= temp3 + '1';
            end if;
         end if;
      end if;
end process counter_3;




--pt al doilea nr

counter_4: process(Clock1,Reset,par)
   begin
      if (Reset='1' or (rising_edge(par))) then
         temp4 <= "00";
      elsif(rising_edge(Clock1)) then
         if Clock1_enable_B='1' then
            if temp4="11" then
               temp4<="00";
            else
               temp4 <= temp4 + '1';
            end if;
         end if;
      end if;
end process counter_4;


counter_5: process(Clock2,Reset,par)
   begin
      if (Reset='1' or (rising_edge(par))) then
         temp5 <= "0000";
      elsif(rising_edge(Clock2)) then
         if Clock1_enable_B='1' then
            if temp5="1001" then
               temp5<="0000";
            else
               temp5 <= temp5 + '1';
            end if;
         end if;
      end if;
end process counter_5;


counter_6: process(Clock3,Reset,par)
   begin
      if (Reset='1' or (rising_edge(par))) then
         temp6 <= "0000";
      elsif(rising_edge(Clock3)) then
         if Clock1_enable_B='1' then
            if temp6="1001" then
               temp6<="0000";
            else
               temp6 <= temp6 + '1';
            end if;
         end if;
      end if;
end process counter_6;

--output pt fiecare counter

afisaj: entity afisaj_1 port map (Clock1_Enable_B,temp1,temp2,temp3,temp4,temp5,temp6,Output1,Output2,Output3);
	
--BCD in binar	

binar: entity BCD_in_binar port map (temp1,temp2,temp3,prim_binar,semn1);
binar2: entity BCD_in_binar port map (temp4,temp5,temp6,secund_binar,semn2);
	
--sumatorul pe 8 biti 

sumator: entity sumator port map (semn_plus,prim_binar,secund_binar,semn1,semn2,rezultat,semn_final);
	
--scazatorul pe 8 biti

scazator: entity scazator port map (semn_minus,prim_binar,secund_binar,semn1,semn2,rezultat2,semn2_final);
	
--semne inmultire/impartire	

semne:	entity semn port map (semn_inmultit,semn_impartit,semn1,semn2,semn3_final);
	
--multiplicator (cu algoritm)

inmultitor: entity inmultitor port map (semn_inmultit,prim_binar,secund_binar,rezultat3);

--impartitorul (cu algoritm)

impartitor: entity impartitor port map (semn_impartit,prim_binar,secund_binar,rezultat4,rezultat4_1); 
	
--procedeul de gasire a unei erori

eroare_prim: entity eroare1 port map (semn_plus,semn_minus,semn_inmultit,semn_impartit,prim_binar,secund_binar,Rezultat,Rezultat2,Rezultat3,Rezultat4,eroare1,eroare2,eroare3,eroare4,eroare5);
eroare_final: entity eroare_final port map (eroare1,eroare2,eroare3,eroare4,eroare5,eroare);
	
--procedeul de selectie al rezultatului 

procedeu_selectie: entity selectie port map (semn_plus,semn_minus,semn_inmultit,semn_impartit,semn_final,semn2_final,semn3_final,rezultat,rezultat2,rezultat3,rezultat4,paralelBinar,semn_final_final);	
	
--8biti inapoi in BCD pt incarcare paralela
binary_again: entity binar_in_BCD port map (paralelBinar,semn_final_final,paralel1,paralel2,paralel3);
	
--BCD in 7 segmente

segmente_1: entity bcd_7segment_0 port map (Output1,eroare,ss1);
segmente_2: entity bcd_7segment_1 port map (Output1,eroare,ss2);
segmente_3: entity bcd_7segment port map (Output2,eroare,ss3);
segmente_4: entity bcd_7segment port map (Output3,eroare,ss4);
	
afisaj_rapid: process
variable x : INTEGER :=0;
    begin
            x := x+1;
            y<=x;
            if (x= 4) then  
				x := 0;
            end if;
			wait for 20 ns;
end process afisaj_rapid;

selectie_anod: process(y,ss1,ss2,ss3,ss4)
begin
case y is
    when 1 => anod<="1110";
                 catod<=ss4;
    when 2 => anod<="1101";
                 catod<=ss3;
    when 3 => anod<="1011";
                 catod<=ss2;
    when 4 => anod<="0111";
                 catod<=ss1;
    when others =>  anod<="0000";
                    catod<="0000000";
    end case;
end process selectie_anod;

	
end ARH_calculator;	