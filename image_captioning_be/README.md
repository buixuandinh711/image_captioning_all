# Image Captioning Application Back-end Service

This service receives base64 image requests from client applications, convert images to ML model compatible format and forward it to configured ML model hosted in Triton.

## How to run
To run this service with Docker:
1. Create `.env` file with:
```bash
cp .env.example .env
```
2. (Optional) If model is also run on host machine, get host machine IP for Triton URL:
```bash
ip addr show
```
3. Update `.env` with Triton URL and captioning model name (default model is `git`, other options is `blip` and `gitvqa`).
```
TRITON_REST_URL="http://192.168.164.104:8000" 
CAPTIONING_MODEL="git"
```
4. Run with Docker compose:
```bash
docker compose up
```