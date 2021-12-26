FROM node:16-alpine

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
COPY package-lock.json ./

# add app
COPY . ./

RUN npm cache clean --force
RUN npm cache verify
RUN npm install --silent

# start app
EXPOSE 80
CMD ["npm", "start"]
