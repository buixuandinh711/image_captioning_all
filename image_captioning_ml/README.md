# ML

## Chạy Triton bằng Dockerfile

## Chạy với Docker Compose

### 1. Khởi Chạy
```bash
docker-compose up -d
```

### 2. Kiểm Tra Logs
```bash
docker-compose logs -f
```

### 3. Dừng Dịch Vụ
```bash
docker-compose down
```

## Kiểm Tra Dịch Vụ
Sau khi chạy, truy cập địa chỉ:
- **Triton Inference Server:** `http://localhost:8000/v2/health/ready`
- **Triton Metrics:** `http://localhost:8002/metrics`


## Tài Liệu Tham Khảo
- [Triton Inference Server](https://github.com/triton-inference-server/server)
- [Docker Documentation](https://docs.docker.com/)

## Finetune mô hình ImageCaptioning
Chi tiết tại: [image_captioning_ml/finetune/README.md]