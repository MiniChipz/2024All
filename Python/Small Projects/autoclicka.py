from pynput.mouse import Listener
import time

# List to store captured coordinates
click_points = []

# Function to capture the mouse click position
def on_click(x, y, button, pressed):
    if pressed:  # Capture only when the mouse is pressed
        print(f"Mouse clicked at: ({x}, {y})")
        click_points.append((x, y))

        # Optional: Stop listening after capturing 5 clicks (you can remove this part)
        if len(click_points) >= 2:
            print("Captured enough points. Exiting...")
            return False

# Start the mouse listener
with Listener(on_click=on_click) as listener:
    listener.join()

# Print captured points after stopping the listener
print("\nCaptured points:", click_points)

# Implement autoclicker function (optional)
def start_autoclicker():
    from pynput.mouse import Controller

    mouse_controller = Controller()

    for point in click_points:
        mouse_controller.position = point
        mouse_controller.click(mouse_controller.Button.left)
        time.sleep(1)  # Wait for 1 second between clicks

# Start the autoclicker after a delay
time.sleep(2)  # Wait 2 seconds before starting
start_autoclicker()
