#!/usr/bin/env bash
read -p "请输入项目名：" project

# mac
sed -i '' "s/backend/${project}/g" `grep backend -rl ./ --exclude="rename.sh" --exclude-dir=".git" --exclude-dir=".idea"`

# linux
#sed -i "s/backend/${project}/g" `grep backend -rl ./ --exclude="rename.sh" --exclude-dir=".git" --exclude-dir=".idea"`

 mv backend-server.iml ${project}-server.iml

# wms-common
mv backend-common ${project}-common

mv ${project}-common/src/main/java/com/thundersdata/backend ${project}-common/src/main/java/com/thundersdata/${project}

mv ${project}-common/backend-common.iml ${project}-common/${project}-common.iml

# wms-facade
mv backend-facade ${project}-facade

mv ${project}-facade/src/main/java/com/thundersdata/backend ${project}-facade/src/main/java/com/thundersdata/${project}

mv ${project}-facade/backend-facade.iml ${project}-facade/${project}-facade.iml

# wms-gateway
mv backend-gateway ${project}-gateway

mv ${project}-gateway/src/main/java/com/thundersdata/backend ${project}-gateway/src/main/java/com/thundersdata/${project}

mv ${project}-gateway/backend-gateway.iml ${project}-gateway/${project}-gateway.iml

# wms-service
mv backend-service ${project}-service

mv ${project}-service/src/main/java/com/thundersdata/backend ${project}-service/src/main/java/com/thundersdata/${project}

mv ${project}-service/src/test/java/com/thundersdata/backend ${project}-service/src/test/java/com/thundersdata/${project}

mv ${project}-service/backend-service.iml ${project}-service/${project}-service.iml

echo "Congratulations ! rename project success."
#find ./ -name '*back' | xargs rm -rf
