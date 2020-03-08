#include <Servo.h>
#include <SoftwareSerial.h>
// Pinii motor 1
#define mpin00 5
#define mpin01 6
// Pinii motor 2
#define mpin10 3
#define mpin11 11
Servo srv;
char comanda;
char comandaMiscare;

int vitezaComanda = 100;
int vitezaCitita;

const int trigPin = 4;
const int echoPin = 10;
// defines variables
long duration;
int distance;

int encoder_pin = 2; // pulse output from the module
long int rpm; // rpm reading
volatile byte pulses; // number of pulses
long int timeold;
// number of pulses per revolution
// based on your encoder disc
long int pulsesperturn = 20;
void counter()
{
   //Update count
   pulses++;
}




SoftwareSerial mySerial(12, 13); // RX, TX
void setup() {
  // configurarea pinilor motor ca iesire, initial valoare `0
  digitalWrite(mpin00, 0);
  digitalWrite(mpin01, 0);
  digitalWrite(mpin10, 0);
  digitalWrite(mpin11, 0);
  pinMode (mpin00, OUTPUT);
  pinMode (mpin01, OUTPUT);
  pinMode (mpin10, OUTPUT);
  pinMode (mpin11, OUTPUT);
  pinMode (A0, OUTPUT);
  // pin LED
  pinMode(13, OUTPUT);


  // set the data rate for the SoftwareSerial port
  mySerial.begin(9600);
  mySerial.println("Hello, world?");
  Serial.begin(9600);
  pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
pinMode(echoPin, INPUT);


   pinMode(encoder_pin, INPUT);
   //Interrupt 0 is digital pin 2
   //Triggers on Falling Edge (change from HIGH to LOW)
   attachInterrupt(0, counter, FALLING);
   // Initialize
   pulses = 0;
   rpm = 0;
   timeold = 0;

  
}
// Functie pentru controlul unui motor
// Intrare: pinii m1 si m2, directia si viteza
void StartMotor (int m1, int m2, int forward, int speed)
{

  if (speed == 0) // oprire
  {
    digitalWrite(m1, 0);
    digitalWrite(m2, 0);
  }
  else
  {
    if (forward)
    {
      digitalWrite(m2, 0);
      analogWrite (m1, speed); // folosire PWM
    }
    else
    {
      digitalWrite(m1, 0);
      analogWrite(m2, speed);
    }
  }
}

// Functie de siguranta
// Executa oprire motoare, urmata de delay
void delayStopped(int ms)
{
  StartMotor (mpin00, mpin01, 0, 0);
  StartMotor (mpin10, mpin11, 0, 0);
  delay(ms);
}
// Utilizare servo
// Pozitionare in trei unghiuri
// La final, ramane in mijloc (90 grade)
void playWithServo(int pin)
{
  srv.attach(pin);
  srv.write(0);
  delay(1000);
  srv.write(180);
  delay(1000);
  srv.write(90);
  delay(1000);
  srv.detach();
}
void loop() {
  // Cod avertizare
  // Blink lent

  /*
  for (int i = 0; i < 10; i++)
  {
    digitalWrite(13, 1);
    delay(200);
    digitalWrite(13, 0);
    delay(200);
  }
  // Blink rapid. Scoateti cablul USB!!!!
  for (int i = 0; i < 10; i++)
  {
    digitalWrite(13, 1);
    delay(100);
    digitalWrite(13, 0);
    delay(100);
    playWithServo(8);
  }
  */
  digitalWrite(13, 1);
  // Pornirea motorului Servo

  /*
  // Acum se pornesc motoarele DC
  StartMotor (mpin00, mpin01, 0, 128);
  StartMotor (mpin10, mpin11, 0, 128);

  delay (500); // Cat timp e motorul pornit
  delayStopped(500); // Cat timp e oprit

  StartMotor (mpin00, mpin01, 1, 128);
  StartMotor (mpin10, mpin11, 1, 128);

  delay (500);
  delayStopped(500);
  StartMotor (mpin00, mpin01, 0, 128);
  StartMotor (mpin10, mpin11, 1, 128);

  delay (500);
  delayStopped(500);

  StartMotor (mpin00, mpin01, 1, 128);
  StartMotor (mpin10, mpin11, 0, 128);

  delay (500);
  delayStopped(500);
  */

  if (mySerial.available()){ // Citire de pe Bluetooth, trimite la PC
 comanda=mySerial.read();
  }

  if('0'<= comanda && comanda <='9')
  {
    vitezaComanda =100+  (10 * (comanda - '0'));
  }
  else
  {
     comandaMiscare = comanda;
    
  }

     if (millis() - timeold >= 1000) {
      //Don't process interrupts during calculations
      detachInterrupt(0);
      rpm = ((long)60 * 1000 / pulsesperturn )/ (millis() - timeold)* pulses;
      timeold = millis();
      pulses = 0;
      Serial.print("RPM = ");
      Serial.println(rpm,DEC);
      //Restart the interrupt processing
      attachInterrupt(0, counter, FALLING);
   }


Serial.print("\n Comanda: ");
  Serial.print(comanda);
  Serial.print("\n vitezaData: ");
  Serial.print(vitezaComanda);
  Serial.print("\n COmanda Miscare: ");
  Serial.print(comandaMiscare);

  Serial.print("RPM = ");
      Serial.println(rpm,DEC);
  Serial.print("\n");

digitalWrite(trigPin, LOW);
delayMicroseconds(2);
// Sets the trigPin on HIGH state for 10 micro seconds
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);
// Reads the echoPin, returns the sound wave travel time in microseconds
duration = pulseIn(echoPin, HIGH);
// Calculating the distance
distance= duration*0.034/2;
// Prints the distance on the Serial Monitor

if(comanda == 'K'){
mySerial.print("Distance: ");
mySerial.println(distance);  
comanda = 'S';



}

if(comanda == 'M'){
tone(A0, 440, 2000);



}


/*
Serial.print("Distance: ");
Serial.println(distance);
Serial.print("\n");
*/
  if(distance < 10 && comandaMiscare!='B')
  {
    delayStopped(100);
   }

  if(comandaMiscare=='S')
  {
    delayStopped(100);
  }

    if(distance >10 && comandaMiscare=='F'){
    StartMotor (mpin00, mpin01, 1, vitezaComanda);
  StartMotor (mpin10, mpin11, 1, vitezaComanda);
  //delay(5000);
    }
  

  if(comandaMiscare=='B')
  {
    StartMotor (mpin00, mpin01, 0, vitezaComanda);
  StartMotor (mpin10, mpin11, 0, vitezaComanda);
  //delay(5000);
  }

  if(comandaMiscare=='L')
  {
    StartMotor (mpin00, mpin01, 1, vitezaComanda);
  StartMotor (mpin10, mpin11, 0, vitezaComanda);
  //delay(5000);
  }
  if(comanda=='R')
  {
    StartMotor (mpin00, mpin01, 0, vitezaComanda);
  StartMotor (mpin10, mpin11, 1, vitezaComanda);
  //delay(5000);
  }
  
  
}
