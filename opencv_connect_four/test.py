import cv2
import numpy as np;
import pprint;



def nothing(x):
    pass

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




def getEmptyCircles(img_hsv):
    r = cv2.getTrackbarPos('R','empty')
    g = cv2.getTrackbarPos('G','empty')
    b = cv2.getTrackbarPos('B','empty')
    r_1 = cv2.getTrackbarPos('R_1','empty')
    g_1 = cv2.getTrackbarPos('G_1','empty')
    b_1 = cv2.getTrackbarPos('B_1','empty')
    print("MIN")
    print(r)
    print(g)
    print(b)
    print("MAX")
    print(r_1)
    print(g_1)
    print(b_1)
    lower_red = np.array([r,g,b])
    upper_red = np.array([r_1,g_1,b_1])
    mask = cv2.inRange(img_hsv, lower_red, upper_red)

    # set my output img to zero everywhere except my mask
    output_img = newimage.copy()
    output_img[np.where(mask==0)] = 255
    return output_img

def getKeypoints(img):
    kernel = np.ones((5,5),np.uint8)
    tempImg = cv2.morphologyEx(img, cv2.MORPH_CLOSE, kernel)
    params = cv2.SimpleBlobDetector_Params()
    detector = cv2.SimpleBlobDetector_create(params)
    return detector.detect(tempImg)



cv2.namedWindow('empty')

cv2.createTrackbar('R','empty',0,255,nothing)
cv2.createTrackbar('G','empty',0,255,nothing)
cv2.createTrackbar('B','empty',0,255,nothing)
cv2.createTrackbar('R_1','empty',0,255,nothing)
cv2.createTrackbar('G_1','empty',0,255,nothing)
cv2.createTrackbar('B_1','empty',0,255,nothing)
conectFourMatrix = np.zeros((6,7))
conectFourPoints = []

cap = cv2.VideoCapture(0)

while True:
    ret, img = cap.read()
    newx,newy = img.shape[1]/4,img.shape[0]/4 #new size (w,h)
    newimage = cv2.resize(img,(newx,newy))

    img_hsv = cv2.cvtColor(newimage, cv2.COLOR_BGR2HSV)

    emptyCircles = getEmptyCircles(img_hsv)

    cv2.imshow("empty", emptyCircles)
    #cv2.imshow("img", img)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
