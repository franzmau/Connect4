import serial
import time

class SerialManager:

    def sendNumber(self, number):
        ser = serial.Serial("/dev/tty.usbmodem1411", 9600)

        ## loop until the arduino tells us it is ready
        for i in range(0, 3):
            ser.write(str(number))
            time.sleep(1);
        ser.close()
        time.sleep(10);
