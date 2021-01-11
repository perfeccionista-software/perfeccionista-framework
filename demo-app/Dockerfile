FROM node:latest as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY ./ .
RUN npm run build

FROM nginx as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html/dist
COPY ./src/static /usr/share/nginx/html/src/static
COPY ./index.html /usr/share/nginx/html
COPY ./conf.nginx /etc/nginx/nginx.conf
