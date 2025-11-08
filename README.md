<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="☁️ CloudCrush — Your Personal Alternative to Google Drive. A cloud-native microservices-based storage platform built with Spring Boot, Docker, Jenkins, and Supabase.">
  <meta name="author" content="Nandini Sharma">
  <meta property="og:title" content="CloudCrush | Your Personal Alternative to Google Drive">
  <meta property="og:description" content="Because documents and memories — both deserve a safe home. 💙">
  <meta property="og:type" content="website">
  <meta property="og:image" content="https://img.icons8.com/fluency/96/cloud.png">
  <meta name="theme-color" content="#0077ff">
  <link rel="icon" href="https://img.icons8.com/fluency/48/cloud.png" type="image/png">
  <title>☁️ CloudCrush | Your Personal Alternative to Google Drive</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">
</head>
<body>

<header>
  <h1>☁️ CloudCrush</h1>
  <p>Your Personal Alternative to Google Drive</p>
  <p><em>Because documents and memories — both deserve a safe home. 💙</em></p>
</header>

<section>
  <h2>🌟 Overview</h2>
  <p><span class="highlight">CloudCrush</span> is a production-grade cloud storage platform inspired by Google Drive’s architecture — built from scratch to understand and replicate its core design using a microservices-based cloud-native stack.</p>
  <p>It allows users to securely upload, manage, and retrieve files, with real-time synchronization, intelligent caching, and seamless inter-service communication — all while ensuring scalability and reliability through containerization, load balancing, and service discovery.</p>
  <p>Designed for scalability, security, and simplicity, CloudCrush provides a personal cloud experience that feels enterprise-ready.</p>
</section>

<section>
  <h2>🧩 Microservices Architecture</h2>
  <p>Built using <strong>Spring Cloud Microservices</strong> — each service is independent and modular, communicating through OpenFeign clients and REST APIs.</p>

  <table>
    <tr><th>🧱 Service</th><th>🧭 Responsibility</th><th>🛠️ Core Technologies</th></tr>
    <tr><td>🪪 Auth Service</td><td>Manages secure user authentication and authorization with JWT-based tokens and Spring Security.</td><td>Spring Boot, JWT, Thymeleaf</td></tr>
    <tr><td>📦 Storage Service</td><td>Handles file upload, download, and deletion. Stores files on Supabase Cloud Storage using pre-signed URLs.</td><td>Spring Boot, Supabase API</td></tr>
    <tr><td>📊 Metadata Service</td><td>Maintains metadata for every uploaded file — including file name, size, owner, timestamps, and URLs.</td><td>Spring Data JPA, MySQL/H2</td></tr>
    <tr><td>🚪 API Gateway</td><td>Acts as centralized entry point; routes, filters, and load-balances requests between microservices.</td><td>Spring Cloud Gateway</td></tr>
    <tr><td>🔍 Eureka Server</td><td>Enables service discovery and instance management for scaling and communication.</td><td>Spring Cloud Netflix Eureka</td></tr>
    <tr><td>⚡ Redis Cache</td><td>Provides in-memory caching for frequent file metadata and authentication tokens to enhance performance.</td><td>Redis</td></tr>
    <tr><td>🛰️ WebSocket Service</td><td>Enables real-time synchronization across connected clients (like live updates for file actions).</td><td>WebSocket</td></tr>
  </table>
  <p>Each microservice is containerized and deployed using <strong>Docker Compose</strong> for modularity and easy scaling.</p>
</section>

<section>
  <h2>☁️ Cloud Storage Integration</h2>
  <ul>
    <li>Files are uploaded and stored in <strong>Supabase Buckets (S3-compatible)</strong> using pre-signed URLs for secure access.</li>
    <li>Only metadata and file references are stored in the app database — improving scalability and reducing storage load.</li>
  </ul>
</section>

<section>
  <h2>⚙️ Tech Stack</h2>
  <table>
    <tr><th>Category</th><th>Technologies Used</th></tr>
    <tr><td>Backend Framework</td><td>Java 17, Spring Boot 3, Spring Cloud Netflix</td></tr>
    <tr><td>Frontend</td><td>Thymeleaf Templates (Login, Register, Dashboard)</td></tr>
    <tr><td>Communication</td><td>REST, OpenFeign Client</td></tr>
    <tr><td>Authentication</td><td>Spring Security, JWT</td></tr>
    <tr><td>Database</td><td>H2 (Dev), MySQL (Prod)</td></tr>
    <tr><td>Cache</td><td>Redis</td></tr>
    <tr><td>Real-Time</td><td>WebSocket</td></tr>
    <tr><td>Cloud Storage</td><td>Supabase (S3-compatible)</td></tr>
    <tr><td>Containerization</td><td>Docker, Docker Compose</td></tr>
    <tr><td>CI/CD Pipeline</td><td>Jenkins, Docker Hub</td></tr>
    <tr><td>Build Tool</td><td>Maven</td></tr>
  </table>
</section>

<section>
  <h2>🐳 Docker, DevOps & Scalability</h2>
  <ul>
    <li>Each microservice has its own Dockerfile and configuration.</li>
    <li>All containers are orchestrated using <strong>Docker Compose</strong> for smooth startup.</li>
  </ul>
  <h3>🚀 Jenkins CI/CD Pipeline</h3>
  <ul>
    <li>Every code push triggers Jenkins.</li>
    <li>Automated builds via <code>mvn clean package</code>.</li>
    <li>Docker images are built and pushed to Docker Hub.</li>
    <li>Docker Compose redeploys updated containers automatically.</li>
  </ul>
  <h3>⚡ Scalability & Performance</h3>
  <ul>
    <li>Load balancing via API Gateway.</li>
    <li>Eureka dynamically manages service instances.</li>
    <li>Redis caching boosts performance.</li>
    <li>Supabase ensures scalable cloud storage.</li>
    <li>WebSocket ensures real-time sync across clients.</li>
  </ul>
</section>

<section>
  <h2>🌈 Future Enhancements</h2>
  <ul>
    <li>🌍 Public sharing & access controls</li>
    <li>📁 Folder hierarchies and file versioning</li>
    <li>🔒 OAuth2 and multi-factor authentication</li>
    <li>☁️ Migration to Kubernetes for advanced orchestration</li>
  </ul>
  <p>🤝 Ready to collaborate, get suggestions, and improve further!</p>
</section>

<footer>
  <p>💙 “CloudCrush — because documents and memories both deserve a home.”</p>
  <p>👩‍💻 <strong>Nandini Sharma</strong> — Technical Founder & Software Engineer</p>
  <p><em>Designed & developed from scratch to deeply understand Google Drive’s architecture and replicate it with modern cloud-native technologies.</em></p>
</footer>

</body>
</html>
