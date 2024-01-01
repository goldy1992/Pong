extends Area2D

@export var radius = 50
@export var center = Vector2(300, 300)
@export var color = Color(0.3, 0.65,0.95, 1.0)
@export var velocity = Vector2.ZERO

# Called when the node enters the scene tree for the first time.
func _ready():
	$CollisionShape2D.shape.radius = radius
	pass # Replace with function body.




# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	position = Vector2(position.x + velocity[0], position.y + velocity[1])
	var vp = get_viewport().size
	if (position.x > (vp[0] + radius)) or (position.x < (0 - radius)):
		print("ball is out of bounds")
		get_parent().waiting_to_serve = true
		position = Vector2(vp[0] / 2, vp[1] / 2)
		velocity = Vector2.ZERO
	queue_redraw()


		

func _on_draw():
#	print(str("ball center: " ,center, ", velocity", velocity ))
	draw_circle(Vector2.ZERO, radius, color)


func _is_stationary() -> bool:
	return velocity == Vector2.ZERO


func _on_area_entered(area):
	if area.name == "Player1"or area.name == "Player2":
		velocity[0] *= -1.05
	elif area.name == "WallTop" or area.name == "WallBottom":
		print(str("hit area: ", area.name))
		velocity[1] *= -1
	pass # Replace with function body.
