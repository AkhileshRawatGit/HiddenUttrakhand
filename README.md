# HiddenUkWeb 🏔️

**HiddenUkWeb** is a tourism discovery platform focused on showcasing **hidden and unexplored destinations of Uttarakhand** beyond mainstream tourist locations.

The platform combines destination discovery with hotel and room management, inventory tracking, booking workflows, dynamic pricing, and secure role-based access.

## 🚀 Features

### 🏔️ Destination Discovery

* Discover hidden and unexplored destinations across Uttarakhand.
* Organize destinations and associated accommodations.
* Provide structured information about tourist places.

### 🏨 Hotel Management

* Manage hotels associated with destinations.
* Manage hotel rooms.
* Maintain room inventory.
* Manage hotel images using MinIO object storage.

### 💰 Dynamic Pricing

* Implemented dynamic hotel pricing based on:

  * Demand.
  * Season.
* Pricing can adapt to changing booking conditions.

### 📅 Booking Management

* Manage room availability.
* Track inventory.
* Process booking workflows.
* Maintain relationships between destinations, hotels, rooms, inventory, and bookings.

### 🔐 Authentication & Authorization

* JWT-based authentication.
* Spring Security integration.
* Role-based access control.
* Protected REST APIs.

## 🔑 Key Highlights

* **5-level nested architecture:**

```text
Place
  ↓
Hotel
  ↓
Room
  ↓
Inventory
  ↓
Booking
```

* Dynamic demand/season-based pricing.
* MinIO-based object storage.
* DTO-based API architecture using ModelMapper.
* JWT authentication and role-based authorization.
* RESTful backend architecture.

## 🏗️ Architecture

```text
Client
   │
   ▼
REST APIs
   │
   ▼
Spring Boot
   │
   ├── Spring Security + JWT
   │
   ├── Controller Layer
   │
   ├── Service Layer
   │
   ├── Repository Layer
   │
   ▼
MySQL

External Storage
   │
   ▼
MinIO
```

## 🛠️ Tech Stack

| Technology      | Purpose               |
| --------------- | --------------------- |
| Java            | Backend Development   |
| Spring Boot     | REST API Development  |
| Spring Data JPA | Persistence Layer     |
| MySQL           | Database              |
| Spring Security | Security              |
| JWT             | Authentication        |
| ModelMapper     | DTO Mapping           |
| MinIO           | Object Storage        |
| REST APIs       | Backend Communication |

## 📂 Core Modules

```text
Authentication
    └── Login / JWT / Role Management

Place Management
    └── Destinations / Locations

Hotel Management
    └── Hotels / Rooms / Images

Inventory Management
    └── Room Availability / Inventory

Pricing
    └── Demand-Based Pricing
    └── Seasonal Pricing

Booking
    └── Room Booking / Booking Management

Media
    └── MinIO Object Storage
```

## 🔗 Entity Relationship

```text
Place
 └── Hotels
      └── Rooms
           └── Inventory
                └── Bookings
```

This structure allows the application to maintain a clear relationship between tourist destinations, accommodations, room availability, and bookings.

## 🗄️ Data Management

The application uses **MySQL** as its relational database and **Spring Data JPA** for persistence.

DTOs are used between the API and persistence layers to provide cleaner API contracts and reduce direct exposure of database entities.

## 🪣 Object Storage

**MinIO** is used for structured hotel image storage.

```text
Application
     ↓
Image Upload
     ↓
MinIO
     ↓
Object URL / Metadata
     ↓
Hotel Response
```

## 🔐 Security

Security is implemented using:

* Spring Security.
* JWT authentication.
* Role-based access control.
* Protected REST endpoints.

## 📌 Future Improvements

* Online payment integration.
* Hotel reviews and ratings.
* Location-based recommendations.
* Tourist itinerary planning.
* Map integration.
* Personalized destination recommendations.

## 👨‍💻 Author

**Akhilesh Rawat**

[GitHub](https://github.com/AkhileshRawatGit)
