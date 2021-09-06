# This Dockerfile is used to build an headles vnc image based on Ubuntu
FROM consol/ubuntu-xfce-vnc

USER root

# Setting env
ENV VNC_RESOLUTION=1920x1080 \
    VNC_PW=password

# Create app directory
WORKDIR /usr/src/app

COPY . ./
# install program dependencies
RUN apt-get update \
    && apt-get install -yq libgconf-2-4 sudo curl wget xvfb dos2unix 

# Install Java: jdk-8
RUN sudo apt-get install openjdk-8-jdk -y \
    && apt-get autoremove -y \
    && apt-get autoclean -y \
    && java -version

RUN find . -type f -name "*.sh" -exec dos2unix {} \+;

RUN useradd -m docker && echo "docker:docker" | chpasswd && adduser docker sudo \
    && useradd -m jenkins && echo "jenkins:jenkins" | chpasswd && adduser jenkins sudo

RUN  whoami
