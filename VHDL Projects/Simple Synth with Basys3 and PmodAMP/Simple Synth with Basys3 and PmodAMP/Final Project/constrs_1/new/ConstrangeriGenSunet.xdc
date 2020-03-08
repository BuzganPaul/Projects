set_property PACKAGE_PIN W5 [get_ports ClkIn]
    set_property IOSTANDARD LVCMOS33 [get_ports ClkIn]
    create_clock -add -name sys_clk_pin -period 10.00 -waveform {0 5} [get_ports ClkIn]


set_property PACKAGE_PIN R2 [get_ports {switch[15]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[15]}]

set_property PACKAGE_PIN T1 [get_ports {switch[14]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[14]}]

set_property PACKAGE_PIN U1 [get_ports {switch[13]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[13]}]

set_property PACKAGE_PIN W2 [get_ports {switch[12]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[12]}]

set_property PACKAGE_PIN R3 [get_ports {switch[11]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[11]}]

set_property PACKAGE_PIN T2 [get_ports {switch[10]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[10]}]

set_property PACKAGE_PIN T3 [get_ports {switch[9]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[9]}]

set_property PACKAGE_PIN V2 [get_ports {switch[8]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[8]}]

set_property PACKAGE_PIN W13 [get_ports {switch[7]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[7]}]

set_property PACKAGE_PIN W14 [get_ports {switch[6]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[6]}]

set_property PACKAGE_PIN V15 [get_ports {switch[5]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[5]}]

set_property PACKAGE_PIN W15 [get_ports {switch[4]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[4]}]

set_property PACKAGE_PIN W17 [get_ports {switch[3]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[3]}]

set_property PACKAGE_PIN W16 [get_ports {switch[2]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[2]}]

set_property PACKAGE_PIN V16 [get_ports {switch[1]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[1]}]

set_property PACKAGE_PIN V17 [get_ports {switch[0]}]
set_property IOSTANDARD LVCMOS33 [get_ports {switch[0]}]


set_property PACKAGE_PIN U18 [get_ports {btn[0]}]						
set_property IOSTANDARD LVCMOS33 [get_ports {btn[0]}]

set_property PACKAGE_PIN T18 [get_ports {btn[1]}]						
set_property IOSTANDARD LVCMOS33 [get_ports {btn[1]}]

set_property PACKAGE_PIN W19 [get_ports {btn[2]}]						
set_property IOSTANDARD LVCMOS33 [get_ports {btn[2]}]

set_property PACKAGE_PIN T17 [get_ports btn[3]]						
set_property IOSTANDARD LVCMOS33 [get_ports {btn[3]}]

set_property PACKAGE_PIN U17 [get_ports {btn[4]}]						
set_property IOSTANDARD LVCMOS33 [get_ports {btn[4]}]


set_property PACKAGE_PIN L1 [get_ports {lit[15]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[15]}]

set_property PACKAGE_PIN P1 [get_ports {lit[14]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[14]}]

set_property PACKAGE_PIN N3 [get_ports {lit[13]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[13]}]

set_property PACKAGE_PIN P3 [get_ports {lit[12]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[12]}]

set_property PACKAGE_PIN U3 [get_ports {lit[11]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[11]}]

set_property PACKAGE_PIN W3 [get_ports {lit[10]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[10]}]

set_property PACKAGE_PIN V3 [get_ports {lit[9]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[9]}]

set_property PACKAGE_PIN V13 [get_ports {lit[8]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[8]}]

set_property PACKAGE_PIN V14 [get_ports {lit[7]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[7]}]

set_property PACKAGE_PIN U14 [get_ports {lit[6]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[6]}]

set_property PACKAGE_PIN U15 [get_ports {lit[5]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[5]}]

set_property PACKAGE_PIN W18 [get_ports {lit[4]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[4]}]

set_property PACKAGE_PIN V19 [get_ports {lit[3]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[3]}]

set_property PACKAGE_PIN U19 [get_ports {lit[2]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[2]}]

set_property PACKAGE_PIN E19 [get_ports {lit[1]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[1]}]

set_property PACKAGE_PIN U16 [get_ports {lit[0]}]
set_property IOSTANDARD LVCMOS33 [get_ports {lit[0]}]


set_property PACKAGE_PIN A14 [get_ports {freq}]
set_property IOSTANDARD LVCMOS33 [get_ports {freq}]


set_property PACKAGE_PIN A16 [get_ports {gain}]
set_property IOSTANDARD LVCMOS33 [get_ports {gain}]

set_property PACKAGE_PIN B16 [get_ports {shutdown}]
set_property IOSTANDARD LVCMOS33 [get_ports {shutdown}]


set_property PACKAGE_PIN W4 [get_ports {an[3]}]
set_property IOSTANDARD LVCMOS33 [get_ports {an[3]}]

set_property PACKAGE_PIN V4 [get_ports {an[2]}]
set_property IOSTANDARD LVCMOS33 [get_ports {an[2]}]

set_property PACKAGE_PIN U4 [get_ports {an[1]}]
set_property IOSTANDARD LVCMOS33 [get_ports {an[1]}]

set_property PACKAGE_PIN U2 [get_ports {an[0]}]
set_property IOSTANDARD LVCMOS33 [get_ports {an[0]}]

set_property PACKAGE_PIN W7 [get_ports {cat[0]}]
set_property IOSTANDARD LVCMOS33 [get_ports {cat[0]}]

set_property PACKAGE_PIN W6 [get_ports {cat[1]}]
set_property IOSTANDARD LVCMOS33 [get_ports {cat[1]}]

set_property PACKAGE_PIN U8 [get_ports {cat[2]}]
set_property IOSTANDARD LVCMOS33 [get_ports {cat[2]}]

set_property PACKAGE_PIN V8 [get_ports {cat[3]}]
set_property IOSTANDARD LVCMOS33 [get_ports {cat[3]}]

set_property PACKAGE_PIN U5 [get_ports {cat[4]}]
set_property IOSTANDARD LVCMOS33 [get_ports {cat[4]}]

set_property PACKAGE_PIN V5 [get_ports {cat[5]}]
set_property IOSTANDARD LVCMOS33 [get_ports {cat[5]}]

set_property PACKAGE_PIN U7 [get_ports {cat[6]}]
set_property IOSTANDARD LVCMOS33 [get_ports {cat[6]}]
