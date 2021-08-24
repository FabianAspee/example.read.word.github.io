#Stage 1: use nginx official image to deploy the app
FROM nginx:latest AS base
WORKDIR /app
EXPOSE 80
# Stage 2: Compile and Build angular codebase

# Use official node image as the base image
FROM node:16-alpine3.11 AS build
# Set the working directory
WORKDIR /src
# Add the source code to app
COPY frontend/angular/front-end-read-word/package.json /app 
COPY frontend/angular/front-end-read-word/ .
WORKDIR /src/app

RUN npm install -g @angular/cli
# Install all the dependencies
RUN npm install


FROM build AS publish
# Generate the build of the application 
WORKDIR /src/app
RUN ng build


FROM base AS final
WORKDIR /app 
# Copy the build output to replace the default nginx contents.
COPY --from=publish /src/dist/front-end-read-word /usr/share/nginx/html
