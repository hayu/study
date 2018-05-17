# Initial: Wed May  6 18:47:13 PDT 2015
# Animates a cube based on data pushed from a web socket. It also demonstrate CORS in web socket.
#

Demo videos: 
	a) demo/webSocket-cube-animate.mov
	b) demo/single-hostler.mov

This is a prototype of WebSocket push-driven animation. It is based on the following two resources

1. https://github.build.ge.com/212365823/sb-websocket-hello
2. https://www.packtpub.com/web-development/threejs-essentials, July, 2014, chapter 3, 03.02-animate-cube.html

By default, it assumes that Eclipse IDE is used and supplies associated project setting files to facilitate import as an existing project.

The animation is on a 4x4x4 cube, moving randomly at a distance between 2 to 8 units, and in X (right/left) or Z (forward/back) directions.

To exercise the prototype, do

1. mvn clean package && java -jar target/sb-websocket-animate-0.1.0.jar

   or alternateively, with gradle

   ./gradlew clean build && java -jar build/libs/sb-websocket-animate-0.1.0.jar

2. Use a browser to access http://localhost:8080
3. Manually exercise "right", "left", "forward", "back" controls to animate the cube.
4. Click "connect" to subscribe to server-side websocket and animate the cube based on pushed data.
5. Click "disconnect" to stop the animation.
6. As a PoC, no effort was put into confining the cube in a visible area. You can reload the page to reset the cube at the origin.
7. Another similar, but slightly more realistic PoC is done with an intermodal container yard as the background. It simulates a couple of hostlers moving around in the yard based on position data pushed from the server. Access it at http://localhost:8080/yard.html and click on the "connect" button.
8. To demonstrate CORS, start the Spring Boot regularly (on port 8080), use another web server to serve the yard.html page on a different port. The web socket will still work due to the promiscuous allowed origin header in WebSocketConfig.java

Detail on the prototype:

1. Tech stacks: 
   a) Server-side: Spring Boot + WebSocket + Stomp + in-memory simple message broker + scheduler
   b) Client-side: Three.js + Tween + WebGL + SockJS + Stomp
2. A scheduled task pushes out movement data like {direction: "right", distance: 4}
3. Control "connect" subscribes to the movement data and animate the cube accordingly
4. Client animatation is at 500 msec per movement. Server pushes movement data at 600 msec interval.
5. Client-Server API's:
   a) "/hello" -- REST end point for negotiating the WebSocket protocol upgrade
   b) "/topic/simplemove" -- Stomp end point for server push and client subscription on cube animation
   c) "/topic/yardmove" -- Stomp end point for server push and client subscription on intermodal yard movement
   d) "/app/publish" -- Server-side end point for client incoming message
6. To make the world axes visible at the origin, uncomment this line:

       //scene.add(buildAxes(100));

