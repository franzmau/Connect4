import cv2
import numpy as np;
import pprint;
import time
import mnist_loader
training_data, validation_data, test_data = mnist_loader.load_data_wrapper()
import network
net = network.Neuron_Network([784, 30, 10])
net.Stochastic_Gradient_Descent(training_data, 1, 10, 3.0, test_data=test_data)


cap = cv2.VideoCapture(0)

while True:
    x0 = 0
    x1 = 0
    y0 = 0
    y1 = 0
    ret, img = cap.read()
    #img = cv2.fastNlMeansDenoisingColored(img,None,10,10,7,21)

    img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    gray_img = y = img.copy()
    th3 = cv2.adaptiveThreshold(img,255,cv2.ADAPTIVE_THRESH_GAUSSIAN_C,cv2.THRESH_BINARY,11,2)

    _, contours, _ = cv2.findContours(th3,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)
    imageDetected = False;
    for i in range(0, len(contours)):
        if (i % 2 == 0):
           cnt = contours[i]
           #mask = np.zeros(im2.shape,np.uint8)
           #cv2.drawContours(mask,[cnt],0,255,-1)
           x,y,w,h = cv2.boundingRect(cnt)
           if w > 80 and w < 200 and h > 140 and h < 210:
               offset = 20
               x0 = x - offset
               x1 = x + w + offset
               y0 = y - offset
               y1 = y + h + offset
               imageDetected = True
               cv2.rectangle(img,(x,y),(x+w,y+h),(0,255,0),2)
               cv2.imshow('Features', img)

    if imageDetected:
        output = gray_img[y0:y1,x0:x1]
        resized_image = cv2.resize(output, (28, 28))
        cv2.imshow('output', output)
        cv2.imshow('NUMBER', resized_image)
        output = np.reshape(resized_image, (784, 1))
        output = output / 255;
        number = net.predict_digit(output);
        print('Number = ' + str(number));
        time.sleep(5);
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
