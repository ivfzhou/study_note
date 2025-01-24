FROM golang:1.23.2
FROM ubuntu:20.04
COPY --from=0 /usr/local/go/lib/time/zoneinfo.zip /opt/zoneinfo.zip
ENV ZONEINFO /opt/zoneinfo.zip
ENV ZONEINFO /opt/zoneinfo.zip
ENV TZ Asia/Shanghai
RUN ln -fs /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
RUN apt-get update && apt-get install -y tzdata
RUN dpkg-reconfigure -f noninteractive tzdata
COPY --from=0 /etc/ssl/certs/ca-certificates.crt /etc/ssl/certs/
ENV WORKDIR /home/ubuntu/app

