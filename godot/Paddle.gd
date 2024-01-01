extends Area2D

@export var x = 100.0
@export var y = 100.0

var width = 20
var height = 100

@export var maxHeight = 0
@export var minHeight = 0

@export var move_up_action = "move_up"
@export var move_down_action = "move_down"
#@export var color = Color(0.3, 0.15,0.95, 1.0)
@export var color = Color(1.0, 1.0, 1.0, 1.0)

# Called when the node enters the scene tree for the first time.
func _ready():
	$CollisionShape2D.shape.size = Vector2(width, height)
#	$CollisionShape2D.shape.color = Color(1, 1,1, 0.5)
	print(str("setting paddle ", name, " collision: ", $CollisionShape2D.shape.get_rect(), " with position: ", position))
	pass # Replace with function body.

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	if Input.is_action_pressed(move_up_action):
		var newY = clamp(position.y - 10, minHeight, maxHeight)
		position = Vector2(position.x, newY)
	
	
	if Input.is_action_pressed(move_down_action):
		var newY = clamp(position.y + 10, minHeight, maxHeight)
		position = Vector2(position.x, newY)


	#if name == "Player2":
	#	print(str(name, ": ", $CollisionShape2D.shape.get_rect(), ", position: ", position))
	queue_redraw()


func _on_draw():
	var r = Rect2(0 - (width / 2), 0 - (height / 2), width, height)
	draw_rect(r, color)
	draw_circle(Vector2.ZERO, 5, Color.BROWN)

	

func _is_wall(area) -> bool:
	return "wall.gd" in area

func _on_area_entered(area):
	print(str(name, " hit ", area.name))