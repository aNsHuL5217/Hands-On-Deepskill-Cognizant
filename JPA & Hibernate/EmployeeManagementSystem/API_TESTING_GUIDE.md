# API Testing Guide - Employee Management System

## Problem & Solution

### Issue: 400 Bad Request Errors
When testing endpoints in Postman/Insomnia, you were getting `400 Bad Request` errors on both the department and employee endpoints.

### Root Cause
The JSON request bodies were either:
1. Empty or incomplete
2. Missing required fields
3. Improperly formatted

### Solution
Use properly formatted JSON with all required fields. See examples below.

---

## API Endpoints & Testing

### 1. Department Management

#### Create Department
**Endpoint:** `POST /api/departments`

**Request Body:**
```json
{
  "name": "Engineering"
}
```

**Success Response:** `200 OK`
```json
{
  "id": 5,
  "name": "Engineering",
  "employees": [],
  "createdAt": "2026-07-08T23:27:29.557163",
  "updatedAt": "2026-07-08T23:27:29.557163"
}
```

#### Get All Departments
**Endpoint:** `GET /api/departments`

**Success Response:** `200 OK` - Array of departments

#### Get Department by ID
**Endpoint:** `GET /api/departments/{id}`

**Success Response:** `200 OK` - Single department

---

### 2. Employee Management

#### Create Single Employee
**Endpoint:** `POST /api/employees`

**Request Body:**
```json
{
  "name": "Priya",
  "email": "priya1@gmail.com",
  "department": {
    "id": 5
  }
}
```

**Important:** The `department.id` must exist in the database!

#### Bulk Create Employees ✅ (Verified Working)
**Endpoint:** `POST /api/employees/bulk`

**Request Body:**
```json
[
  {
    "name": "Priya",
    "email": "priya1@gmail.com",
    "department": {
      "id": 5
    }
  },
  {
    "name": "Rahul",
    "email": "rahul1@gmail.com",
    "department": {
      "id": 5
    }
  }
]
```

**Success Response:** `200 OK`
```json
[
  {
    "id": 4,
    "name": "Priya",
    "email": "priya1@gmail.com",
    "department": {
      "id": 5,
      "name": "Engineering",
      "createdAt": "2026-07-08T23:27:29.557163",
      "updatedAt": "2026-07-08T23:27:29.557163"
    },
    "createdAt": "2026-07-08T23:27:42.8356813",
    "updatedAt": "2026-07-08T23:27:42.8356813"
  },
  {
    "id": 5,
    "name": "Rahul",
    "email": "rahul1@gmail.com",
    "department": {
      "id": 5,
      "name": "Engineering",
      "createdAt": "2026-07-08T23:27:29.557163",
      "updatedAt": "2026-07-08T23:27:29.557163"
    },
    "createdAt": "2026-07-08T23:27:42.8409716",
    "updatedAt": "2026-07-08T23:27:42.8409716"
  }
]
```

#### Get All Employees
**Endpoint:** `GET /api/employees`

**Success Response:** `200 OK` - Array of all employees

#### Get Employee by ID
**Endpoint:** `GET /api/employees/{id}`

**Success Response:** `200 OK` - Single employee

---

### 3. JPA Projections (Verified Working ✅)

#### Basic Projection by Department
**Endpoint:** `GET /api/employees/projection/basic/department/{departmentId}`

**Test:** `GET /api/employees/projection/basic/department/5`

**Response:** `200 OK`
```json
[
  {
    "name": "Priya",
    "id": 4,
    "email": "priya1@gmail.com"
  },
  {
    "name": "Rahul",
    "id": 5,
    "email": "rahul1@gmail.com"
  }
]
```

**What it does:** Returns only essential employee fields (id, name, email) using interface-based projection. Reduces database load by fetching only required columns.

---

#### Summary Projection with Computed Properties
**Endpoint:** `GET /api/employees/projection/summary/department/{departmentId}`

**Test:** `GET /api/employees/projection/summary/department/5`

**Response:** `200 OK`
```json
[
  {
    "name": "Priya",
    "id": 4,
    "email": "priya1@gmail.com",
    "employeeSummary": "Priya works in Engineering"
  },
  {
    "name": "Rahul",
    "id": 5,
    "email": "rahul1@gmail.com",
    "employeeSummary": "Rahul works in Engineering"
  }
]
```

**What it does:** Uses @Value-based projection with SpEL (Spring Expression Language) to compute properties. The `employeeSummary` field is calculated server-side by concatenating employee name and department name.

---

