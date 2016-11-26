#include <Servo.h>
#include <EEPROM.h>

Servo myservo;
Servo myservoChip;// create servo object to control a servo
// twelve servo objects can be created on most boards

int pos = 6;    // variable to store the servo position
int address = 0;

void setup() {
  myservo.attach(9);
  myservoChip.attach(6);// attaches the servo on pin 9 to the servo object
  Serial.begin(9600);
  pos = EEPROM.read(address);
  myservoChip.write(90);
}

void moveRight(){
  if(pos < 6){
    myservo.write(70);              // tell servo to go to position in variable 'pos'
    delay(600);  
    myservo.write(90);              // tell servo to go to position in variable 'pos'
    delay(1000);
    pos ++; 
    EEPROM.write(address, pos);
  }
}

void moveLeft(){
  if(pos > 0){
    myservo.write(110);              // tell servo to go to position in variable 'pos'
    delay(750);  
    myservo.write(90);              // tell servo to go to position in variable 'pos'
    delay(1000); 
    pos --;
    EEPROM.write(address, pos);
  }
}

void moveTo(int position){
  while(pos > position){
    moveLeft();
    Serial.println(pos, DEC);
    Serial.println(position, DEC);
  }

  while(pos < position){
    moveRight();
    Serial.println(pos, DEC);
    Serial.println(position, DEC);
  }
}

void dropChip(){
  int speed = 3;
  int i;
  for( i = 90; i >= 20; i-=speed){
    myservoChip.write(i);
    delay(100); 
  }
  myservoChip.write(20);
  for( i = 20; i <= 90; i+=speed){
    myservoChip.write(i);
    delay(100); 
  }
  myservoChip.write(90);
}

void loop() {
  if (Serial.available() > 0) {
      // read the incoming byte:
      int incomingByte = Serial.read();
      int number = incomingByte - '0';
      if(incomingByte == 99){
        pos = 6;
        EEPROM.write(address, pos);
      }
      if(number >= 0 && number < 7){
        moveTo(number);
        dropChip();
      }
  }
}
