FROM node:13 as build
COPY . .
RUN npm install

FROM nginx as production
COPY --from=build index.html /usr/share/nginx/html/
COPY --from=build js /usr/share/nginx/html/js
COPY --from=build node_modules /usr/share/nginx/html/node_modules
