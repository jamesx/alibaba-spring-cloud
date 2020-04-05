FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 379249906@qq.com

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /alibaba-register

WORKDIR /alibaba-register

EXPOSE 8848

ADD ./target/alibaba-register.jar ./

CMD sleep 30;java -jar alibaba-register.jar


