## The Lost Cities Squared Dev-5:
* Fixed an ArrayIndexOutOfBoundsException when generating chunks (sandtechnology)
* Add a config to generate with MWC's loot. (Doesn't do anything right now)
* Fixed bridges generating air underwater (Windmill-City)
* Improved how cities flatten terrain (Windmill-City)
* Added loottables that generate different items based on what building the chest is in. (Office, Food)
* Added a check for REID
* Cobblestone walls have a chance to be mossy   

## The Lost Cities Squared Dev-4:
* Added a bunch of closed PRs from the original Lost Cities. The original author's username is credited next to the change.
* Fixed train station is one block elevated
* Fixed bridges being one block elevated
* Fixed explosions leaving airblocks underwater (Windmill-City)
* Fixed Min + Max Cellars (Windmill-City)
* Fixed grass blocks generating underwater (Windmill-City)
* Fixed waterlevel being wrong (Windmill-City) 
* Fixed biomes being one chunk offset (Pseudonomenclature)
* Increased the spawn chance of the townhall for testing
* Streets now use Dark Grey Concrete as the road base
* Added more loot variety to chests
* Added a check for if ATG is loaded
* Started work on the MWC pallete
* Lang credits:
  * Blueberryy (ru_RU.lang)
  * MCUmbrella (zh_CN.lang)
  * Lykrast (fr_FR.lang)

## The Lost Cities Squared Dev-3:
* Removed the water that can be generated inside a building
* Double stone slabs can sometimes generate as stone slabs for a more destroyed look
* Closed the roof on one of the wells
* Changed "street variant" to use stone bricks instead of bricks for the time being
* Streets now generate with more stone slabs (More like a city)
* Removed some stone slabs from park_pool
* Internal: 
  * New MODID
  * Renamed park_pool to park_pond 

## The Lost Cities Squared Dev-2:
* New "experimental" profile for my testing. May work, may not.  
* Library.json changes:
  * Brewing stands will generate less frequently in cities.
  * Enchanting tables will generate less frequent cities.
  * Slight chance to generate air in place of crafting tables or furnaces.
* Un-privated the "bio_wasteland" and "water_empty" profiles. They are used internally for waterbubbles and biospheres respectively.
* Removed the ugly Blue and Light Blue glass colors from generating in cities.
* Changed the Mod name and Verison. 

## The Lost Cities Squared Dev-1:
* New "nospawner" profile
