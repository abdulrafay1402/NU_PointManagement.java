# NU Point Management System 🎯

## 📖 Overview
The **NU Point Management System** is a Java-based console application designed to help teachers or admins efficiently manage student points. It offers features like **role-based access, point adjustments, data persistence, and a clean menu-driven UI**, making it ideal for small academic environments.

## 🚀 Features
- **Role-Based Access**:
  - **Admin Mode**: Add/deduct points, view/search all records
  - **Student Mode**: View-only access to their own points
- **Point Management**:
  - ➕ Add points for achievements or participation
  - ➖ Deduct points for penalties or violations
- **Record Search & View**:
  - 🔍 Search student records by name
  - 📜 View all students' points in a tabular format
- **Data Persistence**:
  - 💾 Automatically saves data to `students.txt`
  - 🔄 Retains student records between sessions
- **User Interface**:
  - 🧭 Clean, menu-driven console UI
  - ❌ Input validation to prevent invalid entries
- **Performance**:
  - ⚡ Lightweight and runs instantly on any machine

## 🛠 Technologies Used
- **Programming Language**: Java
- **Storage**: Text file (`students.txt`)
- **IDE Compatibility**: IntelliJ, Eclipse, NetBeans, VS Code

## 🔧 Installation & Usage
### 1️⃣ Clone the Repository
```bash
git clone https://github.com/abdulrafay1402/NU_PointManagement.java.git
cd NU_PointManagement.java
```

### 2️⃣ Compile & Run
```bash
javac NU_PointManagement.java
java NU_PointManagement
```

### 3️⃣ Modes of Use
- **Admin Mode**:
  - Add/deduct points
  - Search/view all records
- **Student Mode**:
  - View only their own points

## 📸 Sample Use Cases
### 1️⃣ Add / Deduct Points
- Admin adds 10 points to student "Ali" for participation
- Admin deducts 5 points from "Sarah" for late submission

### 2️⃣ View & Search Records
- View all students' current points
- Search for a specific student by name

### 3️⃣ Student Access
- Students can view their own point total
- Cannot modify or delete records

## 🎯 Future Improvements
- Add GUI using **Swing/JavaFX**
- Implement password-protected logins for admins
- Generate **charts or dashboards** for point trends
- Integrate with **Google Sheets** or **MySQL** for remote data sync

## 📝 Authors & Collaborators
- **Abdul Rafay** 📧 Contact: [abdulrafay14021997@gmail.com]
