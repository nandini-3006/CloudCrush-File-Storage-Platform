☁️ CloudCrush
Your Personal Alternative to Google Drive

Because documents and memories — both deserve a safe home. 💙


🌟 Overview
CloudCrush is a production-grade cloud storage platform inspired by Google Drive’s architecture — built from scratch to understand and replicate its core design using a microservices-based cloud-native stack.
It allows users to securely upload, manage, and retrieve files, with real-time synchronization, intelligent caching, and seamless inter-service communication — all while ensuring scalability and reliability through containerization, load balancing, and service discovery.
Designed for scalability, security, and simplicity, CloudCrush provides a personal cloud experience that feels enterprise-ready.

🧩 Microservices Architecture
CloudCrush is built using the Spring Cloud Microservices model, where each service is independent, modular, and communicates with others through OpenFeign clients and REST APIs.
🧱 Service🧭 Responsibility🛠️ Core Technologies🪪 Auth ServiceManages secure user authentication and authorization with JWT-based tokens and Spring Security.Spring Boot, JWT, Thymeleaf📦 Storage ServiceHandles file upload, download, and deletion. Stores actual files on Supabase Cloud Storage using pre-signed URLs.Spring Boot, Supabase API📊 Metadata ServiceMaintains metadata for every uploaded file — including file name, size, owner, timestamps, and cloud URLs.Spring Data JPA, MySQL/H2🚪 API GatewayActs as a centralized entry point; routes, filters, and load-balances requests between microservices.Spring Cloud Gateway🔍 Eureka ServerEnables service discovery and instance management to ensure smooth communication and scaling.Spring Cloud Netflix Eureka⚡ Redis CacheProvides in-memory caching for frequent file metadata and authentication tokens to enhance performance.Redis🛰️ WebSocket ServiceEnables real-time synchronization across connected clients (like live updates for file actions).WebSocket

Each microservice is containerized and deployed using Docker Compose, ensuring modularity and easy scaling.


☁️ Cloud Storage Integration


Files are uploaded and stored in Supabase Buckets (S3-compatible), using pre-signed URLs for secure access.


Only metadata and file references are stored in the application database — reducing load and improving scalability.



⚙️ Tech Stack
CategoryTechnologies UsedBackend FrameworkJava 17, Spring Boot 3, Spring Cloud NetflixFrontendThymeleaf Templates (Login, Register, Dashboard)CommunicationREST, OpenFeign ClientAuthenticationSpring Security, JWTDatabaseH2 (Dev), MySQL (Prod)CacheRedisReal-TimeWebSocketCloud StorageSupabase (S3-compatible)ContainerizationDocker, Docker ComposeCI/CD PipelineJenkins, Docker HubBuild ToolMaven

🧱 Microservices Communication Flow


A request enters through the API Gateway, which routes it to the correct microservice.


Auth Service validates the JWT token for secure access.


Storage Service handles file upload/download operations via Supabase.


Metadata Service, through OpenFeign, stores all file URLs and metadata records.


Redis caches frequently accessed data for instant retrieval.


WebSocket broadcasts real-time updates across user sessions for seamless sync.


Eureka Server manages and monitors all microservice instances, allowing dynamic scaling and load balancing through the gateway.



🐳 Docker, DevOps & Scalability
CloudCrush is fully containerized — each microservice has its own Dockerfile and configuration.
All containers are orchestrated using Docker Compose for smooth, multi-service startup.
🚀 Jenkins CI/CD Pipeline


Every code push triggers Jenkins.


Jenkins runs automated builds (mvn clean package).


Docker images are created and pushed to Docker Hub.


Docker Compose redeploys updated containers automatically.


⚡ Built for Scalability & Performance


Load balancing through API Gateway.


Eureka Server dynamically manages and discovers service instances.


Redis caching reduces database hits and boosts speed.


Supabase Cloud Storage provides scalable and secure object storage.


WebSocket communication ensures real-time collaboration.



Together, these make CloudCrush scalable, responsive, and production-ready.


🌈 Future Enhancements


🌍 Public sharing & access controls


📁 Folder hierarchies and file versioning


🔒 OAuth2 and multi-factor authentication


☁️ Migration to Kubernetes for advanced orchestration


🤝 Open to collaborations, feedback, and improvement suggestions



💬 Slogan

💙 “CloudCrush — because documents and memories both deserve a home.”


👩‍💻 Built & Engineered By
✨ Nandini Sharma
🧠 Technical Founder & Full Stack Engineer
📍 Designed & developed from scratch to deeply understand Google Drive’s architecture and replicate it with modern cloud-native technologies.

Would you like me to now turn this version into a clean, aesthetic HTML README (with icons, subtle gradients, and proper sections) for your portfolio site?
It’ll look like a one-page professional landing page for your project.
