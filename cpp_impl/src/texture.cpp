#include <stdio.h>
#include <string>
#include <SDL.h>
#include <SDL_image.h>
#include <texture.h>
#include <constants.h>

Texture::Texture()
{
	printf("creating texture\n");
	//Initialize
	mTexture = NULL;
	mWidth = 0;
	mHeight = 0;
}

Texture::~Texture()
{
	printf("destroying texture\n");
	//Deallocate
	free();
}

bool Texture::loadFromFile( std::string path, SDL_Renderer* renderer )
{
	printf("loading from file\n");
	//Get rid of preexisting texture
	free();

	//The final texture
	SDL_Texture* newTexture = NULL;

	//Load image at specified path
	SDL_Surface* loadedSurface = IMG_Load( path.c_str() );
	if( loadedSurface == NULL )
	{
		printf( "Unable to load image %s! SDL_image Error: %s\n", path.c_str(), IMG_GetError() );
	}
	else
	{
		//Color key image
		SDL_SetColorKey( loadedSurface, SDL_TRUE, SDL_MapRGB( loadedSurface->format, 0, 0xFF, 0xFF ) );

		//Create texture from surface pixels
        newTexture = SDL_CreateTextureFromSurface( renderer, loadedSurface );
		if( newTexture == NULL )
		{
			printf( "Unable to create texture from %s! SDL Error: %s\n", path.c_str(), SDL_GetError() );
		}
		else
		{
			//Get image dimensions
			mWidth = loadedSurface->w;
			mHeight = loadedSurface->h;
            printf("width %d, height %d\n", mWidth, mHeight);
		}

		//Get rid of old loaded surface
		SDL_FreeSurface( loadedSurface );
	}

	//Return success
	mTexture = newTexture;
	bool loadedFromFile = mTexture != NULL;
	printf("loaded from file: %d\n", loadedFromFile);
	return loadedFromFile;
}

void Texture::free()
{
	//Free texture if it exists
	if( mTexture != NULL )
	{
		SDL_DestroyTexture( mTexture );
		mTexture = NULL;
		mWidth = 0;
		mHeight = 0;
	}
}

void Texture::render(SDL_Renderer* renderer, int x, int y, SDL_Rect* clip, Direction d)
{
	//Set rendering space and render to screen
	SDL_Rect renderQuad = { x, y, mWidth, mHeight };


	//Set clip rendering dimensions
	if( clip != NULL )
	{
			//printf("clip not null x, %d, y %d, width %d, height %d\n", x, y, clip->w, clip->h);

		renderQuad.w = clip->w;
		renderQuad.h = clip->h;
	}


	//Render to screen
	//SDL_RenderCopy( renderer, mTexture, clip, &renderQuad );
	SDL_RendererFlip flip = SDL_FLIP_NONE;
	if (d == DIR_LEFT) {
		flip = SDL_FLIP_HORIZONTAL;
	}
	SDL_RenderCopyEx(renderer, mTexture, clip, &renderQuad, 0, NULL, flip);
}

int Texture::getWidth()
{
	return mWidth;
}

int Texture::getHeight()
{
	return mHeight;
}
