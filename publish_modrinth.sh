#!/bin/sh
set -e
MODID="8nIicBYu"
mod_name="JRFTL"

if [ -z $MODRINTH_TOKEN ]
then
    echo "Environment Variable MODRINTH_TOKEN must contain the Github Authentication token"
    exit 1
fi

version_name=$(grep -oP '(*plb:^mod_version=).*' gradle.properties)
mc_version_name=$(grep -oP '(*plb:^mc_version=).*' gradle.properties)

echo "Version $version_name"

changelog=$(cat changelogs/"$version_name") || echo "Found no Changelog"
dependencies='[]'
versions=$(grep -oP '(*plb:compatible_mc_versions=").*(*pla:")' gradle.properties)
release_type="release"
loaders='["forge"]'
is_featured="true"

echo "Building json"

modrinth_req=$(jq -n \
--arg vers "$version_name" \
--arg changelog "$changelog" \
--argjson deps "$dependencies" \
--argjson versions $versions \
--arg verstype "$release_type" \
--argjson loaders "$loaders" \
--argjson featured $is_featured \
--arg project_id "$MODID" \
--arg file_parts "file" \
'{name: $vers, version_number:$vers, changelog:$changelog, dependencies:$deps, 
game_versions: $versions, version_type:$verstype, loaders:$loaders, 
featured:$featured, project_id:$project_id, file_parts:[$file_parts]}')

echo "Sending request"

mod_file="build/libs/$mod_name-$mc_version_name-$version_name.jar"

curl -H "UserAgent:456Xander/$mod_name/$version_name (alexander.daum@mailbox.org)" -H "Authorization:$MODRINTH_TOKEN" -F data="$modrinth_req" -F file=@$mod_file https://api.modrinth.com/v2/version
