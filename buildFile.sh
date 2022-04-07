## Step 1: Run DB & Migrations
echo "!!-------------------------------!!"
echo "!! Step 1/3 - DB & Migrations    !!"
echo "!!-------------------------------!!"

docker-compose up -d skeleton_db

## We sleep a couple of seconds to give the container time to start up
sleep 5

printf "\n\n"

## Step 2: Setup application environment
echo "!!-------------------------------!!"
echo "!! Build Step 2/3 - Build App    !!"
echo "!! This may take a while         !!"
echo "!!-------------------------------!!"
docker-compose up -d --build

printf "\n\n"

# Step 3: Setup application environment
echo "!!-------------------------------!!"
echo "!! Build Step 3/3 - Tailing logs !!"
echo "!!-------------------------------!!"
docker logs skeleton_service -f -t