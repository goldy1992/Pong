extends Node2D

@export var viewportSize = Vector2i(0,0)
@export var paddle_height = 100
@export var paddle_width = 20
@export var waiting_to_serve = true
@export var serve_action = "serve"

# Called when the node enters the scene tree for the first time.
func _ready():
	# get the viewport to place the paddles
	viewportSize = get_viewport().size
	var viewport_width = viewportSize[0]
	var viewport_height = viewportSize[1]
	print(str("viewportSize: ", viewportSize))
	# Set up Paddle Positions
	var paddle_y_pos = viewport_height  / 2
	var paddleMinHeight = 30 + paddle_height
	var paddleMaxHeight = viewport_height - 30 - paddle_height
	$Player1.maxHeight = paddleMaxHeight
	$Player1.minHeight = paddleMinHeight
	$Player2.maxHeight = paddleMaxHeight
	$Player2.minHeight = paddleMinHeight
	$Player1.position = Vector2(40, paddle_y_pos)
	var player_2_x_pos = viewport_width - 40
	$Player2.position = Vector2(player_2_x_pos, paddle_y_pos)
	print(str("player2 pos: ", $Player2.position))
	# Set up the ball starting position
	$Ball.position = Vector2(viewport_width / 2, viewport_height / 2)
	# set up the walls
	print(str("setting wall dimensions in main"))
	$WallTop.position = Vector2(viewport_width / 2, 30)
	var wall_dimensions = Vector2(viewport_width, 100)
	$WallTop.set_dimensions(wall_dimensions)
	$WallBottom.position = Vector2(viewport_width / 2, viewport_height - 30)
	$WallBottom.set_dimensions(wall_dimensions)

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):

	if Input.is_action_pressed(serve_action):
		print("space pressed")
		waiting_to_serve = false

	if not waiting_to_serve:
		if $Ball._is_stationary():
			$Ball.velocity = _random_velocity()
			print("ball velocity:")
			print($Ball.velocity)

func _random_velocity() -> Vector2:
	var rng = RandomNumberGenerator.new()
	var random_speed_x = rng.randf_range(4.1, 4.2)
	var multiplier_x = -1 if rng.randi() % 2 == 0 else 1
	var random_speed_y = rng.randf_range(4.1, 4.2)
	var multiplier_y = -1 if rng.randi() % 2 == 0 else 1
	return Vector2(random_speed_x * multiplier_x,
		random_speed_y * multiplier_y)
			