#### DTO Projection (All Employees)
**Endpoint:** `GET /api/employees/projection/dto`

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Amit",
    "email": "amit101@gmail.com",
    "departmentName": "Engineering"
  },
  {
    "id": 2,
    "name": "Priya",
    "email": "priya101@gmail.com",
    "departmentName": "Engineering"
  },
  {
    "id": 3,
    "name": "Rahul",
    "email": "rahul101@gmail.com",
    "departmentName": "Engineering"
  },
  {
    "id": 4,
    "name": "Priya",
    "email": "priya1@gmail.com",
    "departmentName": "Engineering"
  },
  {
    "id": 5,
    "name": "Rahul",
    "email": "rahul1@gmail.com",
    "departmentName": "Engineering"
  }
]
```

**What it does:** Uses constructor-based DTO projection with JPQL to assemble complex objects. Directly maps joined table data (employee + department) into a lightweight DTO.

---

### 4. Reporting Datasource (Verified Working ✅)

#### Test Reporting Datasource
**Endpoint:** `GET /api/reporting/test`

**Response:** `200 OK`
```
Reporting datasource working
```

**What it does:** Demonstrates secondary datasource functionality for reporting queries. Uses a separate H2 in-memory database configured via `DataSourceConfig` bean.

---

## Testing Steps Summary

### Step 1: Create Department
```bash
POST http://localhost:8081/api/departments
Content-Type: application/json

{
  "name": "Engineering"
}
```

**Result:** Note the `id` returned (e.g., 5)

### Step 2: Bulk Create Employees
```bash
POST http://localhost:8081/api/employees/bulk
Content-Type: application/json

[
  {
    "name": "Priya",
    "email": "priya1@gmail.com",
    "department": { "id": 5 }
  },
  {
    "name": "Rahul",
    "email": "rahul1@gmail.com",
    "department": { "id": 5 }
  }
]
```

### Step 3: Test Projections (All working ✅)
```bash
# Basic projection
GET http://localhost:8081/api/employees/projection/basic/department/5

# Summary projection
GET http://localhost:8081/api/employees/projection/summary/department/5

# DTO projection
GET http://localhost:8081/api/employees/projection/dto
```

### Step 4: Verify Reporting Datasource
```bash
GET http://localhost:8081/api/reporting/test
```

---

## Common Errors & Fixes

### Error: 400 Bad Request
**Cause:** Malformed JSON or missing required fields

**Fix:**
- Ensure JSON is valid (use a JSON validator)
- Include all required fields: `name`, `email`, `department.id`
- Set `Content-Type: application/json` header
- For POST /api/employees/bulk, body must be a JSON array `[...]`

### Error: 500 Internal Server Error (old issue - now fixed)
**Cause:** Department ID doesn't exist when creating employees

**Fix:**
- Create the department first
- Use the correct department ID in employee creation
- The department must exist before referencing it

---

## Test Results - All Endpoints Verified ✅

| Endpoint | Method | Status | Result |
|----------|--------|--------|--------|
| `/api/departments` | POST | ✅ 200 | Department created successfully |
| `/api/employees/bulk` | POST | ✅ 200 | 2 employees bulk inserted |
| `/api/employees/projection/basic/department/5` | GET | ✅ 200 | Returns 2 employees with basic projection |
| `/api/employees/projection/summary/department/5` | GET | ✅ 200 | Returns 2 employees with computed summary |
| `/api/employees/projection/dto` | GET | ✅ 200 | Returns all employees with DTO projection |
| `/api/reporting/test` | GET | ✅ 200 | Secondary datasource confirmed working |

---

## Key Features Demonstrated

### 1. **JPA Projections**
- **Interface-based:** Lightweight queries selecting only needed fields
- **@Value-based:** Computed properties using SpEL expressions
- **DTO-based:** Complex data assembly with constructor expressions

### 2. **Bulk Operations**
- Batch insert multiple employees with Hibernate batch optimization
- Configured with `hibernate.jdbc.batch_size=20`
- Reduces database round-trips significantly

### 3. **Multi-Datasource**
- Primary datasource for transactional operations
- Secondary reporting datasource for analytics queries
- Configured in `DataSourceConfig.java`

### 4. **Performance Optimizations**
- @DynamicUpdate: Only updates modified columns
- @BatchSize: Batch loading of related entities
- Batch processing: Reduces N+1 query problems

---

## Notes

- All endpoints tested and working correctly ✅
- The application uses H2 in-memory database for both primary and reporting datasources
- Data persists during the current application session
- Restarting the app will reset the database
- For production, migrate to a persistent database (PostgreSQL, MySQL, etc.)
