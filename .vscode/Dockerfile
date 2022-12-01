FROM maven:3.5.0-jdk-8
WORKDIR /apps
COPY . /apps
RUN javac start/guisystem.java

# Don't forget to substitute the following with a real mainClass
CMD java start.guisystem