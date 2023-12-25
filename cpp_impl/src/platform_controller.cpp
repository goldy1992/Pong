#include <iostream>
#include <list>
#include <platform.h>
#include <SDL.h>

const Uint64 SPAWN_TIME_MS = 1000;
const Uint64 NOT_SPAWNED = -1;

class PlatformController {

    private:
        int tagNumber = 0;
        std::list<Platform*> platforms;
        Uint64 lastUpdateTime = SDL_GetTicks64();
        Uint64 lastSpawnTime = SDL_GetTicks64();

        bool shouldSpawn(const Uint64 &currentTime) {
            int timeDiff = ((int) currentTime) -  ((int)lastSpawnTime);
            return timeDiff > SPAWN_TIME_MS;
        }

    public:
        void update() {
            int currentTime = (int)SDL_GetTicks64();
            if (shouldSpawn(currentTime)) {
//                std::cout << "creating " << std::to_string(tagNumber) << std::endl;
                std::string platformTag = "platform_" + std::to_string(tagNumber);
                Platform* newPlatform = new Platform(platformTag, currentTime);
                tagNumber++;
  //              std::cout << "adding to list: " << platformTag << std::endl;
                platforms.push_back(newPlatform);
               // std::cout << "platforms size: " << platforms.size() << std::endl;
                lastSpawnTime = currentTime;
            }
        //   std::cout << "start update" << std::endl;
            for (Platform* p : platforms) {      
                (*p).update();
            }
         //  std::cout << "end update" << std::endl;
            std::list<Platform*>::iterator i = platforms.begin();
         //   std::cout << "while remove" << std::endl;
            while (i != platforms.end())
            {
                bool shouldDespawn = (*i) -> shouldDespawn();
                if (shouldDespawn)
                {
                    std::cout << "removing from list" << std::endl;
                    platforms.erase(i++);  // alternatively, i = items.erase(i);
                }
                else
                {
                    i++;
                }
           //     std::cout << "end while iteration" << std::endl;
            }
          //  std::cout << "end while remove" << std::endl;
        }

        void render(SDL_Renderer* renderer) {
            for (Platform* p : platforms) {      
                (*p).render(renderer);
            }
        }

        std::list<Platform*>* getPlatforms() {
            return &platforms;
        }
};