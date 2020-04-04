FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 379249906@qq.com

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /alibaba-gateway

WORKDIR /alibaba-gateway

EXPOSE 9999

ADD ./target/alibaba-gateway.jar ./

CMD sleep 60;java -jar alibaba-gateway.jar
