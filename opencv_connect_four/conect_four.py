import cv2
import numpy as np;
import pprint;
import time

def isEqual(empty, actual):
    x = 0;
    y = 1;
    offset = 0.5;
    if (actual[x]  >= (empty[x] - offset) and actual[x]  <= (empty[x] + offset)):
        if (actual[y]  >= (empty[y] - offset) and actual[y]  <= (empty[y] + offset)):
            return True
    return False

def sortCondition(a , b):
    if a[0] < b[0]:
        if a[1] < b[1]:
            return -1;
    return 1;


def sortArray(toSort):
    for i in range(len(toSort)):
        j = i
        while (j > 0 and sortCondition(toSort[j], toSort[j-1]) <= 0):
            temp = toSort[j];
            toSort[j] = toSort[j-1];
            toSort[j-1] = temp
            j = j - 1

def nothing(x):
    pass

def printMatrix(matrix):
    for i in range(len(matrix)):
        output = ""
        for j in range(len(matrix[i])):
            output = output + str(matrix[i][j]) + ", ";
        print(output)
    print();

def getBlackCircles(img_hsv):
    lower_red = np.array([0,5,0])
    upper_red = np.array([255,171,36])
    mask = cv2.inRange(img_hsv, lower_red, upper_red)

    # set my output img to zero everywhere except my mask
    output_img = newimage.copy()
    output_img[np.where(mask==0)] = 255
    return output_img

def getRedCircles(img_hsv):
    lower_red = np.array([0,181,0])
    upper_red = np.array([17,242,196])
    mask = cv2.inRange(img_hsv, lower_red, upper_red)

    # set my output img to zero everywhere except my mask
    output_img = newimage.copy()
    output_img[np.where(mask==0)] = 255
    return output_img

def getEmptyCircles(img_hsv):
    lower_red = np.array([20,145,0])
    upper_red = np.array([60,255,255])
    mask = cv2.inRange(img_hsv, lower_red, upper_red)

    # set my output img to zero everywhere except my mask
    output_img = newimage.copy()
    output_img[np.where(mask==0)] = 0
    output_img[np.where(mask!=0)] = 255
    return output_img

def getKeypoints(img):
    kernel = np.ones((5,5),np.uint8)
    tempImg = img
    params = cv2.SimpleBlobDetector_Params()
    detector = cv2.SimpleBlobDetector_create(params)
    return detector.detect(tempImg)



cap = cv2.VideoCapture(0)

while True:
    conectFourMatrix = np.zeros((6,7))
    conectFourPoints = []
    ret, img = cap.read()
    newx,newy = img.shape[1]/4,img.shape[0]/4 #new size (w,h)
    newimage = cv2.resize(img,(newx,newy))

    img_hsv = cv2.cvtColor(newimage, cv2.COLOR_BGR2HSV)


    # Show keypoints
    redCircles = getRedCircles(img_hsv)
    blackCircles = getBlackCircles(img_hsv)
    emptyCircles = getEmptyCircles(img_hsv)

    emptyKeypoints = getKeypoints(emptyCircles);
    blackKeypoints = getKeypoints(blackCircles);
    redKeypoints = getKeypoints(redCircles);

    sortArray(conectFourPoints);

    for i in range(len(emptyKeypoints)):
        conectFourPoints.append(emptyKeypoints[i].pt);


    for i in range(len(blackKeypoints)):
        for j in range(len(emptyKeypoints)):
            if isEqual(emptyKeypoints[j].pt, blackKeypoints[i].pt):
                conectFourMatrix[j/7][j%7]=2;
                break;

    for i in range(len(redKeypoints)):
        for j in range(len(emptyKeypoints)):
            if isEqual(emptyKeypoints[j].pt, redKeypoints[i].pt):
                conectFourMatrix[j/7][j%7]=1;
                break;

    conectFourMatrix = np.flipud(conectFourMatrix)
    conectFourMatrix = np.fliplr(conectFourMatrix)

    printMatrix(conectFourMatrix);

    cv2.imshow("image_2", newimage)
    cv2.imshow("empty", emptyCircles)
    cv2.imshow("black", blackCircles)
    cv2.imshow("red", redCircles)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
