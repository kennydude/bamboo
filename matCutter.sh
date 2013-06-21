#!/bin/sh
echo "Cutting mat"
convert res/textures/blocks/mat.png -crop 16x16+2+2 res/textures/blocks/matA.png

convert res/textures/blocks/mat.png -crop 16x16+0+0 res/textures/blocks/matLT.png
convert res/textures/blocks/mat.png -crop 16x16+0+2 res/textures/blocks/matL.png
convert res/textures/blocks/mat.png -crop 16x16+2+0 res/textures/blocks/matT.png
convert res/textures/blocks/mat.png -crop 16x16+4+0 res/textures/blocks/matRT.png
convert res/textures/blocks/mat.png -crop 16x16+4+2 res/textures/blocks/matR.png

convert res/textures/blocks/mat.png -crop 16x16+0+4 res/textures/blocks/matLB.png
convert res/textures/blocks/mat.png -crop 16x16+2+4 res/textures/blocks/matL.png
convert res/textures/blocks/mat.png -crop 16x16+4+4 res/textures/blocks/matRB.png

convert res/textures/blocks/mat.png -crop 16x16+2+4 res/textures/blocks/matB.png

echo "Done cutting"