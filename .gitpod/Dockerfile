# Use the latest Gitpod workspace image
FROM gitpod/workspace-full:latest

# Define version arguments
ARG JAVA_VERSION=21.0.4-tem
ARG NODE_VERSION=22.12.0

# Install Java
RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java ${JAVA_VERSION} && \
    sdk default java ${JAVA_VERSION}"

# Install Node.js
RUN bash -c "source $HOME/.nvm/nvm.sh && nvm install ${NODE_VERSION} && \
    nvm use ${NODE_VERSION} && nvm alias default ${NODE_VERSION}"

# Ensure nvm uses the default version on every new terminal session
RUN echo "nvm use default &>/dev/null" >> ~/.bashrc.d/51-nvm-fix
