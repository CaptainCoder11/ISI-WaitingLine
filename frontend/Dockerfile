FROM node:16-alpine as build-stage
WORKDIR /app
COPY package*.json /app/
RUN npm install
COPY . ./
ARG configuration=production
RUN npm run build -- --output-path=./dist/out --configuration $configuration

FROM nginx:1.15
COPY --from=build-stage /app/dist/out/ /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf

