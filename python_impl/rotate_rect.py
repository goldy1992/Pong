
from pygame import Rect
from math import cos,sin, sqrt, pow, pi

def rotate_corner(
    center: tuple[int,int], 
    corner:tuple[int,int], 
    angle: float) -> tuple[int, int]: 

    distance_to_corner = sqrt(pow(center[0] - corner[0], 2) + pow(center[1] - corner[1], 2))

    new_x = center[0] + (distance_to_corner * cos(angle))
    new_y = center[1] + (distance_to_corner * sin(angle))
    return [new_x, new_y]



def rotate_rect(rect: Rect, angle: float):
    center = rect.center
    new_tl = rotate_corner(center, rect.topleft, angle)
    new_tr = rotate_corner(center, rect.topleft, angle)
    new_bl = rotate_corner(center, rect.topleft, angle)
    new_br = rotate_corner(center, rect.topleft, angle)

    return Rect(
        topLeft = new_tl, 
        topRight = new_tr,
        bottomleft = new_bl,
        bottonRight = new_br
    )

