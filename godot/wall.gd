extends Area2D

var size = Vector2.ZERO
@export var color = Color(0.2, 0.66, 0.32, 1.0)


# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.

func set_dimensions(s):
	size = s
	print(str("setting wall dim: ", s, " for name: ", name, " with position: ", position))
	$CollisionShape2D.shape.size = s
#	$CollisionShape2D.
	print(str("walltop: ", name, " shape: ", $CollisionShape2D.shape.get_rect()))

	

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	queue_redraw()


func _on_draw():
	var r = Rect2(0 - (size[0] / 2), 0 - (size[1] / 2), size[0], size[1])
	draw_rect(r, color)
