# Base image
FROM nvcr.io/nvidia/tritonserver:24.12-py3

# install Python modules needed by the Python app
RUN apt-get update \
    && apt-get remove -y python3-blinker \
    && apt-get install -y nvidia-container-toolkit \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*
COPY requirements.txt /usr/src/app/
RUN pip install --no-cache-dir -r /usr/src/app/requirements.txt
