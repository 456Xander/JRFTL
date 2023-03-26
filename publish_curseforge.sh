#!/bin/sh
MODID="247136"
mod_name="JRFTL"

if [ -z $CURSEFORGE_TOKEN ]
then
    echo "Environment Variable CURSEFORGE_TOKEN must contain the Github Authentication token"
    exit 1
fi

version_name=$(grep -oP '(*plb:^mod_version=).*$' gradle.properties)
mc_version_name=$(grep -oP '(*plb:^mc_version=).*$' gradle.properties)

echo Version $mod_version
changelog=$(cat changelogs/"$version_name")
dependencies='[]'
versions=$(grep -oP '(*plb:compatible_mc_versions=").*(*pla:")' gradle.properties)
release_type="release"

# For Curseforge Minecraft versions have to be translated to numerical versions...
# Sadly it is pretty complicated to do it
version_types=$(curl "https://minecraft.curseforge.com/api/game/version-types?token=$CURSEFORGE_TOKEN")
cf_api_versions=$(curl "https://minecraft.curseforge.com/api/game/versions?token=$CURSEFORGE_TOKEN")

major_mc_version=$(echo $mc_version_name | grep -oP '\d+\.\d+')
echo "MC Version $major_mc_version"
mc_version_type=$(echo $version_types | jq ".[] | select(.name == \"Minecraft $major_mc_version\").id")
echo "MC Version Type ID: $mc_version_type"
java_version_type=$(echo $version_types | jq ".[] | select(.name == \"Java\").id")
echo "Java Type ID: $mc_version_type"

transformed_versions=""
for x in $(echo $versions | jq '.[]'); do
    tv=$(echo $cf_api_versions | \
    jq ".[] | select(.name == $x) | select(.gameVersionTypeID == $mc_version_type).id")
    transformed_versions="$transformed_versions,$tv"
done
for x in "Java 18" "Java 17"; do
    tv=$(echo $cf_api_versions | \
    jq ".[] | select(.name == \"$x\") | select(.gameVersionTypeID == $java_version_type).id")
    transformed_versions="$transformed_versions,$tv"
done

tv=$(echo $cf_api_versions | \
    jq ".[] | select(.name == \"Forge\").id")
transformed_versions="$transformed_versions,$tv"

transformed_versions="[${transformed_versions:1}]"

echo "Building json"

cf_req=$(jq -n \
--arg changelog "$changelog" \
--argjson deps "$dependencies" \
--argjson vers $transformed_versions \
--arg verstype "$release_type" \
'{changelog:$changelog, changelogType:"text",
gameVersions: $vers, releaseType:$verstype}')

echo "Sending request"

mod_file="build/libs/$mod_name-$mc_version_name-$version_name.jar"

echo $cf_req

curl -H "X-API-Token:$CURSEFORGE_TOKEN" -F metadata="$cf_req" -F file=@$mod_file \
    https://minecraft.curseforge.com/api/projects/$MODID/upload-file
