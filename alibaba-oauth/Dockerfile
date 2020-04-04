FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 379249906@qq.com

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /alibaba-oauth

WORKDIR /alibaba-oauth

EXPOSE 8081

ADD ./target/alibaba-oauth.jar ./

CMD sleep 60;java -jar alibaba-oauth.jar
