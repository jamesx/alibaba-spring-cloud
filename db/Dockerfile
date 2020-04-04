FROM mysql:8.0.19

MAINTAINER august(379249906@qq.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./alibaba-cloud.sql /docker-entrypoint-initdb.d
