FROM gitpod/workspace-full:latest

USER gitpod

# Install Java 21.0.4-tem
RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java 21.0.4-tem && \
    sdk default java 21.0.4-tem"

# Install Node.js v20.18.1
RUN bash -c 'VERSION="20.18.1" && \
    source $HOME/.nvm/nvm.sh && nvm install $VERSION && \
    nvm use $VERSION && nvm alias default $VERSION'

# Ensure nvm uses the default version on every new terminal session
RUN echo "nvm use default &>/dev/null" >> ~/.bashrc.d/51-nvm-fix
