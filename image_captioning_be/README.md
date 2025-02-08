# Image Captioning Application Back-end Service

This service receives base64 image requests from client applications, convert images to ML model compatible format and forward it to configured ML model hosted in Triton.

## How to run
To run this service with Docker:
```bash
docker compose up
```