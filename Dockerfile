FROM maven:3.8.4-openjdk-11 as MVN_BUILD

WORKDIR /opt/solo/
ADD . /tmp
RUN cd /tmp && mvn package -DskipTests -Pdev  && mv target/solo/* /opt/solo/ \
&& cp -f /tmp/src/main/resources/docker/* /opt/solo/

FROM openjdk:18-alpine
LABEL maintainer="fangguangming@aliyun.com"

WORKDIR /opt/solo/
COPY --from=MVN_BUILD /opt/solo/ /opt/solo/
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories  \
    && apk add --no-cache ca-certificates tzdata

ENV TZ=Asia/Shanghai
ARG git_commit=0
ENV git_commit=$git_commit

EXPOSE 8080

ENTRYPOINT [ "java", "-cp", "lib/*:.", "org.b3log.solo.Server" ]
