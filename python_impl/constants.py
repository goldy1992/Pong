from typing import Tuple, Union


transparent = (0,0,0,0)
WINDOW_WIDTH = 800
WINDOW_HEIGHT = 600

RGB = Tuple[int, int, int]
RGBA = Tuple[int, int, int, int]
Position = Tuple[float, float]
Color = Union[RGB, RGBA]