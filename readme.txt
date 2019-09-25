COMP 557 - Assignment 1: NATHALIE RAFFRAY 260682940

My insect (alien thing) can wink. It is quite flirtatious. I implemented this feature inside of the Geometry class. I made winking to be a degree of freedom, so under the object "eyeball" in the Key Frame Controls, there is a DOF that once shifted all the way to the left will make the eye close, and shifted to the right will make it open.  To make the eye wink, I changed the scale of the eyeball in the y direction, allowing it to bury itself into the black sphere (its eye socket).

I reused the torso of the insect to be one of its wings by scaling it down on the z direction by 0.05. It made for an interesting effect. 

One of the notable things I decided to do was to give the Geometry class a fixed Rotation, that way I could position objects at an angle without them necessarily being joints. This allowed me to position the parts of the tail, for example. I also allowed the possibility to set limits (for euler angles) and default angles for the SphericalJoint objects. This allowed for me to make the shoulders only rotate on the x and y axis. 



