# Java Rasterizer

Simple 2D Java rasterizer with minimal dependencies. Written to learn more about Java.

There are no external dependencies except AWT to output the framebuffer.

Everything else, including the matrix math library is written by me and contained in the project.

Contains a simple matrix algebra library to perform calculations, and outputs the rasterized pixels to a framebuffer with AWT.

## Features:
 - Fully contained matrix/CG math library with easy transformation helper functions
 - Barycentric coordinate calculations
 - Can render any amount of 2D triangles
 - Simple API

# Showcase Images
![Barycentric coordinate preview](preview1.png)
Rendering of a rectangle with interpolated vertex colors using barycentric coordinates

![Preview Image Of Triangle](preview.png)
Basic rendering of a triangle